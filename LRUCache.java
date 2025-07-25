import java.util.*;

class LRUCache {
    int capacity;
    int size;
    CDLL list;
    Map<Integer, CDLLNode> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.list = new CDLL();
        this.map = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        CDLLNode node = map.get(key);
        list.moveAtFront(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            CDLLNode node = map.get(key);
            node.val = value;
            list.moveAtFront(node);
        } else if (size < capacity) {
            CDLLNode node = list.insAtBegin(key, value);
            map.put(key, node);
            size++;
        } else {
            int deletedKey = list.delNode();
            map.remove(deletedKey);
            CDLLNode node = list.insAtBegin(key, value);
            map.put(key, node);
        }
    }
}

class CDLLNode {
    int key, val;
    CDLLNode prev, next;

    CDLLNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

class CDLL {
    CDLLNode head;

    public CDLL() {
        head = null;
    }

    CDLLNode insAtBegin(int key, int val) {
        CDLLNode newNode = new CDLLNode(key, val);
        newNode.next = newNode.prev = newNode;
        if (head == null) {
            head = newNode;
            return head;
        }
        CDLLNode last = head.prev;
        newNode.next = head;
        head.prev = newNode;
        last.next = newNode;
        newNode.prev = last;
        head = newNode;
        return head;
    }

    int delNode() {
        if (head == null) return -1;
        CDLLNode last = head.prev;
        if (last == head) {
            int key = head.key;
            head = null;
            return key;
        }
        int key = last.key;
        last.prev.next = head;
        head.prev = last.prev;
        return key;
    }

    void moveAtFront(CDLLNode node) {
        if (node == head) return;
        // Detach node
        node.prev.next = node.next;
        node.next.prev = node.prev;

        // Insert at front
        CDLLNode last = head.prev;
        node.next = head;
        head.prev = node;
        last.next = node;
        node.prev = last;
        head = node;
    }

    // Optional: Utility to print list keys (for debugging)
    void printLL() {
        if (head == null) return;
        System.out.print("Cache Order: " + head.key + " ");
        CDLLNode temp = head.next;
        while (temp != head) {
