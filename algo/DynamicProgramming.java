package algo;
public class DynamicProgramming{

    public static void main(String []args){
        int []coins = {1,2,3};
        MinimumCoinChange coinChange = new MinimumCoinChange(coins, 5);
        System.out.println(coinChange.minCoinChange());
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
