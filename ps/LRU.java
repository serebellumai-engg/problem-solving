class LRUCache {

    private int capacity;
    private Map<Integer, DoublyNode> cacheMap;
    private DoublyNode head = null, tail = null;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        cacheMap = new HashMap<>();
        head = new DoublyNode();
        tail = new DoublyNode();
        head.next = tail;
        tail.previous = head;
    }
    
    public int get(int key) {
        if(capacity <=0 || !cacheMap.containsKey(key)) {
            return -1;
        }
        DoublyNode node = cacheMap.get(key);
        moveNodeToFront(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if(cacheMap.containsKey(key)) {
            DoublyNode node = cacheMap.get(key);
            node.value = value;
            moveNodeToFront(node);
        } else {
            DoublyNode node = new DoublyNode(key, value);
            cacheMap.put(key, node);
            addNewNode(node);
            if(cacheMap.size() > capacity) {
                int removeKey = removeLRUNode();
                cacheMap.remove(removeKey);
            }
        }
    }
    
    private void moveNodeToFront(DoublyNode node) {
        node.next.previous = node.previous;
        node.previous.next = node.next;
        node.next = head.next;
        node.next.previous = node;
        node.previous = head;
        head.next = node;
    }
    
    private void addNewNode(DoublyNode node) {
        node.next = head.next;
        node.next.previous = node;
        node.previous = head;
        head.next = node;
    }
    
    private int removeLRUNode() {
        int key = -1;
        if(tail.previous != head) {
            DoublyNode lruNode = tail.previous;
            key = lruNode.key;
            lruNode.previous.next= tail;
            tail.previous = lruNode.previous;
        }
        return key;
    }
}

class DoublyNode {
    int key;
    int value;
    DoublyNode previous;
    DoublyNode next;
    
    public DoublyNode() {
        
    }
    public DoublyNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
