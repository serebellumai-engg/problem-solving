package ds;

public class Tries{

    public static void main(String []args){
        TrieNode root = new TrieNode();
        insert(root, "avinash");
        insert(root, "aviral");
        insert(root, "girish");
        System.out.println("avi " + search(root, "av.nas"));
    }

    public static void insert(TrieNode root, String word) {
        char []wordArr = word.toCharArray();
        TrieNode currentNode = root;
        for(int i=0;i< wordArr.length; i++){
            int index  = (int) wordArr[i] - 'a';
            if(currentNode.children[index] == null){
                currentNode.children[index] = new TrieNode();
            }
            currentNode = currentNode.children[index];
        }
        currentNode.isEndOfWord = true;  
    }

    /**
     * Recursive Search
     * @param root
     * @param word
     * @return
     */
    public static boolean search(TrieNode root, String word){
        if(word == null || word.isEmpty()) {
            return root.isEndOfWord;
        }
        char ch = word.charAt(0);
        int index  = (int)ch - 'a';
        if(index < 0 || root.children[index] == null)
            return false;
        return search(root.children[index], word.substring(1));   
    }

    public static boolean searchIterative(TrieNode root, String word) {
        char []wordArr = word.toCharArray();
        TrieNode currentNode = root;
        for(int i=0;i< wordArr.length; i++){
            int index  = (int) wordArr[i] - 'a';
            if(index < 0){
                return false;
            }
            if(currentNode.children[index] == null)
                return false;
            currentNode = currentNode.children[index];
        }
        return currentNode != null && currentNode.isEndOfWord;
    }
}

class TrieNode{

    TrieNode []children = new TrieNode[26];
    boolean isEndOfWord;

    TrieNode() {
        children = new TrieNode[26];
    }

}

/**
 * My Leet Code solution for Trie Structure
 */
class WordDictionary {

    TrieNode rootTrieNode;
    /** Initialize your data structure here. */
    public WordDictionary() {
        rootTrieNode = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        char []wordArr = word.toCharArray();
        TrieNode currentNode = rootTrieNode;
        for(int i=0;i< wordArr.length; i++){
            int index  = (int) wordArr[i] - 'a';
            if(currentNode.children[index] == null){
                currentNode.children[index] = new TrieNode();
            }
            currentNode = currentNode.children[index];
        }
        currentNode.isEndOfWord = true;  
    }
    
    private boolean mySearch(TrieNode root, String word){
        if(word == null || word.isEmpty()) {
            return root.isEndOfWord;
        }
        char ch = word.charAt(0);
        if(ch == '.') {
            boolean isFound = false;
            for(TrieNode node : root.children){
                if(node == null){
                    continue;
                }
                isFound = isFound || mySearch(node, word.substring(1));
            }
            return isFound;
        } else {
            int index  = (int)ch - 'a';
            if(index < 0 || root.children[index] == null)
                return false;
            return mySearch(root.children[index], word.substring(1));
        }
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
       return mySearch(rootTrieNode, word);
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */