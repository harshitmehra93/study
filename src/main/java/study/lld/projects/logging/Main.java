package study.lld.projects.logging;

import java.io.*;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;

/*
time: 2h
Learnings
- I am not implementing solutions. I did not know file handling syntax by heart.
    Needed a refresher or guidance to complete this
- failures while doing destination writes are not handled
-
 */
public class Main {
    public static void main(String[] args) {
        /*
        Logging framework
        - ability to log in different logging levels info,debug,error
        - ability to have different destinations of the logs output file, console, remote
        - different loggers can have different destinations
        - different loggers can have different logging formats

        - a logger can have multiple destinations every time a log message comes in, its written to all destinations.
        - the speed of writing to a destination may vary from the speed of incoming logs.
            So, we need to have an outbox/buffer for a logger which gets appended when logs come in
            and then its written to the specified destination.
        - probably a better design is a inbox at the destination class. Which get written to the destination,
            a logger can just synchronously write to destination. And the destination will buffer the inbox and
            asynchronously write to its destination
        - logger writing to destination should be thread safe ? Probably yes. There should be a write lock
            on destinations inbox. So that multiple writers. Or probably the inbox can be a FIFO queue which can be written
            by multiple writers together.
        - The destination should have some sort of timestamp based indexing, or it should be a time series database to
            show the logs to users efficiently.


        Logger
        - id
        - name
        - List<LogDestination>
        - minimumLevel
        - info()
        - error()
        - debug()

        LogMessage
        - Instant creationTime
        - String message
        - logLevel
        - Map<String, String> dimensions | out of scope

        LogDestination
        + write(LogMessage)
        - each logDestination will have an encoder
         */
        StringLogEventFormatter stringLogEventFormatter = new StringLogEventFormatter();
        LogDestination consoleDestination = new ConsoleLogDestination(stringLogEventFormatter);
        LogDestination fileDestination =
                new FileLogDestination("application.log", stringLogEventFormatter);

        LoggerDao loggerDao = new LoggerDao();
        LoggerService loggerService =
                new LoggerService(consoleDestination, LogLevel.INFO, loggerDao);

        Logger billing = loggerService.getLogger("Billing");
        billing.addDestination(fileDestination);
        Logger inventory = loggerService.getLogger("Inventory");
        inventory.addDestination(fileDestination);

        billing.info("got bill 1");
        inventory.info("inventory 1");
        billing.warn("got bill 2");
        inventory.error("inventory 2");
        billing.error("got bill 3");
        inventory.warn("inventory 3");
    }
}
// This will be a singleton
class LoggerService {
    private final LogDestination defaultDestination;
    private final LogLevel defaultLogLevel;
    private final LoggerDao loggerDao;

    LoggerService(
            LogDestination defaultDestination, LogLevel defaultLogLevel, LoggerDao loggerDao) {
        this.defaultDestination = defaultDestination;
        this.defaultLogLevel = defaultLogLevel;
        this.loggerDao = loggerDao;
    }

    Logger getLogger(String name) {
        if (loggerDao.contains(name)) {
            return loggerDao.get(name);
        }
        Logger logger = new Logger(name, defaultLogLevel, defaultDestination);
        loggerDao.save(logger);
        return logger;
    }

    void addDestination(String loggerName, LogDestination destination) {
        Logger logger = loggerDao.get(loggerName);
        logger.addDestination(destination);
    }
}

class LoggerDao {
    Map<String, Logger> loggers = new ConcurrentHashMap<>();

    Logger get(String loggerName) {
        if (!loggers.containsKey(loggerName)) throw new RuntimeException("logger doesnt exist");
        return loggers.get(loggerName);
    }

    public void save(Logger logger) {
        loggers.put(logger.getName(), logger);
    }

    public boolean contains(String name) {
        return loggers.containsKey(name);
    }
}

class Logger {
    private static AtomicInteger counter = new AtomicInteger(0);
    int id;
    @Getter private final String name;
    List<LogDestination> destinations;
    private final LogLevel minimumLogLevel;

    Logger(String name, LogLevel minimumLogLevel, LogDestination defaultDestination) {
        this.id = counter.getAndIncrement();
        this.name = name;
        this.minimumLogLevel = minimumLogLevel;
        destinations = new ArrayList<>();
        destinations.add(defaultDestination);
    }

    public void info(String message) {
        log(message, LogLevel.INFO);
    }

    public void warn(String message) {
        log(message, LogLevel.WARN);
    }

    public void error(String message) {
        log(message, LogLevel.ERROR);
    }

    private void log(String message, LogLevel level) {
        if (level.level < minimumLogLevel.level) {
            return;
        }
        writeToAllDestinations(new LogEvent(message, level, name));
    }

    public void writeToAllDestinations(LogEvent logEvent) {
        destinations.forEach(d -> d.write(logEvent));
    }

    public void addDestination(LogDestination destination) {
        destinations.add(destination);
    }
}

enum LogLevel {
    TRACE(10),
    DEBUG(20),
    INFO(30),
    WARN(40),
    ERROR(50);
    final int level;

    LogLevel(int level) {
        this.level = level;
    }
}

class LogEvent {
    String message;
    String loggerName;
    Instant time;
    LogLevel level;

    LogEvent(String message, LogLevel level, String loggerName) {
        this.message = message;
        this.level = level;
        this.loggerName = loggerName;
        this.time = Instant.now();
    }
}

interface LogDestination {
    void write(LogEvent log);
}

class ConsoleLogDestination implements LogDestination {
    private final String DESTINATION = "ConsoleLog";
    private final StringLogEventFormatter logEventFormatter;

    ConsoleLogDestination(StringLogEventFormatter logEventFormatter) {
        this.logEventFormatter = logEventFormatter;
    }

    @Override
    public void write(LogEvent log) {
        System.out.println(logEventFormatter.format(log, DESTINATION));
    }
}

class FileLogDestination implements LogDestination {
    public static final int MAX_FILE_SIZE_IN_BYTES = 1024;
    private final String DESTINATION = "FileLog";
    private String fileName;
    private final StringLogEventFormatter logEventFormatter;

    FileLogDestination(String fileName, StringLogEventFormatter logEventFormatter) {
        this.fileName = fileName;
        this.logEventFormatter = logEventFormatter;
    }

    @Override
    public synchronized void write(LogEvent log) {
        try (BufferedWriter outputStream = new BufferedWriter(new FileWriter(fileName, true)); ) {
            outputStream.append(logEventFormatter.format(log, DESTINATION));
            outputStream.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        rotateFileIfRequired();
    }

    private void rotateFileIfRequired() {
        File file = new File(fileName);
        if (file.length() > MAX_FILE_SIZE_IN_BYTES) {
            String newFileName = fileName + "_" + Instant.now();
            file.renameTo(new File(newFileName));
        }
    }
}

class StringLogEventFormatter {
    String format(LogEvent logEvent, String DESTINATION) {
        return String.format(
                "%s %s [%s] - %s - %s",
                DESTINATION, logEvent.loggerName, logEvent.level, logEvent.time, logEvent.message);
    }
}

class ElkLogDestination implements LogDestination {
    Queue<LogEvent> queue = new ArrayDeque<>(); /* this could be a persisted queue for resiliency */
    ScheduledExecutorService
            consumer; /* consumes queue and saves checkpoint somewhere for resiliency */

    @Override
    public void write(LogEvent log) {
        queue.offer(log);
    }
}
