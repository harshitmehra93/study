package study.lld.projects.jobworker;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.Objects.isNull;

/*
Time: 52min
- leases
- blocking queues
- retry counter of job

Learnings
- failed
 */
public class Main {
    public static void main(String[] args) {
        /*
        Job worker
        - User can submit any type of job The job is put on a durable queue.
        - workers pick up the job execute the job. Once the job is complete its marked as complete and removed from queue.
        - once job is complete user can view the result in a durable storage or with a Future if its a shared JVM
            between producer and consumer. We will use a void job for this implementation.
        - workers should not pick up same message twice from the queue so there is no duplicate processing.
            For this the first step of the worker should be to get the job and atomically read its status, and if its
            in ACCEPTED state then change the status to IN PROGRESS, if it has any other state then its already
            picked up by someone else.
        - If the job is not completed within X amount of time, then the job is put in FAILED state. This can be implemented
            with a lease. A worker should get a lease on the job. And it should continuously keep checking the time
            of processing. If the time breached the lease time, then the job is marked as FAILED. And its put back
            to retry.
        - We will have a job + worker lease for X amount of time, which we will store durably.
        - A failed job should have a finally / catch step which tries to do some rollback of the work thats partially
            done by the job.
        - The producer and consumer of the queue should be non blocking if both are on the same JVM. Non blocking in the sense,
            when producer/user adds a job to the queue, then one of the consume ris notified to start consuming. At other times,
            the consumers are sleeping, therefore they are not in busy wait.
        - The enqueue and dequeue on the queue should be done on a lock.
        - In a distributed scenario, consumer will wake up and poll the queue after a specified amount of time.
            We will consider in-memory non blocking queue.

        Job
        - id
        - status: ACCEPTED, COMPLETED,FAILED
        - execute(): void
        - rollback(): void

        BlockingQueue
        - enqueue(Job)
        - dequeue(): Job

        Worker
        - id
        - currentLease

        Lease
        - id
        - jobId
        - workerId
         */
    }
}
abstract class Job{
    int id;
    AtomicInteger noOfAssignments = new AtomicInteger(0);
    final int MAX_ASSIGNMENTS = 10;
    abstract void run();
    abstract void rollback();
    JobStatus status;

}
enum JobStatus{
    ACCEPTED,COMPLETED,FAILED,IN_PROGRESS
}
class Worker implements Runnable{
    private final BlockingQueue queue;
    private final LeaseService leaseService;
    private final JobService jobService;
    private final AtomicInteger counter = new AtomicInteger(0);
    int id;
    boolean isRunning;
    ScheduledExecutorService heartbeat;

    Worker(BlockingQueue queue, LeaseService leaseService, JobService jobService) {
        this.queue = queue;
        this.leaseService = leaseService;
        this.jobService = jobService;
        this.id=counter.getAndIncrement();
        isRunning=true;
        heartbeat= Executors.newSingleThreadScheduledExecutor();
        Runnable renewLeaseJob = ()->{
            Lease lease = leaseService.getActiveLeaseForWorker(id);
            if(!isRunning){
                return;
            }
            if(isNull(lease)){
                System.out.println("no active lease");
                return;
            }
            leaseService.renewLease(lease);
        };
        heartbeat.scheduleWithFixedDelay(renewLeaseJob,5,5, TimeUnit.SECONDS);
    }

    public void run() {
        isRunning=true;
        while (isRunning){
            System.out.println("polling for work "+id);
            Job job = queue.dequeue();
            System.out.println("work received"+id);
            if(job.status==JobStatus.COMPLETED || job.status==JobStatus.IN_PROGRESS){
                throw new RuntimeException("invalid job state");
            }
            if(job.noOfAssignments.get()==job.MAX_ASSIGNMENTS){
                throw new RuntimeException("max no of assignments reached for job");
            }
            // transaction boundary
            if(leaseService.hasExistingLeaseForJob(job.id)){
                Lease lease = leaseService.getLeaseForJob(job.id);
                if(lease.expiryTime.isAfter(Instant.now())){
                    throw new RuntimeException("active lease already exists for job");
                }
            }
            leaseService.createLeaseForJob(id,job.id);
            // transaction end


            try{
                // transaction start
                leaseService.validateLease(id,job.id);
                System.out.println("Executing job "+job.id+" in worker "+id);
                jobService.updateStatusOfjob(job,JobStatus.IN_PROGRESS);
                jobService.incrementNoOfAssignmentOfJob(job);
                // transaction end

                leaseService.validateLease(id,job.id);
                job.run();

                // transaction start
                leaseService.validateLease(id,job.id);
                System.out.println("Completed job "+job.id+" in worker "+id);
                jobService.updateStatusOfjob(job,JobStatus.COMPLETED);
                // transaction end
            }catch (Exception e){
                System.out.println("Failed job "+job.id+" in worker "+id);
                job.rollback();
                jobService.updateStatusOfjob(job,JobStatus.FAILED);
                queue.enqueue(job); // add back in queue for retry
            }finally {
                leaseService.endLease(id);
            }
        }
    }
    void stop(){
        isRunning=false;
    }
}
interface LeaseService{

    boolean hasExistingLeaseForJob(int jobId);

    Lease getLeaseForJob(int jobId);

    void createLeaseForJob(int workerId, int jobId);

    void validateLease(int workerId, int jobId);

    Lease getActiveLeaseForWorker(int id);

    void endLease(int id);

    void renewLease(Lease lease);
}
class Lease{
    int id;
    int jobId;
    int workerId;
    Instant expiryTime;
}
class BlockingQueue{
    Queue<Job> queue = new ArrayDeque<>();
    private final ReentrantLock LOCK = new ReentrantLock();
    private final Condition notEmpty = LOCK.newCondition();
    public void enqueue(Job job){
        LOCK.lock();
        try {
            queue.offer(job);
            notEmpty.signal();
        }finally {
            LOCK.unlock();
        }
    }
    public Job dequeue(){
        LOCK.lock();
        try {
            while (queue.isEmpty()){
                notEmpty.await();
            }
            return queue.poll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            LOCK.unlock();
        }
    }
}
class JobService{
    private final BlockingQueue queue;

    JobService(BlockingQueue queue) {
        this.queue = queue;
    }

    void submitJob(Job job){
        queue.enqueue(job);
    }

    public void updateStatusOfjob(Job job, JobStatus jobStatus) {
    }

    public void incrementNoOfAssignmentOfJob(Job job) {
    }
}
class WorkerService{
    List<Worker> workers = new ArrayList<>();
    private final BlockingQueue queue;
    private final LeaseService leaseService;
    private final JobService jobService;

    WorkerService(BlockingQueue queue, LeaseService leaseService, JobService jobService) {
        this.queue = queue;
        this.leaseService = leaseService;
        this.jobService = jobService;
    }

    void registerNewWorker(){
        System.out.println("Registered new worker");
        Worker worker = new Worker(queue, leaseService, jobService);
        workers.add(worker);
        worker.run();
    }
}
