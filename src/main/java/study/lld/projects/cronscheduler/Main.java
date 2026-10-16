package study.lld.projects.cronscheduler;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*
design time: 30m
coding time: 60 mins
Learnings -
1. I did not separate jobDefinition with JobRun. Because of this I couldnt model atleast once execution of every job scheduled run.
    Say, our service is down for 5 minytes and a job needed to run every minute then we need to have 5 runs scheduled of the same job
2. JobRun at a scheduled time should be unique
3. Learning to have Lease and Job state work together.
 */
public class Main {
    public static void main(String[] args) {
        /*
        CRON Scheduler
        - About 100,000 job definitions.
        - Up to 10,000 job runs executing at the same time.
        - Every job is a cron job. There is no "run now" request: a job runs only when its schedule fires.
        - Execution is at least once: every scheduled run must eventually execute.
            A run may occasionally execute more than once, but it must never be silently skipped.

        A user will submit a job and enter a cron schedule. The service will accept the job and it will
            setup a scheduled run for the job.
        We will have a JobDefinition and JobRun

        How to trigger scheduled runs ?
        1. Scheduler can scan all job every second and then run the ones which are due to be run. Then it
            computes the next run time of the job and updates the job.
        2. We use ScheduledExecutorService, with 10000 threads. No computer has 10000 threads so we partition the jobs
            and distribute it into JVMs and we submit the jobs and their schedule to the scheduled executor service.
            It will take care of running the jobs

        I will select number 1 trigger strategy
        - I will have a jobDefinition table which is sorted with nextRun time.
        - We have a scheduled run which scans the JobDefinition table and creates eligible jobRuns in another table
        - JobRuns are persisted. And their
        - Producer scans the JobRun table and puts them in a Queue with status RUNNABLE, it also picks up running jobs with expired lease and failed jobs.
        - We have a consumer which polls the queue for job, it checks their status is RUNNABLE, tries to get a lease for the job, and marks their
            status as running with a write lock
        - After completion, we mark the status of JobRun as RUN_COMPLETED, we also release the lease.
        - On failure of the job, we mark the job as FAILED and we release the lease.
        - jobDefinitionId and scheduleTimeId should be unique. So that multiple runs of the same scedhule are not created.
        - At a interval of a minute we do a repair scan of the jobRun where we scan all eligible jobRuns and put them on the queue again.
            elgible are - INIT, RUNNING with expired leases, FAILED

        What if job never finishes?
        - I will have a lease job + consumer which will be held for X minutes. If the lease is expired, the job is ready to be picked up again.

        JobDefinition
        - id
        - Runnable/Task
        - CronSchedule
        - NextRun
        - nextScheduledRun
        - startTime
        - setNextRunTIme(): this will compute the next run time. This will always be the next slot based on lastScheduledTime

        JobRun
        - id
        - jobDefinitionId
        - scheduledTime
        - status: INIT,RUNNABLE/INQUEUE, RUNNING, FAILED, RUN_COMPLETED
        - consumerId (lease)
        - expiryTime (lease)
        - attempt

        Lease
        - id
        - jobRunId
        - consumerId
        - expiryTime

        Consumer
        - id
        - currentJob: JobRun
        -
         */
    }
}
class CronSchedulerService{
    JobDefinitionService jobDefinitionService;
    void createCronJob(Runnable runnable, Instant startTime,Duration repeatInterval){
        jobDefinitionService.createJobDefinition(runnable,startTime,repeatInterval);
    }
}
interface JobDefinitionService{

    public void createJobDefinition(Runnable runnable, Instant startTime, Duration repeatInterval);

    List<JobDefinition> listJobDefinitions();

    void updateNextScedhuledRun(JobDefinition jd);

    JobDefinition get(int jobDefinitionId);
}

// This should be a singleton
class JobRunManager{
    private final ScheduledExecutorService createEligibleRunsManager;
    private final ScheduledExecutorService requeueEligibleRunsManager;
    private final JobDefinitionService jobDefinitionService;
    private final JobRunService jobRunService;
    private final JobQueue jobQueue;
    private boolean isRunning;

    JobRunManager(JobDefinitionService jobDefinitionService, JobRunService jobRunService, JobQueue jobQueue) {
        this.createEligibleRunsManager = Executors.newSingleThreadScheduledExecutor();
        this.requeueEligibleRunsManager = Executors.newSingleThreadScheduledExecutor();
        this.jobDefinitionService = jobDefinitionService;
        this.jobRunService = jobRunService;
        this.jobQueue = jobQueue;
        this.isRunning = false;
    }

    void start(){
        isRunning=true;
        createEligibleRunsManager.scheduleAtFixedRate(()-> createEligibleRuns(),1,5, TimeUnit.SECONDS);
        requeueEligibleRunsManager.scheduleAtFixedRate(()-> requeueEligibleRuns(),1,2, TimeUnit.MINUTES);
    }

    private void requeueEligibleRuns() {
        // this needs to be paginated
        List<JobRun> runs = jobRunService.listRuns();
        for(JobRun run: runs){
            if(run.status==JobStatus.FAILED){
                if(!jobRunService.exceededRetryCount(run)){
                    jobQueue.enqueue(run);
                    jobRunService.updateStatusOfRun(run,JobStatus.RUNNABLE);
                }else {
                    jobRunService.deadLetter(run);
                }
            } else if (run.status==JobStatus.RUNNING) {
                if(!jobRunService.hasActiveLease(run)){
                    jobQueue.enqueue(run);
                    jobRunService.updateStatusOfRun(run,JobStatus.RUNNABLE);
                }
            } else if (run.status==JobStatus.INIT) {
                jobQueue.enqueue(run);
                jobRunService.updateStatusOfRun(run,JobStatus.RUNNABLE);
            } else if (run.status==JobStatus.RUNNABLE) {
                if(!jobQueue.contains(run))
                    jobQueue.enqueue(run);
            }
        }
    }

    void stop(){
        isRunning=false;
        createEligibleRunsManager.shutdownNow();
        requeueEligibleRunsManager.shutdownNow();
    }

    private void createEligibleRuns() {
        // this should have pagination
        List<JobDefinition> jobDefinitions = jobDefinitionService.listJobDefinitions();
        for(JobDefinition jd : jobDefinitions){
            if(isEligible(jd)){
                // get job Definition run creator lock
                // start mutation boundary. Make it atomic with transaction
                if(jobRunService.findRun(jd.id,jd.nextScheduledRun).isPresent()){
                    System.out.println("job already exists");
                    continue;
                }
                JobRun jobRun = jobRunService.createRun(jd);
                jobDefinitionService.updateNextScedhuledRun(jd);
                jobQueue.enqueue(jobRun);
                // end mutation boundary
                // release job Definition run creator lock
            }
        }
    }

    private boolean isEligible(JobDefinition jd) {
        return !jd.nextScheduledRun.isAfter(Instant.now());
    }
}
// this will be a blocking queue
interface JobQueue{
    void enqueue(JobRun jobRun);
    JobRun dequeue();

    boolean contains(JobRun run);
}
class JobRunnerConsumer implements Runnable{
    int id;
    JobRun currentJob;
    JobQueue jobQueue;
    JobRunService jobRunService;
    JobDefinitionService jobDefinitionService;
    boolean isRunning;

    @Override
    public void run() {
        // consumer will poll the queue every X seconds if in a distributed environment
        // otherwise we can have a blocking queue and consumer can poll continuously;
        while (isRunning){
            JobRun jobRun = jobQueue.dequeue();
            if(jobRun.status==JobStatus.COMPLETED){
                System.out.println("invalid state");
                continue;
            }
            if(jobRun.status==JobStatus.RUNNING && jobRunService.hasActiveLease(jobRun)){
                System.out.println("job is already being worked on");
                continue;
            }
            if(jobRun.status==JobStatus.FAILED){
                System.out.println("job is failed, wait for requeue");
                continue;
            }

            try{
                // transaction start
                if(jobRunService.get(jobRun.id).status!=JobStatus.RUNNABLE){
                    System.out.println("invalid state");
                    continue;
                }
                boolean leaseAcquired = jobRunService.tryToGetLease(jobRun,id); // acts like a job write lock
                if(!leaseAcquired){
                    System.out.println("lease not held");
                    continue;
                }

                currentJob = jobRun;
                jobRunService.updateStatusOfRun(jobRun,JobStatus.RUNNING);
                jobRunService.incrementNoOfAssignments(jobRun);
                // transaction end

                jobDefinitionService.get(jobRun.jobDefinitionId).task.run();

                jobRunService.updateStatusOfRun(jobRun,JobStatus.COMPLETED); // method is transactional
            }catch(Exception e){
                System.out.println("unexpected error occured");
                jobRunService.updateStatusOfRun(jobRun,JobStatus.FAILED);
            }finally {
                jobRunService.releaseLeaseIfHeldByConsumer(jobRun,id);
            }

        }

    }
}
class JobDefinition{
    int id;
    Runnable task;
    Instant startTime;
    Duration interval;
    Instant nextScheduledRun;
}
interface JobRunService{

    JobRun createRun(JobDefinition jd);

    Optional<JobRun> findRun(int jobDefinitionId, Instant nextScheduledRun);

    boolean tryToGetLease(JobRun jobRun, int consumerId); // atomic transaction

    boolean hasActiveLease(JobRun jobRun);

    boolean exceededRetryCount(JobRun jobRun);

    void updateStatusOfRun(JobRun jobRun, JobStatus jobStatus);

    void incrementNoOfAssignments(JobRun jobRun);

    void releaseLeaseIfHeldByConsumer(JobRun jobRun, int consumerId);

    List<JobRun> listRuns();

    void deadLetter(JobRun run);

    JobRun get(int id);
}
class JobRun{
    int id;
    Instant scheduledRunTime;
    int jobDefinitionId;
    JobStatus status;
    int consumerId;
    Instant leaseExpiryTime;
    int noOfAssignments;
}
enum JobStatus{
    INIT,RUNNABLE,RUNNING,FAILED,COMPLETED
}
