package study.neetcode.interview.linkedlist;

import java.util.HashMap;
import java.util.Map;
import study.neetcode.interview.commons.LruNode;

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
    LruNode head;
    LruNode tail;
    int size;
    int capacity;
    Map<Integer, LruNode> nodes;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        size = 0;
        nodes = new HashMap<>();
    }

    public int get(Integer key) {
        if (!nodes.containsKey(key)) return -1;
        LruNode node = nodes.get(key);
        moveNodeToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (nodes.containsKey(key)) {
            LruNode node = nodes.get(key);
            node.val = value;
            moveNodeToHead(node);
            return;
        }
        if (size == capacity) {
            removeLru();
        }
        var node = new LruNode(key, value);
        nodes.put(key, node);
        addToHead(node);
    }

    private void removeLru() {
        if (size == 1) {
            nodes.clear();
            head = tail = null;
            size--;
            return;
        }
        nodes.remove(tail.key);
        var secondLast = tail.next;
        secondLast.previous = null;
        tail = secondLast;
        size--;
    }

    void moveNodeToHead(LruNode node) {
        if (size == 1) return;
        if (node == head) return;
        if (node == tail) {
            node.next.previous = null;
            tail = node.next;
        } else {
            node.next.previous = node.previous;
            node.previous.next = node.next;
        }
        head.next = node;
        node.previous = head;
        node.next = null;

        head = node;
    }

    private void addToHead(LruNode node) {
        if (size == 0) {
            head = tail = node;
            size++;
            return;
        }
        head.next = node;
        node.previous = head;
        node.next = null;

        head = node;
        size++;
    }
}
