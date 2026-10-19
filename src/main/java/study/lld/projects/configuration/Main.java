package study.lld.projects.configuration;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

/*
time: 2h
This is mostly an LLD problem. Checkout design of Spring Cloud Config
Learning -
1. In such question we need to specify scope, Does this have to be completely an in memory single node app with same JVM
    Then we can design around observer pattern, otherwise observer pattern doesnt even come into play
    in actual production systems
 */
public class Main {
    public static void main(String[] args) {
        /*
        Configuration management system
        - Its a central api to manage configuration for an application.
        - this will be a central store of configurations,
            which is updated by a CRUD api configuration(spplicationID, config Key, config value)
        - The consumer application will be a listener to the central service any change in the central service
            is being listened in by the consuming application
        - the configuration object in the application is a singleton, whose read and write are made exclusive with a lock
            when the config is updated then a write lock is held, and then reads are allowed after updated.
        - this object will be a listener to the main configuration service. Either the app can poll for a configuration
            change every X time. Or the change in central API will be pushed to the application. I prefer push model
            which eliminates polling from application side.
        - Upon an update in config, we will call an api on the application to update its config.
        - The config wil be a map -> applicationId,Map<String,String>
        - upon update/create/delete of config, we will call an updated api on the application, which will in turn
            call a get api on the config service. to get the latest config.
            Or we can push the latest config in the updated call. I prefer pushing the config for now.
        -  The application copy will be  a persisted copy, anytime a config is updated we need
            to update the in memory copy. In a distributed system, with multiple nodes of the application.
            We need to make sure all nodes update their config.  Probably lease holder will persist the config
            and all nodes will see the updated config by doing a GET call.
        - The config version should be monotonically increasing,
            No node should try to update its local copy with an older version


         Configuration
         - id
         - Map<String,String>
         - int version

         Application
         - id
         - name
         - endpoint
         - List<Configuration>

         ConfigurationService
         - createConfiguration(Map<String,String> config)
         - readConfiguration(id)
         - addConfigurationToApplication(appId,configId)
         - updateConfiguration(id,Map<String,String> config)

         ApplicationService
         - updateConfigOnApplication(applicationId,Configuration)
         - getConfigurationsOfApplication(id)
         - getApplication(id)
         - addConfigurationToApplication(appId,configId)
         - notifyApplication(id)
         */
    }
}

class Configuration {
    private static AtomicInteger counter = new AtomicInteger(0);
    int id;
    int version;
    private Map<String, String> configuration;

    Configuration(int id, int version, Map<String, String> configuration) {
        this.id = counter.getAndIncrement();
        this.configuration = configuration;
        this.version = 1;
    }

    public synchronized void updateConfig(String key, String value) {
        configuration.put(key, value);
        version++;
    }

    public Map<String, String> getConfiguration() {
        return Collections.unmodifiableMap(configuration);
    }
}

@AllArgsConstructor
class Application {
    int id;
    String name;
    String endpoint;
    Set<Integer> configurationIds;
}

class ConfigurationService {
    ConfigurationDao configurationDao;
    ApplicationService applicationService;

    Configuration createConfiguration(Map<String, String> config) {
        return configurationDao.create(config);
    }

    Configuration getConfiguration(int id) {
        return configurationDao.get(id);
    }

    void addConfigurationToApplication(int configId, int applicationId) {
        applicationService.updateConfigOnApplication(applicationId, configId);
    }

    void updateConfiguration(int id, String key, String value) {
        configurationDao.updateConfig(id, key, value);
        List<Application> apps = applicationService.getAllApplicationsForConfiguration(id);
        apps.forEach(app -> applicationService.notify(app.id));
    }
}

interface ConfigurationDao {

    Configuration create(Map<String, String> config);

    Configuration get(int id);

    void updateConfig(int id, String key, String value);
}
/*
        - updateConfigOnApplication(applicationId,Configuration)
        - getConfigurationsOfApplication(id)
        - getApplication(id)
        - addConfigurationToApplication(appId,configId)
        - notifyApplication(id)
*/
class ApplicationService {
    ApplicationDao applicationDao;
    NotificationService notificationService;

    void updateConfigOnApplication(int applicationId, int configId) {
        applicationDao.updateConfigToApplication(applicationId, configId);
        notify(applicationId);
    }

    void notify(int applicationId) {
        // This has to be a reliable signal. We will put this notification in a durable queue
        // send the notification to the application and wait for an ack, if there is no ack, then we
        // will
        // retry the notification push
        notificationService.notify(applicationId);
    }

    Set<Integer> getApplicationConfigurations(int id) {
        return getApplication(id).configurationIds;
    }

    Application getApplication(int id) {
        return applicationDao.get(id);
    }

    public List<Application> getAllApplicationsForConfiguration(int configId) {
        return applicationDao.list().stream()
                .filter(a -> a.configurationIds.contains(configId))
                .collect(Collectors.toList());
    }
}

class NotificationService {
    Queue<Integer> outbox; // This can be a blocking queue in memory to avoid busy wait

    public void notify(int applicationId) {
        outbox.offer(applicationId);
    }

    public void consumer() {
        if (outbox.isEmpty()) {
            // sleep
        }
        int appId = outbox.poll();
    }
}

interface ApplicationDao {

    Application get(int id);

    void updateConfigToApplication(int applicationId, int configId);

    List<Application> list();
}

class ApplicationApi {
    int applicationId;
    ApplicationService applicationService;
    Set<Configuration>
            currentConfiguration; // this will be a persisted copy so that all app nodes get the
    // update
    // This will also have a lock so that while the config is being updated no readers get a stale
    // read.

    void updateConfig(Set<Configuration> configurations) {
        for (Configuration newConfig : configurations) {
            Optional<Configuration> existing =
                    currentConfiguration.stream().filter(c -> c.id == newConfig.id).findFirst();
            if (existing.isPresent()) {
                if (existing.get().version >= newConfig.version) {
                    System.out.printf("config " + newConfig.id + " is already updated");
                    continue;
                }
            }
            configurations.add(newConfig);
        }
    }
}
