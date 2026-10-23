package study.lld.projects.notification;

import static java.lang.Thread.sleep;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;

/*
- time: 45m
- Learnings
- Did not handle/mention case where an email job failed, then how will we provide retries ?
    we need a channel deliveryDao which track status of job for deduplication
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
        ArrayDeque<Job> emailQueue = new ArrayDeque<>();
        Channel emailChannel = new EmailChannel(emailQueue);
        NotificationConsumer emailConsumer =
                new EmailNotificationConsumer(id, emailQueue, 5, jobService);

        ArrayDeque<Job> smsQueue = new ArrayDeque<>();
        Channel smslChannel = new SmsChannel(smsQueue);
        NotificationConsumer smsConsumer = new SmsConsumer(smsQueue, 5);

        JobService jobService = new JobService(new JobDao(), jobLeaseService);
        NotificationSystemService notificationSystemService =
                new NotificationSystemService(jobService, userService, notificationRequestService);
        notificationSystemService.addChannel(emailChannel);
        notificationSystemService.addChannel(smslChannel);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleWithFixedDelay(emailConsumer, 1L, 3L, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(smsConsumer, 1L, 3L, TimeUnit.SECONDS);

        User a = new User(1, "Harshit", "address", "harshit@gmail.com", "1234");
        notificationSystemService.createNotification(a, "Welcome", "Hello user");
        notificationSystemService.createNotification(a, "Welcome1", "Hello user");
        notificationSystemService.createNotification(a, "Welcome2", "Hello user");
        notificationSystemService.createNotification(a, "Welcome3", "Hello user");
        notificationSystemService.createNotification(a, "Welcome4", "Hello user");
        notificationSystemService.createNotification(a, "Welcome5", "Hello user");
        notificationSystemService.createNotification(a, "Welcome6", "Hello user");

        //        emailConsumer.stop();
        sleep(10_000L);
        scheduledExecutorService.shutdownNow();
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

    NotificationSystemService(
            JobService jobService,
            UserService userService,
            NotificationRequestService notificationRequestService) {
        this.jobService = jobService;
        this.userService = userService;
        this.notificationRequestService = notificationRequestService;
        channels = new HashMap<>();
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

    private void enqueueJobs(List<Job> jobsToBeCreated) {
        jobsToBeCreated.forEach(
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
        if (!supportedChannels.contains(
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

    void consumeJob(Job job) {
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
            System.out.println("job has an active lease");
            return;
        }

        if (job.status == JobStatus.FAILED && job.noOfAssignments >= MAX_JOB_RETRY) {
            System.out.println("max job retry reached");
            try {
                jobService.updateJobStatus(job, JobStatus.TERMINALLY_FAILED);
            } catch (Exception e) {
                System.out.println("unexpected error while changng status");
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

            // All queued and failed jobs can retry
            int previousNoOfAssignments = job.noOfAssignments;
            try {
                jobService.incrementAssignmentOfJob(job.id);
            } catch (Exception e) {
                jobService.setNoOfAssignments(job.id, previousNoOfAssignments);
                System.out.println("increment failed");
                return;
            }

            jobService.updateJobStatus(job, JobStatus.LEASED);
            sendNotification(job);
            jobService.updateJobStatus(job, JobStatus.COMPLETED);
            // mutation boundary end; release lock on lease with id = job.id
        } catch (Exception e) {
            jobService.updateJobStatus(job, JobStatus.FAILED);
            System.out.println("Unexpected failure");
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
            consumeJob(job);
        }
        System.out.println("consumer batch completed " + Thread.currentThread().getName());
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

    public boolean tryToGetLease(Job job, int consumerId) {
        // get lock on JobLease id = job.id, this is a mutation boundary start
        if (jobLeaseService.activeLeasePresent(job.id)) {
            return false;
        }
        Optional<JobLease> leaseOpt = jobLeaseService.find(job.id);
        if (leaseOpt.isPresent() && leaseOpt.get().consumerId != consumerId) {
            System.out.println("lease is held by someone else");
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

    public void releaseLease(Job job, int consumerId) {}

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
}

class JobDao {
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
