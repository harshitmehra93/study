package study.lld.projects.lrucache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class Main {
    public static void main(String[] args) {
        /*
        LRU cache
        - get(key)
        - put(key,value)

        DoublyLinkedList
        - id
        - Map<key,DoublyLinkedListNode>
        - head, tail
        - writeLock

        DoublyLinkedListNode
        - key
        - value
        - next
        - previous

        GET(Key)
        - get DLL lock
        - see if node with the key exists,
            - if not then create it
            - if yes then remove it from DLL
        - put the node at the end
        - return value
        - release DLL lock

        PUT(Key, Value)
        - get DLL lock
        - see if node exists with key
            - if yes then remove it from DLL, and update its value
            - if not then create a new node
        - add the node at the end
        - release DLL lock

         */
        LRUCache cache = new LRUCache(2);
        cache.get("1");
        cache.get("2");

        cache.put("1", "A");
        cache.put("2", "B");
        cache.put("1", "AA");
        cache.put("2", "BB");
        cache.put("3", "C");

        cache.get("1");
        cache.get("2");
        cache.get("3");
    }
}

class LRUCache {
    private final LRUDoublyLinkedList lruDoublyLinkedList;

    LRUCache(int size) {
        lruDoublyLinkedList = new LRUDoublyLinkedList(size);
    }

    String get(String key) {
        lruDoublyLinkedList.lock();
        try {
            if (!lruDoublyLinkedList.containsKey(key)) {
                System.out.println("Cache Miss");
                return null;
            }

            LRUDoublyLinkedListNode node = lruDoublyLinkedList.detachNode(key);
            lruDoublyLinkedList.addToTail(node);
            System.out.printf("Getting %s: %s\n", node.key, node.value);
            return node.value;
        } finally {
            lruDoublyLinkedList.unlock();
        }
    }

    void put(String key, String value) {
        lruDoublyLinkedList.lock();
        try {
            LRUDoublyLinkedListNode node;
            if (lruDoublyLinkedList.containsKey(key)) {
                node = lruDoublyLinkedList.get(key);
                node.value = value;
                lruDoublyLinkedList.detachNode(key);
            } else {
                node = new LRUDoublyLinkedListNode(key, value);
            }

            System.out.printf("Putting %s: %s\n", node.key, node.value);

            lruDoublyLinkedList.addToTail(node);

            if (lruDoublyLinkedList.getSize() > lruDoublyLinkedList.getMAX_SIZE()) {
                lruDoublyLinkedList.evict();
            }
        } finally {
            lruDoublyLinkedList.unlock();
        }
    }
}

class LRUDoublyLinkedList {
    private final Map<String, LRUDoublyLinkedListNode> nodes;
    private final LRUDoublyLinkedListNode head;
    private final LRUDoublyLinkedListNode tail;
    @Getter private final int MAX_SIZE;
    private final ReentrantLock LOCK;

    LRUDoublyLinkedList(int size) {
        this.head = new LRUDoublyLinkedListNode();
        this.tail = new LRUDoublyLinkedListNode();
        head.next = tail;
        tail.previous = head;
        this.nodes = new ConcurrentHashMap<>();
        this.LOCK = new ReentrantLock();
        this.MAX_SIZE = size;
    }

    void lock() {
        LOCK.lock();
    }

    void unlock() {
        LOCK.unlock();
    }

    public boolean containsKey(String key) {
        return nodes.containsKey(key);
    }

    public LRUDoublyLinkedListNode detachNode(String key) {
        LRUDoublyLinkedListNode node = get(key);
        LRUDoublyLinkedListNode prev = node.previous;
        LRUDoublyLinkedListNode next = node.next;
        prev.next = next;
        next.previous = prev;

        nodes.remove(key);

        return node;
    }

    public LRUDoublyLinkedListNode get(String key) {
        if (!containsKey(key)) throw new RuntimeException("Cache Miss");
        return nodes.get(key);
    }

    public void addToTail(LRUDoublyLinkedListNode node) {
        //        System.out.println("Adding key to end "+node.key);
        LRUDoublyLinkedListNode prev = tail.previous;
        node.next = tail;
        tail.previous = node;

        prev.next = node;
        node.previous = prev;

        nodes.put(node.key, node);
    }

    public int getSize() {
        return nodes.size();
    }

    public void evict() {
        LRUDoublyLinkedListNode leastRecentlyUsedNode = head.next;
        LRUDoublyLinkedListNode newLeastRecentlyUsed = leastRecentlyUsedNode.next;

        head.next = newLeastRecentlyUsed;
        newLeastRecentlyUsed.previous = head;

        nodes.remove(leastRecentlyUsedNode.key);
        System.out.println("Evicted key " + leastRecentlyUsedNode.key);
    }
}

@NoArgsConstructor
class LRUDoublyLinkedListNode {
    String key;
    String value;
    LRUDoublyLinkedListNode next;
    LRUDoublyLinkedListNode previous;

    public LRUDoublyLinkedListNode(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
