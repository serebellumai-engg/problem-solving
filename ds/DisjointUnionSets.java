package ds;

public class DisjointUnionSets {

    int []rank, parent;
    int n;
    public DisjointUnionSets(int n){
        this.n = n;
        rank = new int[n];
        parent = new int[n];
        makeSet();
    }

    private void makeSet(){
        for(int i = 0; i<n; i++){
            parent[i] = i;
        }
    }
        

    public int find(int i) {
        if(parent[i] != i){
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    public void union(int i, int j) {
        int iRoot = find(i);
        int jRoot = find(j);
        if(rank[iRoot] < rank[jRoot]){
            parent[iRoot] = jRoot;
        } else if(rank[jRoot] < rank[iRoot]){
            parent[jRoot] = iRoot;
        } else {
            parent[jRoot] = iRoot;
            rank[iRoot] += 1;
        }
    }
    
    public static void main(String []args){
        DisjointUnionSets ds = new DisjointUnionSets(5);
        ds.union(0,2);
        ds.union(4,2);
        ds.union(3,1);
        if(ds.find(4) == ds.find(0)){
            System.out.println("4 and 0 are connected");
        } else {
            System.out.println("4 and 0 are not connected");
        }
        if(ds.find(0) == ds.find(3)){
            System.out.println("3 and 0 are connected");
        } else {
            System.out.println("3 and 0 are not connected");
        }
    }
}