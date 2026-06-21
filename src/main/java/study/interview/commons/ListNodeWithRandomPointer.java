package study.interview.commons;

public class ListNodeWithRandomPointer implements Comparable<ListNodeWithRandomPointer> {
    public int val;
    public ListNodeWithRandomPointer next;
    public ListNodeWithRandomPointer randomPointer;

    public ListNodeWithRandomPointer() {}

    public ListNodeWithRandomPointer(int val) {
        this.val = val;
    }

    public ListNodeWithRandomPointer(int val, ListNodeWithRandomPointer next) {
        this.val = val;
        this.next = next;
    }

    public ListNodeWithRandomPointer(
            int val, ListNodeWithRandomPointer next, ListNodeWithRandomPointer randomPointer) {
        this.val = val;
        this.next = next;
        this.randomPointer = randomPointer;
    }

    @Override
    public int compareTo(ListNodeWithRandomPointer o) {
        return Integer.compare(val, o.val);
    }
}
