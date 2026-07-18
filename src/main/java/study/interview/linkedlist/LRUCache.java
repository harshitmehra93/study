package study.interview.linkedlist;

import java.util.HashMap;
import java.util.Map;

/*
LRU Cache
Design a data structure that follows the constraints of a Least Recently Used cache.
Implement the LRUCache class:
LRUCache(int capacity)
int get(int key)
void put(int key, int value)
Rules:
get(key) returns the value if the key exists, otherwise -1.
Whenever a key is accessed through get, it becomes the most recently used.
Whenever a key is inserted or updated through put, it becomes the most recently used.
If inserting a new key exceeds capacity, evict the least recently used key.
Both get and put must run in O(1) average time.
 */
public class LRUCache {

    ListNode head;
    ListNode tail;

    int capacity;
    Map<Integer, ListNode> map;

    public LRUCache(int capacity) {
        head = new ListNode(null);
        tail = new ListNode(null);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        map = new HashMap<>();
    }

    public int get(Integer key) {
        if (!map.containsKey(key)) return -1;
        var node = map.get(key);
        moveToTail(node);
        return node.val.value();
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            removeFromHeadIfRequired();
            var node = new ListNode(new KeyValue(key, value));
            map.put(key, node);
            addToTail(node);
        } else {
            var node = map.get(key);
            node.val = new KeyValue(key, value);
            moveToTail(node);
        }
    }

    private void moveToTail(ListNode node) {
        detachNode(node);
        addToTail(node);
    }

    private void detachNode(ListNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToTail(ListNode newNode) {
        newNode.next = tail;
        newNode.prev = tail.prev;
        tail.prev.next = newNode;
        tail.prev = newNode;
    }

    private void removeFromHeadIfRequired() {
        if (map.size() < capacity) return;
        var node = head.next;
        node.next.prev = head;
        head.next = node.next;
        map.remove(node.val.key());
    }

    public static class ListNode {
        KeyValue val;
        ListNode next;
        ListNode prev;

        ListNode(KeyValue val) {
            this.val = val;
        }
    }

    record KeyValue(int key, int value) {}
}
