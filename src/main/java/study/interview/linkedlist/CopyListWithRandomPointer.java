package study.interview.linkedlist;

import java.util.HashMap;
import java.util.Map;
import study.interview.commons.ListNodeWithRandomPointer;

/*
Problem:
You are given a linked list of length n where each node contains an additional random pointer, which could point to any node in the list or null.
Construct a deep copy of the list. The deep copy should consist of exactly n new nodes, where each new node has the same value as its corresponding original node. Both the next and random pointers of the new nodes should point to new nodes in the copied list, not nodes in the original list.
Return the head of the copied linked list.
 */
public class CopyListWithRandomPointer {
    public ListNodeWithRandomPointer copyRandomList(ListNodeWithRandomPointer head) {
        Map<ListNodeWithRandomPointer, ListNodeWithRandomPointer> oldToNew = new HashMap<>();

        var oldNode = head;
        ListNodeWithRandomPointer tail = new ListNodeWithRandomPointer();
        var newHead = tail;
        while (oldNode != null) {
            ListNodeWithRandomPointer newNode = new ListNodeWithRandomPointer(oldNode.val);
            tail.next = newNode;
            tail = newNode;
            oldToNew.put(oldNode, newNode);
            oldNode = oldNode.next;
        }
        newHead = newHead.next;

        oldNode = head;
        while (oldNode != null) {
            var newNode = oldToNew.get(oldNode);
            newNode.randomPointer = oldToNew.get(oldNode.randomPointer);
            oldNode = oldNode.next;
        }
        return newHead;
    }
}
