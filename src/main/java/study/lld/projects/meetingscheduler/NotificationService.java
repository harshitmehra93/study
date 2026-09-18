package study.lld.projects.meetingscheduler;

interface NotificationService {
    void notifyAttendees(Meeting meeting);

    void addNotifier(Notifier notifier);
}
