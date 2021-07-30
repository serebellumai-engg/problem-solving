package lc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class LFUCache {

    private Map<Integer, DoublyNode> cacheMap;
    private Map<Integer, DoublyLinkedList> frequencyDoublyList;
    private int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<>();
        frequencyDoublyList = new HashMap<>();
    }

    public int get(int key) {
        if (capacity == 0) return -1;
        if (cacheMap.containsKey(key)) {
            DoublyNode currentNode = moveNodeToNextFrequency(key);
            return currentNode.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        if (cacheMap.containsKey(key)) {
            DoublyNode currentNode = moveNodeToNextFrequency(key);
            currentNode.value = value;
        } else {
            if (cacheMap.size() == capacity) {
                int minFrequency = Collections.min(frequencyDoublyList.keySet());
                DoublyLinkedList leastFrequencyNode = frequencyDoublyList.get(minFrequency);
                DoublyNode lruNode = leastFrequencyNode.removeTail();
                cacheMap.remove(lruNode.key);
            }
            DoublyNode currentNode = new DoublyNode(key, value);
            frequencyDoublyList.computeIfAbsent(1, dl -> new DoublyLinkedList()).moveNodeToFront(currentNode);
            cacheMap.put(key, currentNode);
        }
    }

    private DoublyNode moveNodeToNextFrequency(int key) {
        DoublyNode currentNode = cacheMap.get(key);
        int currentFrequency = currentNode.frequency;
        DoublyLinkedList frequencyDl = frequencyDoublyList.get(currentFrequency);
        frequencyDl.removeNode(currentNode);
        if (frequencyDl.isEmpty()) {
            frequencyDoublyList.remove(currentFrequency);
        }
        currentFrequency++;
        frequencyDoublyList.computeIfAbsent(currentFrequency, dl -> new DoublyLinkedList()).moveNodeToFront(currentNode);
        currentNode.frequency = currentFrequency;
        return currentNode;
    }
}

class DoublyLinkedList {
    private DoublyNode head;
    private DoublyNode tail;

    public DoublyLinkedList() {
        head = new DoublyNode(-1, -1);
        tail = new DoublyNode(-1, -1);
        head.next = tail;
        tail.previous = head;
    }

    public void moveNodeToFront(DoublyNode currentNode) {
        currentNode.next = head.next;
        currentNode.previous = head;
        head.next.previous = currentNode;
        head.next = currentNode;
    }

    public boolean isEmpty() {
        return head.next == tail && tail.previous == head;
    }

    public DoublyNode removeTail() {
        DoublyNode tailNode = tail.previous;
        tailNode.previous.next = tail;
        tail.previous = tailNode.previous;
        return tailNode;
    }

    public void removeNode(DoublyNode currentNode) {
        currentNode.next.previous = currentNode.previous;
        currentNode.previous.next = currentNode.next;
    }
}

class DoublyNode {
    int key;
    int value;
    int frequency;
    DoublyNode previous;
    DoublyNode next;

    public DoublyNode(int key, int value) {
        this.key = key;
        this.value = value;
        this.frequency = 1;
    }
}
