package study.lld.projects.notification;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;

/*
- time: 1/2d Did a heavy redo in depth
- Learnings
- producer consumer sematics
*/
public class Main {
    public static void main(String[] args) throws InterruptedException {
        /*
        Notification System
        - Subject or information
        - Channel sms, email, post
        - each channel can have jobs which are put in queue and notifications can be processed async
        - each channel will have consumers which will send messages by their channel in batches.
        - Formatting of different channels might be different, so we will have formatters
        - concurrent access will be managed by a queueing service.


        User
        - id
        - name
        - email
        - phone
        - address

        NotificationSystem
        - addChannel
        - notify(user,title,body)

        Job
        - id
        - user
        - title
        - body

        NotificationChannel
        - id
        - notify(Job job)
        - setQueue

        NotificationChannelConsumer implements Runnable
        - setQueue
        - run()

         */
        // ------------------------------------------------------------
        // 1. DAOs
        // ------------------------------------------------------------

        UserDao userDao = new UserDao();
        JobDao jobDao = new JobDao();
        JobLeaseDao jobLeaseDao = new JobLeaseDao();
        NotificationRequestDao notificationRequestDao = new NotificationRequestDao();

        // ------------------------------------------------------------
        // 2. Services
        // ------------------------------------------------------------

        UserService userService = new UserService(userDao);

        JobLeaseService jobLeaseService = new JobLeaseService(jobLeaseDao);

        JobService jobService = new JobService(jobDao, jobLeaseService);

        NotificationRequestService notificationRequestService =
                new NotificationRequestService(notificationRequestDao);

        // ------------------------------------------------------------
        // 3. Queues
        //
        // Don't use ArrayDeque because consumers/producers are concurrent.
        // ------------------------------------------------------------

        Queue<Job> emailQueue = new ConcurrentLinkedQueue<>();

        Queue<Job> smsQueue = new ConcurrentLinkedQueue<>();

        // ------------------------------------------------------------
        // 4. Channels / producers
        // ------------------------------------------------------------

        Channel emailChannel = new EmailChannel(emailQueue);

        Channel smsChannel = new SmsChannel(smsQueue);

        // ------------------------------------------------------------
        // 5. Notification service
        // ------------------------------------------------------------

        NotificationSystemService notificationSystemService =
                new NotificationSystemService(jobService, userService, notificationRequestService);

        notificationSystemService.addChannel(emailChannel);
        notificationSystemService.addChannel(smsChannel);

        // ------------------------------------------------------------
        // 6. Consumers
        // ------------------------------------------------------------

        int maxRetries = 5;
        int batchSize = 10;

        NotificationConsumer emailConsumer =
                new EmailNotificationConsumer(
                        1, // consumerId
                        maxRetries,
                        emailQueue,
                        batchSize,
                        jobService);

        NotificationConsumer smsConsumer =
                new SmsConsumer(
                        2, // consumerId
                        maxRetries,
                        jobService,
                        smsQueue,
                        batchSize);

        // ------------------------------------------------------------
        // 7. Start consumers
        // ------------------------------------------------------------

        ScheduledExecutorService consumerExecutor = Executors.newScheduledThreadPool(2);

        consumerExecutor.scheduleWithFixedDelay(emailConsumer, 0, 3, TimeUnit.SECONDS);

        consumerExecutor.scheduleWithFixedDelay(smsConsumer, 0, 3, TimeUnit.SECONDS);

        // ------------------------------------------------------------
        // 8. Create test user
        // ------------------------------------------------------------

        User user = new User(1, "Harshit", "address", "harshit@gmail.com", "1234");

        userService.save(user);

        // ------------------------------------------------------------
        // 9. Create notification requests
        // ------------------------------------------------------------

        for (int i = 1; i <= 7; i++) {

            CreateNotificationRequest request =
                    CreateNotificationRequest.builder()
                            .id(i)
                            .retryToken(1000 + i)
                            .notificationDetails(
                                    NotificationDetails.builder()
                                            .userId(user.id)
                                            .channelType(ChannelType.SMS)
                                            .title("Welcome " + i)
                                            .body("Hello user " + i)
                                            .build())
                            .build();

            CreateNotificationResponse response =
                    notificationSystemService.createNotification(request);

            System.out.println(
                    "Created notification: "
                            + response.id
                            + " status="
                            + response.status
                            + " message="
                            + response.message);
        }

        // ------------------------------------------------------------
        // 10. Let consumers run for a while
        // ------------------------------------------------------------

        Thread.sleep(20_000);

        // ------------------------------------------------------------
        // 11. Shutdown
        // ------------------------------------------------------------

        consumerExecutor.shutdownNow();

        System.out.println("Application stopped");
    }
}

@AllArgsConstructor
class User {
    int id;
    String name;
    String address;
    String email;
    String phone;
}

class UserService {
    private final UserDao userDao;

    UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    User getUser(int id) {
        return userDao.get(id);
    }

    void save(User user) {
        userDao.save(user);
    }

    Optional<User> findUser(int id) {
        try {
            return Optional.of(userDao.get(id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

class UserDao {
    Map<Integer, User> users = new ConcurrentHashMap<>();

    public User get(int id) {
        if (!users.containsKey(id)) throw new RuntimeException("User doesnt exist");
        return users.get(id);
    }

    public void save(User user) {
        users.put(user.id, user);
    }
}

class Job {
    static final AtomicInteger counter = new AtomicInteger(0);
    int id;
    int userId;
    String title;
    String body;
    int retryToken;
    int noOfAssignments;
    JobStatus status;
    ChannelType channelType;

    public Job(int userId, String title, String body, ChannelType channelType) {
        this.id = counter.getAndIncrement();
        this.userId = userId;
        this.title = title;
        this.body = body;
        this.channelType = channelType;
        this.status = JobStatus.ACCEPTED;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Job job) {
            return job.retryToken == retryToken
                    && job.body.equals(body)
                    && job.title.equals(title)
                    && job.channelType.equals(channelType)
                    && job.userId == userId;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return retryToken + body.hashCode() + title.hashCode() + channelType.hashCode() + userId;
    }
}

enum JobStatus {
    ACCEPTED,
    FAILED,
    SUBMITTED_TO_QUEUE,
    LEASED,
    TERMINALLY_FAILED,
    COMPLETED
}

@Builder
class CreateNotificationRequest {
    int id;
    Integer retryToken;
    NotificationDetails notificationDetails;
}

@Builder
class NotificationDetails {
    int userId;
    ChannelType channelType;
    String title;
    String body;
}

@Builder
class CreateNotificationResponse {
    int id;
    int retryToken;
    int userId;
    ChannelType channelType;
    NotificationRequestStatus status;
    String message;
}

enum NotificationRequestStatus {
    ACCEPTED,
    INIT,
    TERMINALLY_FAILED,
    FAILED
}

class NotificationSystemService {
    private final JobService jobService;
    private final UserService userService;
    private final NotificationRequestService notificationRequestService;
    Map<ChannelType, Channel> channels;
    ScheduledExecutorService repairScanExecutor = Executors.newSingleThreadScheduledExecutor();

    NotificationSystemService(
            JobService jobService,
            UserService userService,
            NotificationRequestService notificationRequestService) {
        this.jobService = jobService;
        this.userService = userService;
        this.notificationRequestService = notificationRequestService;
        channels = new HashMap<>();
        repairScanExecutor.scheduleWithFixedDelay(this::repairScan, 2L, 2L, TimeUnit.MINUTES);
    }

    void addChannel(Channel channel) {
        channels.put(channel.getType(), channel);
    }

    CreateNotificationResponse createNotification(
            CreateNotificationRequest createNotificationRequest) {
        Optional<NotificationRequest> existingRequest = Optional.empty();
        try {
            existingRequest = notificationRequestService.find(createNotificationRequest.retryToken);
            if (existingRequest.isPresent()) {
                if (existingRequest.get().status == NotificationRequestStatus.ACCEPTED)
                    return createResponse(existingRequest.get(), "existing request found");
                if (existingRequest.get().status == NotificationRequestStatus.TERMINALLY_FAILED)
                    return createResponse(existingRequest.get(), "incorrect request");
            }
        } catch (Exception e) {
            return failedResponse(createNotificationRequest, "unexpected failure");
        }

        NotificationRequest request;

        // 1. Get or create request
        try {
            request =
                    existingRequest.orElseGet(
                            () ->
                                    notificationRequestService.createNotificationRequest(
                                            createNotificationRequest.notificationDetails));
        } catch (Exception e) {
            return failedResponse(createNotificationRequest, "request could not be created");
        }

        // 2. Find user
        User user;

        try {
            user =
                    userService
                            .findUser(createNotificationRequest.notificationDetails.userId)
                            .orElseThrow();
        } catch (NoSuchElementException e) {
            notificationRequestService.updateStatus(
                    request.id, NotificationRequestStatus.TERMINALLY_FAILED);

            return failedTerminalResponse(createNotificationRequest, "user not found");
        } catch (Exception e) {
            notificationRequestService.updateStatus(request.id, NotificationRequestStatus.FAILED);

            return failedResponse(createNotificationRequest, "failed while looking up user");
        }

        // 3. Validate request/user/channel details
        try {
            validateNotificationAndUserDetails(createNotificationRequest, user);
        } catch (Exception e) {
            notificationRequestService.updateStatus(
                    request.id, NotificationRequestStatus.TERMINALLY_FAILED);

            return failedTerminalResponse(
                    createNotificationRequest, "invalid notification request");
        }

        // 4. Create missing jobs atomically
        List<Job> jobsToBeCreated;

        try {
            // atomic mutation boundary start

            List<Job> alreadyCreatedJobs =
                    jobService.findExistingJobsForRequest(request.retryToken);

            List<Job> proposedJobs = createProposedJobsBasedOnRequest(request);

            jobsToBeCreated = computeJobsToBeCreated(alreadyCreatedJobs, proposedJobs);

            jobsToBeCreated.forEach(jobService::save);

            // atomic mutation boundary end

        } catch (Exception e) {
            notificationRequestService.updateStatus(request.id, NotificationRequestStatus.FAILED);

            return failedResponse(createNotificationRequest, "jobs could not be created");
        }

        // 5. Mark request accepted
        try {
            notificationRequestService.updateStatus(request.id, NotificationRequestStatus.ACCEPTED);
        } catch (Exception e) {
            /*
             * Jobs were already durably created.
             * Client can retry with the same retryToken.
             * Existing jobs should not be created again.
             */
            return failedResponse(
                    createNotificationRequest,
                    "jobs created but request status could not be updated");
        }

        // 6. Best-effort enqueue
        try {
            enqueueJobs(jobsToBeCreated);
        } catch (Exception e) {
            /*
             * Request is already ACCEPTED.
             * Jobs are persisted.
             *
             * Repair scan can later find jobs that were not
             * successfully submitted to the queue.
             *
             * Do not mark the request FAILED here.
             */
            return createResponse(
                    request, "notification accepted; queue submission will be retried");
        }

        return createResponse(request, "notification accepted");
    }

    private void enqueueJobs(List<Job> jobs) {
        jobs.forEach(
                j -> {
                    if (!channels.containsKey(j.channelType)) {
                        throw new RuntimeException("channel is not supported");
                    }
                    Channel channel = channels.get(j.channelType);
                    channel.submitNotificationJob(j);
                    jobService.updateJobStatus(j, JobStatus.SUBMITTED_TO_QUEUE);
                });
    }

    private List<Job> computeJobsToBeCreated(List<Job> alreadyCreatedJobs, List<Job> proposedJobs) {
        List<Job> jobsToBeCreated = new ArrayList<>();
        for (Job proposed : proposedJobs) {
            if (!alreadyCreatedJobs.contains(proposed)) {
                jobsToBeCreated.add(proposed);
            }
        }
        return jobsToBeCreated;
    }

    private List<Job> createProposedJobsBasedOnRequest(NotificationRequest request) {
        List<Job> proposedJobs = new ArrayList<>();
        List<ChannelType> supportedChannels =
                channels.values().stream().map(c -> c.getType()).collect(Collectors.toList());
        if (request.notificationDetails.channelType == ChannelType.ALL_REGISTERED_CHANNELS) {
            supportedChannels.forEach(
                    type -> {
                        proposedJobs.add(
                                new Job(
                                        request.userId,
                                        request.notificationDetails.title,
                                        request.notificationDetails.body,
                                        type));
                    });
        } else {
            proposedJobs.add(
                    new Job(
                            request.userId,
                            request.notificationDetails.title,
                            request.notificationDetails.body,
                            request.notificationDetails.channelType));
        }
        return proposedJobs;
    }

    private void validateNotificationAndUserDetails(
            CreateNotificationRequest createNotificationRequest, User user) {
        List<ChannelType> supportedChannels =
                channels.values().stream().map(c -> c.getType()).collect(Collectors.toList());
        if (createNotificationRequest.notificationDetails.channelType
                        != ChannelType.ALL_REGISTERED_CHANNELS
                && !supportedChannels.contains(
                        createNotificationRequest.notificationDetails.channelType)) {
            throw new RuntimeException("channel is not supported by service");
        }
        switch (createNotificationRequest.notificationDetails.channelType) {
            case SMS -> {
                if (Objects.isNull(user.phone))
                    throw new RuntimeException("phone of user should be non null");
            }
            case EMAIL -> {
                if (Objects.isNull(user.email))
                    throw new RuntimeException("email of user should be non null");
            }
            case ALL_REGISTERED_CHANNELS -> {
                if (Objects.isNull(user.phone) || Objects.isNull(user.email))
                    throw new RuntimeException("phone/email of user should be non null");
            }
        }
    }

    private CreateNotificationResponse failedResponse(
            CreateNotificationRequest createNotificationRequest, String message) {
        return new CreateNotificationResponse(
                createNotificationRequest.id,
                createNotificationRequest.retryToken,
                createNotificationRequest.notificationDetails.userId,
                createNotificationRequest.notificationDetails.channelType,
                NotificationRequestStatus.FAILED,
                message);
    }

    private CreateNotificationResponse failedTerminalResponse(
            CreateNotificationRequest createNotificationRequest, String message) {
        return new CreateNotificationResponse(
                createNotificationRequest.id,
                createNotificationRequest.retryToken,
                createNotificationRequest.notificationDetails.userId,
                createNotificationRequest.notificationDetails.channelType,
                NotificationRequestStatus.TERMINALLY_FAILED,
                message);
    }

    private CreateNotificationResponse createResponse(
            NotificationRequest notificationRequest, String message) {
        return new CreateNotificationResponse(
                notificationRequest.id,
                notificationRequest.retryToken,
                notificationRequest.userId,
                notificationRequest.notificationDetails.channelType,
                notificationRequest.status,
                message);
    }

    void repairScan() {
        List<Job> jobs = jobService.listNonTerminalJobs();
        List<Job> jobsInAcceptedState =
                jobs.stream()
                        .filter(j -> j.status == JobStatus.ACCEPTED)
                        .collect(Collectors.toList());
        enqueueJobs(jobsInAcceptedState);

        List<Job> jobsInFailedState =
                jobs.stream()
                        .filter(j -> j.status == JobStatus.FAILED)
                        .collect(Collectors.toList());
        enqueueJobs(jobsInFailedState);

        List<Job> jobsWithExpiredLeases =
                jobs.stream()
                        .filter(j -> j.status == JobStatus.LEASED)
                        .filter(j -> !jobService.hasActiveLease(j))
                        .collect(Collectors.toList());
        enqueueJobs(jobsWithExpiredLeases);
    }
}

@AllArgsConstructor
class NotificationRequest {
    int id;
    Integer retryToken;
    int userId;
    NotificationDetails notificationDetails;
    NotificationRequestStatus status;
}

class NotificationRequestService {
    private final NotificationRequestDao notificationRequestDao;

    NotificationRequestService(NotificationRequestDao notificationRequestDao) {
        this.notificationRequestDao = notificationRequestDao;
    }

    NotificationRequest createNotificationRequest(NotificationDetails notificationDetails) {
        return notificationRequestDao.create(notificationDetails);
    }

    void updateStatus(int id, NotificationRequestStatus status) {
        notificationRequestDao.get(id).status = status;
    }

    public NotificationRequest get(Integer id) {
        return notificationRequestDao.get(id);
    }

    public Optional<NotificationRequest> find(int id) {
        return notificationRequestDao.find(id);
    }
}

class NotificationRequestDao {
    private static AtomicInteger counter = new AtomicInteger(0);
    Map<Integer, NotificationRequest> requests = new ConcurrentHashMap<>();

    public NotificationRequest create(NotificationDetails notificationDetails) {
        int id = counter.incrementAndGet();
        NotificationRequest request =
                new NotificationRequest(
                        id,
                        id,
                        notificationDetails.userId,
                        notificationDetails,
                        NotificationRequestStatus.INIT);
        requests.put(id, request);
        return request;
    }

    public NotificationRequest get(Integer id) {
        if (!requests.containsKey(id)) throw new RuntimeException("request doesnt exist");
        return requests.get(id);
    }

    public Optional<NotificationRequest> find(int id) {
        try {
            return Optional.of(get(id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

interface Channel {
    void submitNotificationJob(Job job);

    ChannelType getType();
}

enum ChannelType {
    SMS,
    EMAIL,
    ALL_REGISTERED_CHANNELS
}

class EmailChannel implements Channel {
    private final Queue<Job> q;

    EmailChannel(Queue<Job> q) {
        this.q = q;
    }

    @Override
    public void submitNotificationJob(Job job) {
        System.out.printf("Submitting email job %d to channel\n", job.id);
        q.offer(job);
    }

    @Override
    public ChannelType getType() {
        return ChannelType.EMAIL;
    }
}

class SmsChannel implements Channel {
    private final Queue<Job> q;

    SmsChannel(Queue<Job> q) {
        this.q = q;
    }

    @Override
    public void submitNotificationJob(Job job) {
        System.out.printf("Submitting sms job %d to channel\n", job.id);
        q.offer(job);
    }

    @Override
    public ChannelType getType() {
        return ChannelType.SMS;
    }
}

abstract class NotificationConsumer implements Runnable {
    private final JobService jobService;
    private final int consumerId;
    private final int MAX_JOB_RETRY;

    NotificationConsumer(JobService jobService, int id, int maxJobRetry) {
        this.jobService = jobService;
        this.consumerId = id;
        MAX_JOB_RETRY = maxJobRetry;
    }

    /*
    a job is in queue
    - consumer picks it but immediately dies. The get is not committed and its retried.
    - a consumer picks up a job, but its has an active lease. its skipped and commited.
    - a consumer picks up a job which is terminally failed, the consumer skips it and commits it.
    - a consumer picks up a failed job with retries left. It processes it normally.
    - If a consumer take a lease and dies then upon expiry, repair scan will put the job back in queue.
     */

    void processJob(Job job) {
        if (job == null) {
            System.out.println("no jobs");
            return;
        }

        if (job.status == JobStatus.COMPLETED) {
            System.out.println("job is already completed");
            return;
        }

        if (job.status == JobStatus.TERMINALLY_FAILED) {
            System.out.println("job is terminally failed");
            return;
        }

        if (job.status == JobStatus.LEASED && jobService.hasActiveLease(job)) {
            // once the lease expires then repair scan will add the job to queue again, it will mark
            // the job IN QUEUE
            System.out.println("job is leased");
            return;
        }

        if (job.status == JobStatus.FAILED && job.noOfAssignments >= MAX_JOB_RETRY) {
            System.out.println("max job retry reached");
            try {
                jobService.updateJobStatus(job, JobStatus.TERMINALLY_FAILED);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("unexpected error while changing status");
            }

            // dead letter and emit metrics
            return;
        }

        try {
            // mutation boundary start; get lock on lease with id = job.id
            // get lock on job with job id = job.id
            if (!jobService.tryToGetLease(job, consumerId)) {
                System.out.println("cannot get lease");
                return;
            }

            jobService.updateJobStatus(job, JobStatus.LEASED);

            int previousNoOfAssignments = job.noOfAssignments;
            try {
                jobService.incrementAssignmentOfJob(job.id);
            } catch (Exception e) {
                System.out.println("increment failed");
                System.out.println(e);
                jobService.setNoOfAssignments(job.id, previousNoOfAssignments);
                return;
            }

            // sending notification is idempotent
            sendNotification(job);

            jobService.updateJobStatus(job, JobStatus.COMPLETED);
            // mutation boundary end; release lock on lease with id = job.id
        } catch (Exception e) {
            System.out.println("Unexpected failure");
            System.out.println(e);
            jobService.updateJobStatus(job, JobStatus.FAILED);
        } finally {
            jobService.releaseLease(job, consumerId);
        }
    }

    @Override
    public void run() {
        System.out.println("consumer batch started on " + Thread.currentThread().getName());
        for (int i = 0; i < getBatch(); i++) {
            if (getQueue().isEmpty()) {
                System.out.println("no jobs in queue");
                break;
            }
            Job job = getQueue().poll();
            if (job == null) continue;
            try {
                processJob(job);
                commitJobInQueue();
            } catch (Exception e) {
                System.out.println("unexpected error occurred");
            }
        }
        System.out.println("consumer batch completed " + Thread.currentThread().getName());
    }

    private void commitJobInQueue() {
        // In kafka there is a concept of checkpoint. we will do that.
        // in sqs there is a concept of visibility timeout, we will use that
    }

    abstract int getBatch();

    abstract Queue<Job> getQueue();

    abstract void sendNotification(Job job);
}

class EmailNotificationConsumer extends NotificationConsumer {
    private final Queue<Job> q;
    private final int batchSize;

    EmailNotificationConsumer(
            int id, int maxJobRetry, Queue<Job> q, int batchSize, JobService jobService) {
        super(jobService, id, maxJobRetry);
        this.q = q;
        this.batchSize = batchSize;
    }

    public void sendNotification(Job job) {
        System.out.printf(
                "Sending Email to user %s : \n%s\n\t%s\n", job.userId, job.title, job.body);
    }

    @Override
    public int getBatch() {
        return batchSize;
    }

    @Override
    public Queue<Job> getQueue() {
        return q;
    }
}

class SmsConsumer extends NotificationConsumer {

    private final Queue<Job> q;
    private final int batchSize;

    SmsConsumer(int id, int maxJobRetry, JobService jobService, Queue<Job> q, int batchSize) {
        super(jobService, id, maxJobRetry);
        this.q = q;
        this.batchSize = batchSize;
    }

    @Override
    public int getBatch() {
        return batchSize;
    }

    @Override
    public Queue<Job> getQueue() {
        return q;
    }

    @Override
    void sendNotification(Job job) {
        System.out.printf("Sending Sms to user %s : \n%s\n%s\n", job.userId, job.title, job.body);
    }
}

class JobService {
    private final JobDao jobDao;
    private final JobLeaseService jobLeaseService;

    JobService(JobDao jobDao, JobLeaseService jobLeaseService) {
        this.jobDao = jobDao;
        this.jobLeaseService = jobLeaseService;
    }

    void save(Job job) {
        jobDao.save(job);
    }

    public List<Job> findExistingJobsForRequest(Integer retryToken) {
        return jobDao.findExistingJobsForRequest(retryToken);
    }

    public void updateJobStatus(Job j, JobStatus jobStatus) {
        jobDao.updateStatus(j, jobStatus);
    }

    /*
    case 1
    consumer 1 takes lease on job 1
    consumer 2 takes lease on same job 1
    it should return false.

    case 2
    consumer 1 takes lease on job 1 and consumer 1 dies
    consumer 2 tries to take lease on same job 1 after its expiry
     */
    public boolean tryToGetLease(Job job, int consumerId) {
        // get lock on JobLease id = job.id, this is a mutation boundary start
        if (jobLeaseService.activeLeasePresent(job.id)) {
            return false;
        }

        try {
            jobLeaseService.createLease(job.id, consumerId);
        } catch (Exception e) {
            releaseLeaseOnJobIfExists(job.id, consumerId);
            return false;
        }

        return true;
        // mutation boundary end
    }

    private void releaseLeaseOnJobIfExists(int id, int consumerId) {
        // get lock on jobLease id = id, mutation boundary start
        Optional<JobLease> jobLeaseOpt = jobLeaseService.find(id);
        if (jobLeaseOpt.isPresent()) {
            if (jobLeaseOpt.get().consumerId == consumerId) {
                System.out.println("releasing lease");
                jobLeaseService.removeLease(id);
            }
        }
        // get lock on job, mutation boundary end
    }

    public void releaseLease(Job job, int consumerId) {
        // get lock on lease and job with id == job.id, this is atomic and transactional boundary
        Optional<JobLease> lease = jobLeaseService.find(job.id);
        if (lease.isPresent() && lease.get().consumerId == consumerId) {
            jobLeaseService.removeLease(job.id);
        }
        // release lock
    }

    public boolean hasActiveLease(Job job) {
        return jobLeaseService.activeLeasePresent(job.id);
    }

    public void incrementAssignmentOfJob(int id) {
        jobDao.incrementAssignmentOfJob(id);
    }

    public void decrementAssignmentOfJob(int id) {
        jobDao.decrementAssignmentOfJob(id);
    }

    public void setNoOfAssignments(int id, int previousNoOfAssignments) {
        jobDao.setNoOfAssignments(id, previousNoOfAssignments);
    }

    public boolean isLeaseOwner(Job job, int consumerId) {
        Optional<JobLease> leaseOpt = jobLeaseService.find(job.id);
        if (leaseOpt.isEmpty()) return false;
        if (leaseOpt.get().expiry.isBefore(Instant.now())) return false;
        return leaseOpt.get().consumerId == consumerId;
    }

    public List<Job> listNonTerminalJobs() {
        return jobDao.listNonTerminalJobs();
    }
}

class JobDao {
    private static final Set<JobStatus> TERMINAL_STATES =
            Set.of(JobStatus.COMPLETED, JobStatus.TERMINALLY_FAILED);
    Map<Integer, Job> jobs = new HashMap<>();

    public void save(Job job) {
        jobs.put(job.id, job);
    }

    public List<Job> findExistingJobsForRequest(Integer retryToken) {
        return jobs.values().stream()
                .filter(j -> j.retryToken == retryToken)
                .collect(Collectors.toList());
    }

    public void updateStatus(Job j, JobStatus jobStatus) {
        if (!jobs.containsKey(j.id)) {
            throw new RuntimeException("invalid job");
        }
        jobs.get(j.id).status = jobStatus;
    }

    Job get(int id) {
        if (!jobs.containsKey(id)) throw new RuntimeException("job doesnt exist");
        return jobs.get(id);
    }

    public void incrementAssignmentOfJob(int id) {
        Job j = get(id);
        j.noOfAssignments = j.noOfAssignments + 1;
    }

    public void decrementAssignmentOfJob(int id) {
        Job j = get(id);
        j.noOfAssignments = j.noOfAssignments - 1;
    }

    public void setNoOfAssignments(int id, int previousNoOfAssignments) {
        Job j = get(id);
        j.noOfAssignments = previousNoOfAssignments;
    }

    public List<Job> listNonTerminalJobs() {
        return jobs.values().stream()
                .filter(j -> !TERMINAL_STATES.contains(j.status))
                .collect(Collectors.toList());
    }
}

@AllArgsConstructor
class JobLease {
    int jobId;
    int consumerId;
    Instant expiry;
}

class JobLeaseService {
    private final JobLeaseDao jobLeaseDao;

    JobLeaseService(JobLeaseDao jobLeaseDao) {
        this.jobLeaseDao = jobLeaseDao;
    }

    JobLease createLease(int jobId, int consumerId) {
        return jobLeaseDao.createLease(jobId, consumerId);
    }

    public boolean activeLeasePresent(int jobId) {
        return jobLeaseDao.activeLeasePresent(jobId);
    }

    public Optional<JobLease> find(int id) {
        return jobLeaseDao.find(id);
    }

    public void removeLease(int id) {
        jobLeaseDao.retire(id);
    }
}

class JobLeaseDao {
    Map<Integer, JobLease> jobLeases = new HashMap<>();

    JobLease createLease(int jobId, int consumerId) {
        if (activeLeasePresent(jobId)) {
            throw new RuntimeException("an active lease is already present");
        }
        JobLease lease = new JobLease(jobId, consumerId, Instant.now().plus(Duration.ofMinutes(5)));
        jobLeases.put(jobId, lease);
        return lease;
    }

    public boolean activeLeasePresent(int jobId) {
        return jobLeases.containsKey(jobId) && jobLeases.get(jobId).expiry.isAfter(Instant.now());
    }

    public Optional<JobLease> find(int id) {
        if (jobLeases.containsKey(id)) return Optional.of(jobLeases.get(id));
        return Optional.empty();
    }

    public void retire(int id) {
        find(id).ifPresent(j -> j.expiry = Instant.now().minus(Duration.ofMinutes(1)));
    }
}
