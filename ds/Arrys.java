package ds;
import java.util.*;

public class Arrys {

    public static void main(String[] args) {
        int arr[] = {17,18,5,4,6,1};
        replaceElements(arr);
        String input = "abcdba";
        System.out.println(possiblePalinByRemovingOneChar(input));
        int a = 1;
        int b = a * (-1);
        System.out.println(b);

    }

    public static void printArray(int []a){
        for(int i = 0; i<a.length; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }


    public List<Integer> findDisappearedNumbers(int[] nums) {
        Set<Integer> numbers = new HashSet<Integer>();
        List<Integer> finalNumbers = new LinkedList<>();
        for(int i =0;i< nums.length;i++){
            numbers.add(nums[i]);
        }
        System.out.println(numbers);
        for(int i=1 ;i<=nums.length; i++){
            if(!numbers.contains(i)){
                finalNumbers.add(i);
            }
        }
        return finalNumbers;
    }

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        for(int i=0; i<arr.length;i++){
            if(countMap.containsKey(arr[i])){
                countMap.put(arr[i], countMap.get(arr[i]) + 1);
            } else {
                countMap.put(arr[i], 1);
            }
        }
        Set<Integer> valueSet = new HashSet<Integer>();
        for(Integer count : countMap.values()){
            if(valueSet.contains(count)){
                return false;
            } else {
                valueSet.add(count);
            }
        }
        return true;
    }

    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        
        int l1 = arr1.length;
        int l2 = arr2.length;
        int l3 = arr3.length;
        List<Integer> finalIntersectionList = new LinkedList<Integer>();
        for(int i=0 ; i< l1  ; i++){
            if(binarySearch(arr2, 0, l2-1,arr1[i])
               && binarySearch(arr3, 0, l3-1, arr1[i])){
                finalIntersectionList.add(arr1[i]);
            }
        }
        return finalIntersectionList;
    }
    
    private boolean binarySearch(int []arr, int l, int r, int x){
        if (r >= l) { 
            int mid = l + (r - l) / 2; 
            if (arr[mid] == x) 
                return true; 
            if (arr[mid] > x) 
                return binarySearch(arr, l, mid - 1, x); 
            return binarySearch(arr, mid + 1, r, x); 
        } 
        return false; 
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> nums1Unique = new HashSet<Integer>();
        Set<Integer> finalUnique = new HashSet<Integer>();
        for(int i=0; i< nums1.length; i++){
            nums1Unique.add(nums1[i]);
        }
        for(int i=0;i<nums2.length; i++){
            if(nums1Unique.contains(nums2[i])){
                finalUnique.add(nums2[i]);
            }
        }
        int []finalArray = new int[finalUnique.size()];
        int i = 0;
        for(Integer in : finalUnique){
            finalArray[i] = in;
            i++;
        }
        return finalArray;
    }

    public static int fixedPoint(int[] A) {
        int index = -1;
        if(A.length < 2){
            if(A[0] == 0){
                return 0;
            }else {
                return -1;
            }
        }
        int startIndex = findSmallestPositiveIndex(A, 0, A.length-1);
        if(startIndex == -1)
            return -1;
        else {
            if(startIndex < 1){
                return 0;
            }
            for(int i = startIndex-1; i<A.length; i++){
                if(A[i] == i){
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
    
    private static int findSmallestPositiveIndex(int []A, int startIndex, int endIndex){
        int mid = (startIndex + endIndex)/2;
        if(A[mid] < 0){
            return findSmallestPositiveIndex(A, mid + 1,endIndex);
        } else if(A[mid] ==0) {
            return mid;
        }else  {
            if(A[mid-1] < 0){
                return mid;
            }
            return findSmallestPositiveIndex(A, startIndex, mid-1);
        }
    }

    public static int largestUniqueNumber(int[] A) {
        Arrays.sort(A);
        int length = A.length;
        if(length < 2){
            return A[0];
        }
        if(A[length-1] != A[length-2]){
            return A[length-1];
        }
        for(int i = length-2; i>0;i--){
            if( (A[i] != A[i-1]) && (A[i] != A[i+1])){
                return A[i];
            }
        }
        if(A[0]!= A[1]){
            return A[0];
        }
        return -1;
    }

    //[17,18,5,4,6,1]
    //[18,6,6,6,1,-1]
    public static int[] replaceElements(int[] arr) {
        int length = arr.length;
        int greatestNumberSoFar = arr[length-1];
        arr[length-1] = -1;
        if(length < 2){
            return arr;
        }
        for(int i = arr.length-2; i>=0;i--){
            if(arr[i] > greatestNumberSoFar){
                int temp = arr[i];
                arr[i] = greatestNumberSoFar;
                greatestNumberSoFar = temp;
            } else {
                arr[i] = greatestNumberSoFar;
            } 
        }
        return arr;
    }

    public static void getRow(int rowIndex) {
        double value = Math.pow(11, rowIndex);
        System.out.println(value);
    }

    public static int singleNumber(int[] nums) {
        int finalValue = nums[0];
        for(int i =1;i< nums.length; i++){
            finalValue = (finalValue ^ nums[i]);
        }
        return finalValue;
    }

    public static int fib(int N) {
        int arr[] = new int[N+1];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 1;
        for(int i=3;i<= N; i++){
            arr[i] = arr[i-1]+ arr[i-2];
        }
        return arr[N];
    }

    public static int reverse(int x) {
        int finalNumber = 0;
        boolean isNegative = x < 0 ? true : false;
        while(x!=0){
            int rem = x%10;
            x = x/10;
            finalNumber = finalNumber*10 + rem;
        }
        System.out.println(isNegative);
        if(isNegative) {
            finalNumber = finalNumber - (2*finalNumber);
        }
        System.out.println(finalNumber);
        return finalNumber;
    }

    public static int removeElementInPlace(int []a, int x){
        for(int i=0;i<a.length;i++){
            System.out.print(a[i] +" ");
        }
        System.out.println();
        int startIndex = 0;
        int endIndex = a.length-1;
        int count = 0;
        while(startIndex < endIndex){
            if(a[startIndex] ==x && a[endIndex]!= x){
                int temp = a[startIndex];
                a[startIndex] = a[endIndex];
                a[endIndex] = temp;
                startIndex++;
                endIndex--;
                count++;
            } else if(a[startIndex] != x && a[endIndex] == x) {
                startIndex++;
                endIndex--;
                count++;
            } else if(a[startIndex] != x && a[endIndex] != x) {
                startIndex++;
            } else if(a[startIndex] ==x && a[endIndex]==x) {
               endIndex--;
            } else{
                startIndex++;
                endIndex--;
            }
        }
        for(int i=0;i<a.length;i++){
            System.out.print(a[i] +" ");
        }
        System.out.println();
        return (a.length-count);
    }
    public static boolean isPalindrome(int x) {
    
        StringBuilder str = new StringBuilder();
        if(x < 0){
            return false;
        } else if(x ==0){
            return true;
        }
        while(x!=0){
            int rem = x%10;
            x= x/10;
            str.append(rem);
        }
        int startIndex = 0;
        int endIndex = str.length() -1;
        boolean isPalindrome = true;
        System.out.println(str);
        System.out.println(startIndex);
        System.out.println(endIndex);
        while(startIndex < endIndex ){
            if(str.charAt(startIndex) == (str.charAt(endIndex))){
                startIndex++;
                endIndex--;
                continue;
            } else{
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }



    public static boolean isPalindromeWithoutString(int x) {
        if(x < 0){
            return false;
        }
        StringBuilder sb = new StringBuilder();
        while(x != 0){
            int rem = x%10;
            x= x/10;
            sb.append(rem);
        }
        int convertedNumber = Integer.parseInt(sb.toString());
        System.out.println(convertedNumber);
        System.out.println(x);
        if(convertedNumber == x){
            return true;
        } else {
            return false;
        }
    }

    public static boolean findPairWithGivenSum(int []a, int x){
        Arrays.sort(a);
        int startIndex = 0, endIndex = a.length-1;
        while(startIndex < endIndex){
            if(a[startIndex] + a[endIndex] == x){
                return true;
            } else if(a[startIndex] + a[endIndex] < x){
                startIndex++;
            } else if(a[startIndex] + a[endIndex] > x){
                endIndex--;
            }
        }
        return false;
    }

    public static boolean findContigousSubArray(int[] a, int sum) {
        int sumSoFar = a[0];
        int startIndex = 0;
        int n = a.length;
        if (sum == sumSoFar) {
            return true;
        }
        for(int i=1;i<n;i++){
            while(sumSoFar > sum && startIndex < i-1){
                    sumSoFar = sumSoFar - a[startIndex];
                    startIndex++;
            }
            if(sumSoFar == sum){
                return true;
            }
            if(i < n){
                sumSoFar = sumSoFar + a[i];
            }
        }

        return false;
    }

    static boolean isPalindromeII(String str,  
                       int low, int high) 
    { 
        while (low < high)  
        { 
            if (str.charAt(low) != str.charAt(high)) 
                return false; 
            low++; 
            high--; 
        } 
        return true; 
    } 
  
    // This method returns -1 if it is  
    // not possible to make string a palindrome.  
    // It returns -2 if string is already  
    // a palindrome. Otherwise it returns  
    // index of character whose removal can 
    // make the whole string palindrome. 
    static int possiblePalinByRemovingOneChar(String str) { 
        //abcdba
        int low = 0, high = str.length() - 1; 
        while (low < high)  {
            if (str.charAt(low) == str.charAt(high))  { 
                low++; 
                high--; 
            }  
            else{ 
                if (isPalindromeII(str, low + 1, high)) 
                    return low; 
                if (isPalindromeII(str, low, high - 1)) 
                    return high; 
                return -1; 
            } 
        } 
        return -2; 
    } 
    
}