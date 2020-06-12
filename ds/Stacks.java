package ds;
public class Stacks {

     public static void main(String []args) {
        QueueFromStack qs = new QueueFromStack();
        System.out.println(qs.deQueue());
     }
}

class QueueFromStack{
    Stack s1, s2;
    public QueueFromStack(){
        s1 = new Stack();
        s2 = new Stack();
    }
    
    public void enqueue(int val){
        s1.push(val);
    }

    public int deQueue(){
        if(s2.isEmpty()) {
            if(s1.isEmpty()){
                throw new RuntimeException("Queue is Empty");
            }
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }
}

class Stack {
    ListNode top;

    public Stack(){
        top = null;
    }

    void push(int val){
        ListNode temp = new ListNode(val);
        if(!isEmpty()){
            temp.next = top;
        }
        top = temp;
    }

    int pop(){
        if(isEmpty()){
          throw new RuntimeException("Stack is empty");
        }
        ListNode temp = top;
        top = top.next;
        return temp.val;
    }

    boolean isEmpty(){
        return top == null;
    }

    void printStack(){
        if(top == null){
            return; 
        }
        ListNode temp = top;
        System.out.println("Stack");
        while(temp != null){
            System.out.print(temp.val + "->");
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