package ds;
import java.util.Set;
import java.util.Stack;
import java.util.HashSet;
public class LinkedLists {


    public static void main(String []args){
        int []a = {1, 3, 4, 5, 6};
        ListNode head = prepareLinkedListFromArray(a);
        printLinkedList(head);
        head = seggregateEvenOddNodes(head);
        printLinkedList(head);
    }

    private static boolean isEven(int number){
        return number%2 == 0;
    }

    public static void reverseLL(ListNode head){
        ListNode current = head;
        ListNode prev = null;
        ListNode next = null;
        while(current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    private static ListNode addNode(ListNode head, int number) {
        ListNode temp = head;
        if(head == null){
            head = new ListNode(number);
            return head;
        }
        while(temp.next != null){
            temp = temp.next;
        }
        ListNode newNode = new ListNode(number);
        temp.next = newNode;
        return head;
    }

    public static ListNode seggregateEvenOddNodes(ListNode head){
        ListNode headOdd = null;
        ListNode temp = head;
        while(temp.next != null){
            if(!isEven(temp.next.val)){
                headOdd = addNode(headOdd, temp.next.val);
                temp.next = temp.next.next;
            }
            temp = temp.next;
        }
        if(!isEven(head.val)){
            temp.next = new ListNode(head.val);
            head = head.next;
            temp = temp.next;
        }
        temp.next = headOdd;
        return head;
    }

    public static ListNode intersectionOfTwoLL(ListNode head1, ListNode head2){
        if(head1 == null || head2 == null){
            return null;
        }
        ListNode temp1 = head1;
        Set<Integer> visitedNodes = new HashSet<Integer>();
        while(temp1 != null) {
            visitedNodes.add(temp1.val);
            temp1 = temp1.next;
        }
        ListNode temp2 = head2;
        while(temp2 != null){
            if(visitedNodes.contains(temp2.val)){
                return temp2;
            }
            temp2 = temp2.next;
        }
        return null;
    }

    public static ListNode intersectionOfTwoSortedLL(ListNode head1, ListNode head2){
        ListNode temp1 = head1;
        ListNode temp2 = head2;
        while(temp1 != null && temp2 != null){
            if(temp1.val == temp2.val){
                return temp1;
            } else if( temp1.val > temp2.val){
                temp2 = temp2.next;
            } else {
                temp1 = temp1.next;
            }
        }
        return null;
    }

    public static void moveLastElementToFront(ListNode head){
        if(head == null){
            return;
        }
        ListNode temp = head;
        while(temp.next.next != null){
            temp = temp.next;
        }
        ListNode lastNode = temp.next;
        temp.next = null;
        ListNode nextNode = head;
        head = lastNode;
        head.next = nextNode;
        printLinkedList(head);
    }

    public static void pairWiseSwapRecursive(ListNode head){
        ListNode temp = head;
        if(temp != null && temp.next != null){
            int tempVal = temp.val;
            temp.val = temp.next.val;
            temp.next.val = tempVal;
            temp = temp.next.next;
            pairWiseSwap(temp.next.next);
        }
    }

    public static void pairWiseSwap(ListNode head){
        ListNode temp = head;
        while(temp != null && temp.next != null){
            int tempVal = temp.val;
            temp.val = temp.next.val;
            temp.next.val = tempVal;
            temp = temp.next.next;
        }
    }

    public static void removeDuplicatesFromUnsortedLL(ListNode head){
        ListNode temp = head;
        Set<Integer> visitedNumbers = new HashSet<>();
        visitedNumbers.add(head.val);
        while(temp.next != null) {
            if(visitedNumbers.contains(temp.next.val)){
                temp.next = temp.next.next;
            } else {
                visitedNumbers.add(temp.next.val);
                temp = temp.next;
            }
        }

    }

    public static void removeDuplicatesFromSortedLL(ListNode head){
        ListNode temp = head;
        while(temp.next != null){
            if(temp.val == temp.next.val){
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
    }

    public static boolean isPalindrome(ListNode head){
        ListNode temp = head;
        Stack<Integer> st = new Stack<>();
        while(temp != null){
            st.push(temp.val);
            temp = temp.next;
        }
        temp = head;
        while (!st.isEmpty()){
            int val = st.peek();
            if(temp.val == val){
                temp = temp.next;
                st.pop();
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static int getLoopedLength(ListNode head){
        Set<ListNode> visitedNodes = new HashSet<ListNode>();
        ListNode temp = head;
        while(temp != null){
            if(visitedNodes.contains(temp)){
                break;
            }
            visitedNodes.add(temp);
            temp = temp.next;
        }
        int length = 0;
        ListNode loopStartNode = temp;
        temp = temp.next;
        while(temp != null){
            length++;
            if(temp == loopStartNode){
                break;
            }
            temp = temp.next;
        }
       return length;
    }

    public static boolean isLoopedLL(ListNode head){
        Set<ListNode> visitedNodes = new HashSet<ListNode>();
        ListNode temp = head;
        while(temp != null){
            if(visitedNodes.contains(temp)){
                return true;
            }
            visitedNodes.add(temp);
            temp = temp.next;
        }
        return false;
    }

    public static int count(ListNode head, int number){
        ListNode temp = head;
        int count = 0;
        while(temp != null){
            if(temp.val == number)
                count++;
            temp = temp.next;
        }
        return count;
    }

    public static int lengthI(ListNode head){
        ListNode temp = head;
        int length = 0;
        while(temp != null){
            length++;
            temp = temp.next;
        }
        return length;
    }

    public static ListNode middleOfLinkedList(ListNode head){
        if(head == null){
            return null;
        }
        ListNode slowPtr = head;
        ListNode fastPtr = head;
        while(fastPtr != null && fastPtr.next != null){
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
        }
        return slowPtr;
    }

    public static ListNode nthNode(ListNode head, int n){
        ListNode temp = head;
        while(n!=0){
            temp = temp.next;
            n--;
        }
        return temp;
    }

    public static ListNode nthNodeFromEnd(ListNode head, int n){
        ListNode slowPtr = head;
        ListNode fastPtr = head;
        while(n != 0 && fastPtr != null){
            fastPtr = fastPtr.next;
        }
        if(fastPtr == null){
            return null;
        }
        while(fastPtr.next != null){
            fastPtr = fastPtr.next;
            slowPtr = slowPtr.next;
        }
        return slowPtr;
    }

    public static int length(ListNode head){
        if(head == null){
            return 0;
        }
        return 1 + length(head.next);

    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode temp = head;
        if(temp == null){
            return null;
        }
        ListNode currentNode = head;
        while(currentNode != null && currentNode.next != null){
            if(currentNode.val == currentNode.next.val){
                currentNode.next = currentNode.next.next;
            } else {
                currentNode = currentNode.next;
            }
        }
       return head;
    }

    private static ListNode prepareLinkedListFromArray(int []a){
        if(a.length == 0){
            return null;
        }
        ListNode head = new ListNode(a[0]);
        ListNode temp = head;
        for(int i=1;i<a.length; i++){
            ListNode currentNode = new ListNode(a[i]);
            temp.next = currentNode;
            temp = temp.next;
        }
        return head;
    }

    static void printLinkedList(ListNode head){
        if(head == null){
            System.out.println("Empty");
            return;
        }
        ListNode temp = head;
        while(temp != null) {
            System.out.print(temp.val + "->");
            temp = temp.next;
        }
        System.out.println();
    }

    static void printDoublyLinkedList(DoublyListNode head){
        if(head == null){
            System.out.println("Empty");
            return;
        }
        DoublyListNode temp = head;
        while(temp != null) {
            System.out.print(temp.val + "<->");
            temp = temp.next;
        }
        System.out.println();
    }

    
}

 class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
} 

class DoublyListNode {
    int val;
    DoublyListNode next;
    DoublyListNode prev;
    
    public DoublyListNode(int x) { val = x; }
}