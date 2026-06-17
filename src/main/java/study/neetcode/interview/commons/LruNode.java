package study.neetcode.interview.commons;

public class LruNode implements Comparable<LruNode> {

    public Integer key;
    public Integer val;
    public LruNode previous;
    public LruNode next;

    public LruNode() {}

    public LruNode(int key, int val) {
        this.key = key;
        this.val = val;
    }

    public LruNode(LruNode prev, int val, LruNode next) {
        this.previous = prev;
        this.val = val;
        this.next = next;
    }

    @Override
    public int compareTo(LruNode n) {
        return val.compareTo(n.val);
    }
}
