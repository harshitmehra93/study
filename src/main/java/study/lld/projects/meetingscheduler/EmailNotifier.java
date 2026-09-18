package study.lld.projects.meetingscheduler;

class EmailNotifier implements Notifier {
    @Override
    public void notify(Meeting meeting) {
        for (Person person : meeting.attendees) {
            System.out.println(
                    "Sending Email to "
                            + person.name
                            + " at "
                            + person.email
                            + "about meeting"
                            + meeting.description);
        }
    }
}
