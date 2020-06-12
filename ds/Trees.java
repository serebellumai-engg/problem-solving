package ds;
import java.util.LinkedList;
import java.util.List;
import java.lang.Math;
import java.util.Queue;
public class Trees{

static TreeNode prev = null;

   public static void main(String[] args) {
        
        final Tree t = new Tree(3);
        t.root.left = new TreeNode(1);
        t.root.right = new TreeNode(4);
        t.root.left.right = new TreeNode(2);
        /*inOrderTraversal(t.root);
        System.out.println();
        preOrderTraversal(t.root);
        System.out.println();
        postOrderTraversal(t.root);
        System.out.println();
        levelOrderTraversal(t.root);*/
        preOrderTraverseForTrim(t.root, 1,2);
    }

    public static void inOrderTraversal(final TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        inOrderTraversal(treeNode.left);
        System.out.print(treeNode.val + " ");
        inOrderTraversal(treeNode.right);
    }

    public static void preOrderTraversal(final TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.print(treeNode.val + " ");
        preOrderTraversal(treeNode.left);
        preOrderTraversal(treeNode.right);
    }

    public static void postOrderTraversal(final TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        postOrderTraversal(treeNode.left);
        postOrderTraversal(treeNode.right);
        System.out.print(treeNode.val + " ");
    }

    public static void levelOrderTraversal(final TreeNode treeNode) {

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        if(treeNode == null){
            return;
        }
        q.add(treeNode);
        while(!q.isEmpty()){
            TreeNode temp = q.remove();
            if(temp.left != null)
                q.add(temp.left);
            if(temp.right != null)
                q.add(temp.right);
            System.out.print(temp.val + " ");
        }
    }

    public static void captureInOrderTraversal(TreeNode treeNode, List<Integer> nodes) {
        if (treeNode == null) {
            return;
        }
        inOrderTraversal(treeNode.left);
        nodes.add(treeNode.val);
        inOrderTraversal(treeNode.right);
    }

    public static boolean isTreeBST(TreeNode root){
        List<Integer> nodes = new LinkedList<>();
        captureInOrderTraversal(root, nodes);
        for(int i = 1; i< nodes.size()-1; i++){
            if(nodes.get(i) > nodes.get(i-1) && nodes.get(i) < nodes.get(i+1)){
                continue;
            }
            return false;
        }
        return true;
    }

    public static int height(TreeNode root){
        if(root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    static boolean isBST(TreeNode node) { 

        if (node != null) { 
            if (!isBST(node.left)) 
                return false; 
  
            if (prev != null && node.val <= prev.val ) 
                return false; 
            prev = node; 
            return isBST(node.right); 
        } 
        return true; 
    }

    //private static DoublyListNode head = null;

    /*static void addToDLL(TreeNode rootNode){
        if(rootNode == null) return;
        DoublyListNode currentNode = new DoublyListNode(rootNode.val);
        if(head == null){
            head = currentNode;
        } else {
            DoublyListNode temp = head;
            while(temp != null & temp.next != null) temp = temp.next;
            temp.next = currentNode;
            currentNode.prev = temp;
        }
    }

    static void inOrderToDll(TreeNode rootNode){
        if(rootNode == null){ return;}
        inOrderToDll(rootNode.left);
        addToDLL(rootNode.left);
        addToDLL(rootNode);
        inOrderToDll(rootNode.right);
        addToDLL(rootNode.right);
    }

    static void bstToDLL(TreeNode root){
        inOrderToDll(root);
        LinkedLists.printDoublyLinkedList(head);
    }*/


    static TreeNode trimTree(TreeNode root, int L, int R){
        return preOrderTraverseForTrim(root, L, R);
    }

    static TreeNode preOrderTraverseForTrim(TreeNode root, int L, int R){
        if(root == null){ return null;}
        System.out.println(root.val);
        if(root.val < L) {
            TreeNode rightNode = preOrderTraverseForTrim(root.right, L, R);
            root = rightNode;
            return root;

        } else if(root.val > R) {
            TreeNode leftNode = preOrderTraverseForTrim(root.left, L, R);
            root = leftNode;
            return root;

        } else {
            TreeNode leftNode = preOrderTraverseForTrim(root.left, L, R);
            TreeNode rightNode = preOrderTraverseForTrim(root.right, L, R);
            root.left = leftNode;
            root.right = rightNode;
            return root;
        }
    }
}


class Tree {

    TreeNode root;

    public Tree(final int val) {
        root = new TreeNode(val);
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(final int val) {
        this.val = val;
    }
}