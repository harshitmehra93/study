package study.lld.designpatterns.lldbootcampweek2;

import java.util.ArrayList;
import java.util.List;

public class JobProcessingService {
    public static void main(String[] args) {

        Job e1 = new SendEmail();
        Job e2 = new SendEmail();
        Job e3 = new SendEmail();

        Job gr1 = new GenerateReport();
        Job gr2 = new GenerateReport();
        Job gr3 = new GenerateReport();

        Job ct1 = new CleanTempFiles();
        Job ct2 = new CleanTempFiles();
        Job ct3 = new CleanTempFiles();

        Job ra1 = new RecalculateAnalytics();
        Job ra2 = new RecalculateAnalytics();
        Job ra3 = new RecalculateAnalytics();

        List<Job> queue =
                new ArrayList<>(List.of(e1, e2, e3, gr1, gr2, gr3, ct1, ct2, ct3, ra1, ra2, ra3));

        queue.forEach(Job::execute);
    }
}

interface Job {
    void execute();
}

class SendEmail implements Job {

    @Override
    public void execute() {
        System.out.println("Sending Email");
    }
}

class GenerateReport implements Job {

    @Override
    public void execute() {
        System.out.println("Generating report");
    }
}

class CleanTempFiles implements Job {

    @Override
    public void execute() {
        System.out.println("Cleaning temp Files");
    }
}

class RecalculateAnalytics implements Job {

    @Override
    public void execute() {
        System.out.println("Recalculating Analysis");
    }
}
