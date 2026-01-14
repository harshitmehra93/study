package study.neetcode.coreskills.singlylinkedlist;

public class SinglyLinkedListListNodeImpl implements SinglyLinkedList {
    private ListNode head;
    private ListNode tail;
    private int size;
    @Override
    public int get(int i) {
        if(i<0||i>=size)
            return -1;
        if(i==0)
            return head.value;
        ListNode node = getListNode(i);
        return node.value;
    }

    private ListNode getListNode(int i) {
        ListNode pointer=head;
        for(int runner = 1; runner<= i; runner++){
            pointer=pointer.next;
        }
        return pointer;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void insertHead(int value) {
        if(head == null)
            head = tail = new ListNode(value);
        else {
            ListNode newHead = new ListNode(value,head);
            head=newHead;
        }
        size++;
    }

    @Override
    public void insertTail(int value) {
        if(tail==null)
            head=tail=new ListNode(value);
        else{
            ListNode newTail = new ListNode(value);
            tail.next=newTail;
            tail=newTail;
        }
        size++;
    }

    @Override
    public boolean remove(int i) {
        if(i<0||i>=size)
            return false;
        if(i==0){
            head=head.next;
            if (head == null) {
                tail = null;
            }
            size--;
        }else{
            ListNode prevNode = getListNode(i-1);
            prevNode.next=prevNode.next.next;
            if(prevNode.next==null)
                tail=prevNode;
            size--;
        }
        return true;
    }

    @Override
    public int[] getValues() {
        ListNode pointer=head;
        int[] result = new int[size];
        for(int i=0;i<size;i++){
            result[i]=pointer.value;
            pointer=pointer.next;
        }
        return result;
    }
}
