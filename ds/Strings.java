package ds;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
public class Strings {
    
    public static void main(String args[]){
        String []A = {"bella","label","roller"};
        System.out.println(commonChars(A));
        String abc = "Avinash";
        System.out.println(abc.substring(0, 2));
    }

    public static List<String> commonChars(String[] A) {
      int originalCount[] = new int[26];
      for(char ch : A[0].toCharArray()){
          int pointer = (int) ch;
          originalCount[pointer-97]++;
      }
      for(int i = 1; i< A.length; i++){
          int localCount[] = new int[26];
          for(char ch : A[i].toCharArray()){
            int pointer = (int) ch;
              if(originalCount[pointer-97] > 0){
                  if(localCount[pointer-97] >= originalCount[pointer-97]){
                      continue;
                  }
                  localCount[pointer - 97]++;
              }
          }
          for(int j=0 ; j < 26 ;j++){
              if(localCount[j] < originalCount[j]){
                  originalCount[j] = localCount[j];
              } 
          }
      }
      List<String> lists = new LinkedList<>();      
      for(int i =0; i< originalCount.length; i++){
          int a= 97 + i;  
          char c=(char) a;  
          for(int j=0; j< originalCount[i]; j++){
              lists.add(""+ c);
          }
      }
      return lists;
  }

    public static boolean isValidParentheses(String s){        
        Stack<Character> st = new Stack<Character>();
        for(char ch : s.toCharArray()){
            char first;
            switch(ch) {
                case '{': st.push(ch); break;
                case '[': st.push(ch);break;
                case '(': st.push(ch); break;

                case '}': first = !st.isEmpty() ? st.peek() : 'n'; 
                          if(first == 'n') { return true;}
                          if(first!='{'){
                            return false;
                          } st.pop();break;
                case ']': first = !st.isEmpty() ? st.peek() : 'n'; 
                          if(first == 'n') { return true;}
                          if(first!='['){
                            return false;
                          } st.pop();break;
                case ')': first = !st.isEmpty() ? st.peek() : 'n'; 
                         if(first == 'n') { return true;}
                          if(first!='('){
                            return false;
                          } st.pop();break;
            }

        }
        if(!st.isEmpty()){
            return false;
        }
        return true;
        
    }
}