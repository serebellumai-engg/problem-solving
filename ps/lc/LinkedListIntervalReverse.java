package lc;

public class LinkedListIntervalReverse {


    public static void main(String[] args) {
        MyLinkedList myLL = new MyLinkedList();
        myLL.add(3);
        myLL.add(5);
        ListNode newHead = reverseBetween(myLL.getHead(), 1, 2);
        myLL.setHead(newHead);
        myLL.print();
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null) return null;
        if (left >= right) return head;
        int i = 1;
        ListNode temp = head;
        ListNode prevNode = null, afterNode = null, endNode = null;
        while (i < left) {
            prevNode = temp;
            temp = temp.next;
            i++;
        }
        while (i <= right) {
            if (temp.next == null && i < right) return head;
            endNode = temp;
            temp = temp.next;
            i++;
        }
        afterNode = endNode.next;
        endNode.next = null;
        ListNode startNode = prevNode == null ? head : prevNode.next;
        ListNode newStartNode = reverseLL(startNode);
        if (prevNode == null)
            head = newStartNode;
        else
            prevNode.next = newStartNode;
        ListNode t = newStartNode;
        while (t.next != null)
            t = t.next;
        t.next = afterNode;
        return head;
    }

    private static ListNode reverseLL(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode next = reverseLL(head.next);
        head.next.next = head;
        head.next = null;
        return next;
    }
}


class MyLinkedList {
    private ListNode head;

    public ListNode getHead() {
        return head;
    }

    public void setHead(ListNode head) {
        this.head = head;
    }

    public MyLinkedList() {
        head = null;
    }

    public void add(int a) {
        if (head == null) {
            head = new ListNode(a);
            return;
        }
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new ListNode(a);
    }

    public void print() {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + "->");
            temp = temp.next;
        }
        System.out.println();
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
