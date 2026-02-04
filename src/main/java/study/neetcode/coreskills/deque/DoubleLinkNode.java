package study.neetcode.coreskills.deque;

public class DoubleLinkNode {
    DoubleLinkNode prev;
    int value;
    DoubleLinkNode next;
    public DoubleLinkNode(int value){
        this.value=value;
        this.next=this.prev=null;
    }
    public DoubleLinkNode(DoubleLinkNode prev, int value, DoubleLinkNode next){
        this.prev=prev;
        this.value=value;
        this.next=next;
    }

}
