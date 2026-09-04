package study.lld.designpatterns.lldbootcampweek2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThirdPartySDKIntegration {
    public static void main(String[] args) {

        OpenAnalytics openAnalytics = new OpenAnalyticsImpl();
        Metrics openMetricsClient =
                new LoggingMetricDecorator(
                        new BufferedMetricsDecorator(new OpenAnalyticsAdapter(openAnalytics)));

        GoogleAnalytics googleAnalytics = new GoogleAnalyticsImpl();
        Metrics googleMetricsClient =
                new LoggingMetricDecorator(
                        new BufferedMetricsDecorator(new GoogleAnalyticsAdapter(googleAnalytics)));

        MetricsStrategy multiProvider =
                new MultiProviderStrategy(List.of(openMetricsClient, googleMetricsClient));
        multiProvider.sendEvent("createInstance", "Success");

        MetricsStrategy singleProvider = new SingleProviderStrategy(googleMetricsClient);
        singleProvider.sendEvent("createInstance", "Success");
    }
}

class MetricsManager {
    MetricsStrategy metricsStrategy;

    void setMetricsStrategy(MetricsStrategy metricsStrategy) {
        this.metricsStrategy = metricsStrategy;
    }

    void sendMetric(String name, String data) {
        metricsStrategy.sendEvent(name, data);
    }
}

interface MetricsStrategy {
    void sendEvent(String name, String data);
}

class SingleProviderStrategy implements MetricsStrategy {

    private final Metrics client;

    SingleProviderStrategy(Metrics client) {
        this.client = client;
    }

    @Override
    public void sendEvent(String name, String data) {
        System.out.println("Single Provider");
        client.trackEvent(name, data);
    }
}

class MultiProviderStrategy implements MetricsStrategy {

    private final List<Metrics> clients;

    MultiProviderStrategy(List<Metrics> clients) {
        this.clients = clients;
    }

    @Override
    public void sendEvent(String name, String data) {
        System.out.println("Multi Provider");
        clients.forEach(m -> m.trackEvent(name, data));
    }
}

abstract class MetricDecorator implements Metrics {
    protected Metrics metricsClient;

    MetricDecorator(Metrics metricsClient) {
        this.metricsClient = metricsClient;
    }
}

class LoggingMetricDecorator extends MetricDecorator {

    LoggingMetricDecorator(Metrics metricsClient) {
        super(metricsClient);
    }

    @Override
    public void trackEvent(String name, String data) {
        System.out.println("logging before sending");
        metricsClient.trackEvent(name, data);
        System.out.println("logging after sending");
    }
}

class BufferedMetricsDecorator extends MetricDecorator {

    BufferedMetricsDecorator(Metrics metricsClient) {
        super(metricsClient);
    }

    @Override
    public void trackEvent(String name, String data) {
        System.out.println("Buffering before emit");
        metricsClient.trackEvent(name, data);
    }
}

interface Metrics {
    void trackEvent(String name, String data);
}

class GoogleAnalyticsAdapter implements Metrics {

    private final GoogleAnalytics googleAnalytics;

    GoogleAnalyticsAdapter(GoogleAnalytics openAnalytics) {
        this.googleAnalytics = openAnalytics;
    }

    @Override
    public void trackEvent(String name, String data) {

        HashMap<String, String> dimensions = new HashMap<>();
        dimensions.put(name, data);
        googleAnalytics.sendMetric(name, data, dimensions);
    }
}

interface GoogleAnalytics {
    void sendMetric(String name, String data, Map<String, String> dimensions);
}

class GoogleAnalyticsImpl implements GoogleAnalytics {

    @Override
    public void sendMetric(String name, String data, Map<String, String> dimensions) {
        System.out.println(
                "Emitting metrics with GoogleAnalytics name: "
                        + name
                        + " data: "
                        + data
                        + " dimensions: "
                        + dimensions);
    }
}

class OpenAnalyticsAdapter implements Metrics {

    private final OpenAnalytics openAnalytics;

    OpenAnalyticsAdapter(OpenAnalytics openAnalytics) {
        this.openAnalytics = openAnalytics;
    }

    @Override
    public void trackEvent(String name, String data) {

        HashMap<String, String> dimensions = new HashMap<>();
        dimensions.put(name, data);
        openAnalytics.emitMetric(name, data, dimensions);
    }
}

interface OpenAnalytics {
    void emitMetric(String name, String data, Map<String, String> dimensions);
}

class OpenAnalyticsImpl implements OpenAnalytics {

    @Override
    public void emitMetric(String name, String data, Map<String, String> dimensions) {
        System.out.println(
                "Emitting metrics with OpenAnalytics name: "
                        + name
                        + " data: "
                        + data
                        + " dimensions: "
                        + dimensions);
    }
}
