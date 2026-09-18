package study.lld.projects.meetingscheduler;

import java.util.ArrayList;
import java.util.List;

class NotificationServiceImpl implements NotificationService {
    List<Notifier> notifiers = new ArrayList<>();

    @Override
    public void notifyAttendees(Meeting meeting) {
        notifiers.forEach(notifier -> notifier.notify(meeting));
    }

    @Override
    public void addNotifier(Notifier notifier) {
        notifiers.add(notifier);
    }
}
