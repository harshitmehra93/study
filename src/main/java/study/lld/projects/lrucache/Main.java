package study.lld.projects.lrucache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;

public class Main {
    public static void main(String[] args) {
        /*
        LRU cache
        A key value pair db which has a max size N, and when the cache capacity is full, we
        overwrite the least recently used keys

        Keeping LruCache in memory is easy with a doubly linked list, but need to see how we will
            keep it in DB
            - We can have a LruNode table which saves LruNodes
            - when someone does a get(key) we find this table to get the key.
            - we will first add two sentinels Head and Tail in the dao upon initialisation to simplify logic
                - how will we select key of head and tail ?
                    - selecting any key for head and tail will lead to eliminating the key as a valid key of the system
                    - We can select a 10 length alpha numeric random String to greatly reduce collision.
                    - If there is ever a collision we will emit a metric and alarm, and we can have logic to migrate head
                        and tail key if there is ever a collision
                - In a distributed system, how do all apps know the head and tail ?
                    - once we select the key, we need a durable way to get to know the head/tail key
                    - we can have a separate table for this
                - We will have a init method which sets up the sentinels
                    - init will create two rown in lru dao as head and tail
                    - then init will update dentinelDao to update the key of head and tail
                - Then we need an extra query on every put to check if its hitting the sentinel. Which is extra query.

                -  On second thoughts, this adds too much complexity for a real DB scenario, so we will not be using
                    sentinels for real DB, but we will use it in this example for simplicity
            - Then we remove the LruNode from the middle of the doubly Linked list and put it at the head
                - get the node : node = get(key)
                - remove
                    - prev = get(node.prev)
                    - next = get(node.next)
                    - updateNext(previous,next)
                    - updatePrevious(next,previous)
                - AddToHead
                    - currentHead = head.prev
                    - currentHead.next = node
                    - node.previous = currentHead
                    - node.next = head
                    - head.previous = node

        LruCache
        get(key): value <This will increase the recency of the key>
        put(key,value): void <This will increase the recency of the key>
        delete(key): <will skip>

        LruService
         - LruDao
         - get(key): value
         - put(key,value): value
         - delete(key): value

         LruDao
         - SentinelDao sentinelDao
         - getMostRecent(): LruNode
         - get(key): LruNode
         - put(key,value): void
         - getLeastRecent(): LruNode
         - addToHead(LruNode)
         - updatePrevious(LruNode,LruNode)
         - updateNext(LruNode,LruNode)
         - getHead(): LruNode
         - getTail(): LruNode

         LruNode
         - Key
         - Value
         - nextLruNodeId : int
         - previousLruNodeId : int

         SentinelDao
         - List<Sentinel>
         - createHead(): void
         - createTail(): void
         - getHeadKey(): key
         - getTailKey(): key
         - This will save HEAD : key, TAIL: key

         Sentinel
         - type : HEAD/TAIL
         - key : String


         DoublyLinkedList
         Head -> | | | | <- Tail

         */
        LruDao lruDao = new LruDao();
        LruService lru = new LruService(lruDao);

        lru.put("a", "b");
        lru.put("b", "b");
        lru.put("c", "b");
        lru.put("d", "b");
        lru.put("e", "b");
        lru.printAll();
        System.out.println("====");
        lru.put("f", "b");
        lru.put("g", "b");
        lru.put("h", "b");
        lru.put("i", "b");
        lru.put("j", "b");
        lru.printAll();
        System.out.println("====");

        lru.put("f", "c");
        lru.put("k", "b");
        lru.printAll();
        System.out.println("====");
    }
}
/*
       LruService
        - LruDao
        - get(key): value
        - put(key,value): void
        - delete(key): void
*/
class LruService {
    private final LruDao lruDao;
    int capacity = 5;

    LruService(LruDao lruDao) {
        this.lruDao = lruDao;
    }

    String get(String key) {
        LruNode node = lruDao.get(key);
        if (node == null) return null;
        lruDao.removeFromMiddle(node);
        lruDao.addToTail(node);
        return node.value;
    }

    void put(String key, String value) {
        LruNode existingNode = lruDao.get(key);
        if (existingNode == null) {
            if (lruDao.getSize() == capacity) lruDao.removeLeastRecentlyUsed();
            lruDao.addNode(key, value);
        } else {
            lruDao.update(key, value);
            lruDao.removeFromMiddle(existingNode);
            lruDao.addToTail(existingNode);
        }
    }

    void remove(String key) {
        lruDao.removeFromMiddle(lruDao.get(key));
    }

    void printAll() {
        lruDao.printAll();
    }
}

class LruDao {
    String headKey = "-1";
    String tailKey = "-2";
    Map<String, LruNode> nodes;
    @Getter private int size;

    LruDao() {
        nodes = new HashMap<>();
        LruNode head = new LruNode(headKey, headKey);
        LruNode tail = new LruNode(tailKey, tailKey);
        nodes.put(headKey, head);
        nodes.put(tailKey, tail);
        head.nextNodeKey = tailKey;
        tail.previousNodeKey = headKey;
        size = 0;
    }

    LruNode get(String key) {
        if (!nodes.containsKey(key)) return null;
        return nodes.get(key);
    }

    LruNode getHead() {
        return get(headKey);
    }

    void removeFromMiddle(LruNode node) {
        LruNode prev = get(node.previousNodeKey);
        LruNode next = get(node.nextNodeKey);

        prev.nextNodeKey = next.key;
        next.previousNodeKey = prev.key;

        nodes.remove(node.key);
        size--;
    }

    void removeLeastRecentlyUsed() {
        LruNode firstElement = get(getHead().nextNodeKey);
        LruNode secondElement = get(firstElement.nextNodeKey);

        secondElement.previousNodeKey = getHead().key;
        getHead().nextNodeKey = secondElement.key;

        nodes.remove(firstElement.key);
        size--;
    }

    void addToTail(LruNode node) {
        LruNode lastElement = get(getTail().previousNodeKey);

        getTail().previousNodeKey = node.key;
        node.nextNodeKey = getTail().key;

        lastElement.nextNodeKey = node.key;
        node.previousNodeKey = lastElement.key;

        nodes.put(node.key, node);
        size++;
    }

    LruNode getTail() {
        return get(tailKey);
    }

    LruNode addNode(String key, String value) {
        LruNode node = new LruNode(key, value);
        addToTail(node);
        return node;
    }

    public void printAll() {
        LruNode tmp = get(getHead().nextNodeKey);
        while (tmp != null && tmp != getTail()) {
            System.out.printf("%s: %s\n", tmp.key, tmp.value);
            tmp = get(tmp.nextNodeKey);
        }
    }

    public void update(String key, String value) {
        get(key).value = value;
    }
}

class LruNode {
    private static AtomicInteger counter = new AtomicInteger(0);
    String key;
    String value;
    String nextNodeKey;
    String previousNodeKey;

    public LruNode(String value) {
        this.key = String.valueOf(counter.getAndIncrement());
        ;
        this.value = value;
    }

    public LruNode(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
