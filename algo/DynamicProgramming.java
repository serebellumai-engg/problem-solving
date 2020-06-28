package algo;
public class DynamicProgramming{

    public static void main(String []args){
        int []coins = {1,2,3};
        MinimumCoinChange coinChange = new MinimumCoinChange(coins, 5);
        System.out.println("Minimum number of coin changes " + coinChange.minCoinChange());
        int []array = {-2, -3, 4, -1, -2, 1, 5, -3};
        MaximumSubSequenceSum subSequence = new MaximumSubSequenceSum(array);
        System.out.println("Maximum sub sequence sum is " + subSequence.getMaximumSubSequenceSum());
    }
}

class MinimumCoinChange{

    int change;
    int []coins;
    public MinimumCoinChange(int []coins, int change){
        this.change = change;
        this.coins = coins;
    }
    
    //O(mV) => m is number of coins and V is the required change
    public int minCoinChange(){
        int table[] = new int[change + 1];
        for(int i = 0; i<table.length;i++){
            table[i] = Integer.MAX_VALUE;
        }
        table[0] = 0;
        for(int i = 1; i<= change; i++){
            for(int j =0 ; j < coins.length; j++){
                if(coins[j] <= i){
                    int subResult = table[i-coins[j]];
                    if(subResult != Integer.MAX_VALUE && subResult + 1 < table[i]){
                        table[i] = subResult + 1;
                    }
                }
            }
        }
        return table[change];
    }
}

class MaximumSubSequenceSum{

    int []array;
    public MaximumSubSequenceSum(int []array){
        this.array = array;
    }

    public int getMaximumSubSequenceSum(){
        int length = array.length;
        int []tableSum = new int[length];
        tableSum[0] = array[0];
        for(int i = 1; i<length; i++){
            int maxSum = Math.max(array[i], tableSum[i-1] + array[i]);
            tableSum[i] = maxSum;
        }
        int maxSum = Integer.MIN_VALUE;
        for(int j = 0; j<length; j++){
            if(tableSum[j] > maxSum){
                maxSum = tableSum[j];
            }
        }
        return maxSum;
    }

}
