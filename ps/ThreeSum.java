public class ThreeSum {
   public List<List<Integer>> threeSum(int[] nums) {
      if (nums == null || nums.length == 0) return new LinkedList<>();
      Arrays.sort(nums); //O(nLog(n))
      Set<Triplet> tripletSet = new HashSet<>();
      for(int i = 0; i< nums.length-2;i++){ //O(N^2)
          int a = nums[i];
          int start = i+1;
          int end = nums.length-1;
          while(start < end){
              int b = nums[start];
              int c = nums[end];
              if(a + b + c == 0){
                  Triplet t = new Triplet(a,b,c);
                  tripletSet.add(t);
                  start++;
                  end--;
              } else if (a + b + c < 0) {
                  start++;
              } else {
                  end--;
              }
          }
      }
      List<List<Integer>> triplets = new ArrayList<>(tripletSet.size());
      for(Triplet t : tripletSet){
          List<Integer> triplet = new LinkedList<>();
          triplet.add(t.a);
          triplet.add(t.b);
          triplet.add(t.c);
          triplets.add(triplet);
      }
      return triplets;
   }    
}

class Triplet {
    int a,b,c;

    public Triplet(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triplet triplet = (Triplet) o;
        return a == triplet.a && b == triplet.b && c == triplet.c;
    }

    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
