package study.neetcode.coreskills.singlylinkedlist;

public class ListNode {
    int value;
    ListNode next;

    ListNode(int i) {
        value = i;
    }

    ListNode(int i, ListNode next) {
        value = i;
        this.next = next;
    }
}
