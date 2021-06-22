/*https://leetcode.com/problems/circular-array-loop/*/
public class CircularArrayLoop {
  public static boolean circularArrayLoop(int[] nums) {
        for(int i = 0; i < nums.length; ++i){
            int cur = i, count = 0, dir = 0, prev = i;
            while(nums[cur] <= 1000){
                prev = cur;
                int t = (cur + nums[cur])%nums.length;
                if(nums[cur] < 0){
                    cur = t < 0 ? (nums.length + t) : t;
                    dir = (dir == 0 || dir == 1) ? 1 : 3;
                }else{
                    cur = t;
                    dir = (dir == 0 || dir == 2) ? 2 : 3;
                }
                if(dir == 3) break;
                nums[prev]  = 1001 + i;
                if(cur == prev) break;
            }
            if(prev != cur && nums[cur] == (1001 + i) && dir != 3) return true;
        }
        return false;
    }
}
