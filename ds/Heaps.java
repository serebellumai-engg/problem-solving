
package ds;
import java.util.*; 

public class Heaps{


    public static void main(String []args){
        System.out.println("The Min Heap is "); 
        /*MinHeap minHeap = new MinHeap(15); 
        minHeap.insert(5); 
        minHeap.insert(3); 
        minHeap.insert(17); 
        minHeap.insert(10); 
        minHeap.insert(84); 
        minHeap.insert(19); 
        minHeap.insert(6); 
        minHeap.insert(22); 
        minHeap.insert(9); 
  
        minHeap.print(); 
        System.out.println("The Min val is " + minHeap.remove()); 
        System.out.println("The Min val is " + minHeap.remove()); 
        System.out.println("The Min val is " + minHeap.remove()); 
        System.out.println("The Min val is " + minHeap.remove());
        System.out.println("The Min val is " + minHeap.remove());
        System.out.println("The Min val is " + minHeap.remove());
        System.out.println("The Min val is " + minHeap.remove()); */
        heapUsingPriorityQueue();
    }


    public static void heapUsingPriorityQueue(){
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();
        pQueue.add(5);
        pQueue.add(4);
        pQueue.add(3);
        pQueue.add(2);
        pQueue.add(1);
        System.out.println(pQueue.peek());
        System.out.println(pQueue.poll());
    }

}

/**
 * Min Heap Properties:
 * 1. Root is the minimum values
 * 2. Nodes are added from left to right, so its treated as complete binary tree
 * 3. Value of each node is greater than its parent
 */
class MinHeap{

    private int []heap;
    private int size;
    private int maxsize; 

    private static final int FRONT = 1; 


    public MinHeap(int maxsize){
        this.maxsize = maxsize;
        this.size = 0;
        this.heap = new int[this.maxsize];
    }

    private int parent(int pos){
        return pos/2;
    }

    private int leftChild(int pos){
        return pos*2;
    }

    private int rightChild(int pos){
        return (2*pos) + 1;
    }

    private boolean isLeaf(int pos){
        if(pos >= size/2 && pos <= size){return true;}
        return false;
    }

    private void swap(int first, int second){
        int temp;
        temp = heap[first];
        heap[first] = heap[second];
        heap[second] = temp;
    }

    private void minHeapify(int pos){
        if(!isLeaf(pos)){
            if(heap[pos] > heap[leftChild(pos)] || heap[pos] > heap[rightChild(pos)]){

                if(heap[leftChild(pos)] < heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                } else {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }

            } 
        }
    }


    public void insert(int element) { 
        if (size >= maxsize) { 
            return; 
        } 
        heap[++size] = element; 
        int current = size; 
  
        while (heap[current] < heap[parent(current)]) { 
            swap(current, parent(current)); 
            current = parent(current); 
        } 
    } 

    public void minHeap()  { 
        for (int pos = (size / 2); pos >= 1; pos--) { 
            minHeapify(pos); 
        } 
    } 

    public int remove() { 
        int popped = heap[FRONT]; 
        heap[FRONT] = heap[size--]; 
        minHeapify(FRONT); 
        return popped; 
    } 
    public void print() { 
        for (int i = 1; i <= size / 2; i++) { 
            System.out.print(" PARENT : " + heap[i] 
                             + " LEFT CHILD : " + heap[2 * i] 
                             + " RIGHT CHILD :" + heap[2 * i + 1]); 
            System.out.println(); 
        } 
    } 
}