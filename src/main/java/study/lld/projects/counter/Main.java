package study.lld.projects.counter;

/*
multiple threads call increment()
final value must be correct
first solve using synchronized
then solve using ReentrantLock

Questions:
What is the critical section? increment
What object are you locking? counter object in synchorzied and lock object
Does get() also need synchronization? yes
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = SimpeCounter.getInstance();
        CounterTester counterTester =
                new TimedCounterTesterDecorator(new ConcurrentCounterTester());
        counterTester.incrementOneMillionConcurrently(counter);
        System.out.println("Count should be = " + 1_000_000);
        System.out.println("Actual Count =" + counter.get());
    }
}
