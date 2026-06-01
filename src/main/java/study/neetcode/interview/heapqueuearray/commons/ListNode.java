package study.neetcode.interview.heapqueuearray.commons;

public class ListNode implements Comparable<ListNode> {
    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public int compareTo(ListNode o) {
        return Integer.compare(val, o.val);
    }
}
