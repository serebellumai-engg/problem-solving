package ps;


import java.util.HashMap;
import java.util.Map;

public class LRU {


    private DoublyLinkedList recentlyUsedList = null;
    private Map<String, Pair<DoublyNode, String>> map = null;
    private int maxCapacity;


    public LRU(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        recentlyUsedList = new DoublyLinkedList();
        map = new HashMap<>();

    }

    public String get(String key) {
        if (map.containsKey(key)) {
            DoublyNode currentNode = map.get(key).getFirstValue();
            recentlyUsedList.addInBeginning(currentNode);
            return map.get(key).secondValue;
        }
        return null;
    }

    public void put(String key, String value) {
        if (recentlyUsedList.size() == maxCapacity) {
            String removedKey = recentlyUsedList.removeTail();
            map.remove(removedKey);
        }
        DoublyNode newNode = new DoublyNode(key);
        map.put(key, new Pair(newNode, value));
        recentlyUsedList.addInBeginning(newNode);
    }

}

class DoublyNode {

    String val;
    DoublyNode nextPtr;
    DoublyNode prevPtr;

    public DoublyNode(String val) {
        this.val = val;
        this.prevPtr = null;
        this.nextPtr = null;
    }

    public DoublyNode getNextPtr() {
        return nextPtr;
    }

    public DoublyNode getPrevPtr() {
        return prevPtr;
    }

}

class DoublyLinkedList {

    private DoublyNode headPtr;
    private DoublyNode lastPtr;
    private int size;

    public DoublyLinkedList() {
    }

    public DoublyLinkedList(String value) {
        headPtr = new DoublyNode(value);
        headPtr.prevPtr = null;
        headPtr.nextPtr = null;
        lastPtr = headPtr;
    }

    public DoublyNode getHead() {
        return headPtr;
    }

    public void addInBeginning(DoublyNode node) {
        if (headPtr != null) {
            DoublyNode temp = headPtr;
            node.nextPtr = temp;
            temp.prevPtr = node;
            headPtr = node;
        } else {
            headPtr = node;
            lastPtr = headPtr;
        }
        size += 1;
    }

    public String removeTail() {
        String tailValue = lastPtr.val;
        lastPtr = lastPtr.prevPtr;
        lastPtr.nextPtr = null;
        size -= 1;
        return tailValue;
    }

    public int size() {
        return size;
    }

    public void printDoublyLinkedList() {
        DoublyNode temp = this.getHead();
        while (temp != null) {
            System.out.print(temp.val + "<=>");
            temp = temp.getNextPtr();
        }
        System.out.println();
    }
}

class Pair<K, V> {
    K firstValue;
    V secondValue;

    public Pair(K firstValue, V secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public K getFirstValue() {
        return firstValue;
    }

    public V getSecondValue() {
        return secondValue;
    }
}




