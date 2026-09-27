package study.lld.projects.notification;

import static java.lang.Thread.sleep;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.AllArgsConstructor;

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
        NotificationConsumer emailConsumer = new EmailNotificationConsumer(emailQueue, 5);

        ArrayDeque<Job> smsQueue = new ArrayDeque<>();
        Channel smslChannel = new SmsChannel(smsQueue);
        NotificationConsumer smsConsumer = new SmsConsumer(smsQueue, 5);

        JobService jobService = new JobService(new JobDao());
        NotificationSystemService notificationSystemService =
                new NotificationSystemService(jobService);
        notificationSystemService.addChannel(emailChannel);
        notificationSystemService.addChannel(smslChannel);

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleWithFixedDelay(emailConsumer, 1L, 3L, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(smsConsumer, 1L, 3L, TimeUnit.SECONDS);

        User a = new User(1, "Harshit", "address", "harshit@gmail.com", "1234");
        notificationSystemService.submitNotificationJob(a, "Welcome", "Hello user");
        notificationSystemService.submitNotificationJob(a, "Welcome1", "Hello user");
        notificationSystemService.submitNotificationJob(a, "Welcome2", "Hello user");
        notificationSystemService.submitNotificationJob(a, "Welcome3", "Hello user");
        notificationSystemService.submitNotificationJob(a, "Welcome4", "Hello user");
        notificationSystemService.submitNotificationJob(a, "Welcome5", "Hello user");
        notificationSystemService.submitNotificationJob(a, "Welcome6", "Hello user");

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

class Job {
    static final AtomicInteger counter = new AtomicInteger(0);
    int id;
    User user;
    String title;
    String body;

    public Job(User user, String title, String body) {
        this.id = counter.getAndIncrement();
        this.user = user;
        this.title = title;
        this.body = body;
    }
}

class NotificationSystemService {
    private final JobService jobService;
    List<Channel> channels;

    NotificationSystemService(JobService jobService) {
        this.jobService = jobService;
        channels = new ArrayList<>();
        ;
    }

    void addChannel(Channel channel) {
        channels.add(channel);
    }

    void submitNotificationJob(User user, String title, String body) {
        Job job = jobService.create(user, title, body);
        channels.forEach(c -> c.submitNotificationJob(job));
    }

    void submitNotificationJobToChannel(User user, Channel channel, String title, String body) {}
}

interface Channel {
    void submitNotificationJob(Job job);
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
}

interface NotificationConsumer extends Runnable {
    void consumeJob();

    public default void run() {
        System.out.println("consumer batch started on " + Thread.currentThread().getName());
        for (int i = 0; i < getBatch(); i++) {
            if (getQueue().isEmpty()) {
                System.out.println("no jobs in queue");
                break;
            }
            consumeJob();
        }
        System.out.println("consumer batch completed " + Thread.currentThread().getName());
    }

    int getBatch();

    Queue<Job> getQueue();
}

class EmailNotificationConsumer implements NotificationConsumer {

    private final Queue<Job> q;
    private final int batchSize;

    EmailNotificationConsumer(Queue<Job> q, int batchSize) {
        this.q = q;
        this.batchSize = batchSize;
    }

    @Override
    public void consumeJob() {
        Job job = getQueue().poll();
        System.out.printf(
                "Sending Email to user %s : \n%s\n\t%s\n", job.user.name, job.title, job.body);
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

class SmsConsumer implements NotificationConsumer {

    private final Queue<Job> q;
    private final int batchSize;

    SmsConsumer(Queue<Job> q, int batchSize) {
        this.q = q;
        this.batchSize = batchSize;
    }

    @Override
    public void consumeJob() {
        Job job = getQueue().poll();
        System.out.printf(
                "Sending Sms to user %s : \n%s\n%s\n", job.user.name, job.title, job.body);
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

class JobService {
    private final JobDao jobDao;

    JobService(JobDao jobDao) {
        this.jobDao = jobDao;
    }

    Job create(User user, String title, String body) {
        Job job = new Job(user, title, body);
        jobDao.save(job);
        return job;
    }
}

class JobDao {
    Map<Integer, Job> jobs = new HashMap<>();

    public void save(Job job) {
        jobs.put(job.id, job);
    }
}
