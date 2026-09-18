package study.lld.projects.meetingscheduler;

import java.util.concurrent.atomic.AtomicInteger;

class Person {
    static final AtomicInteger counter = new AtomicInteger(0);
    private final int id;
    String name;
    String email;
    String address;

    public Person(String name, String email, String address) {
        this.id = counter.getAndIncrement();
        this.name = name;
        this.email = email;
        this.address = address;
    }
}
