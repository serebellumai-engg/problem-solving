
package ds.graphs;

import java.util.*;
public class GraphAdj {
    private ArrayList<ArrayList<Integer>> adjacencyList = null;
    private int size;
    private boolean isUndirected = false;

    public GraphAdj(int size){
        init(size);
    }

    public void init(int size){
        adjacencyList = new ArrayList<ArrayList<Integer>>(size);
        this.size = size;
        for (int i = 0; i < size; i++) 
            adjacencyList.add(new ArrayList<Integer>()); 
    }

    public GraphAdj(int size, boolean isUndirected){
        init(size);
        this.isUndirected = isUndirected;
    }

    public void addEdge(final int u, final int v){
        adjacencyList.get(u).add(v);
        if(isUndirected)
            adjacencyList.get(v).add(u);
    }

    public ArrayList<ArrayList<Integer>> getAdjacencyList(){
        return this.adjacencyList;
    }

    public int getSize(){
        return this.size;
    }

    void printGraph(){
        System.out.println(adjacencyList);
        for(int i=0; i < adjacencyList.size(); i++){
            System.out.print("AdjacencyList of "+i+"->");
            for(int j = 0; j< adjacencyList.get(i).size(); j++){
                System.out.print(adjacencyList.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    void bfs(int s){
        final boolean visited[] = new boolean[size];
        final Queue<Integer> queue = new LinkedList<>();
        visited[s] = true;
        queue.add(s);
        while(!queue.isEmpty()){
            s = queue.poll();
            System.out.print(s + "->");
            final Iterator<Integer> i = adjacencyList.get(s).listIterator(); 
            while(i.hasNext()) {
                final int n = i.next();
                if(!visited[n]){
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        System.out.println();
    }

    void DFSUtil(final int s){
        final boolean visited[] = new boolean[size];
        dfs(s, visited);
    }
    void dfs(final int s, final boolean[] visited){
        if(visited[s]){
            return;
        }
        System.out.print(s + "->");
        visited[s] = true;
        for(int i=0; i< adjacencyList.get(s).size();i++){
            dfs(adjacencyList.get(s).get(i), visited);
        }
    }

    List<Integer> getTopologicalSortDFS(){
       Stack<Integer> st = new Stack<>();
       boolean []visited = new boolean[size];
       for(int i = 0; i< size; i++){
           if(!visited[i]){
               topologicalDFS(i, st, visited);
           }
       }
       List<Integer> output = new LinkedList<>();
       while(!st.isEmpty()){
        output.add(st.pop());
       }
       return output;
    }

    private void topologicalDFS(int v, Stack<Integer> st, boolean []visited){
        visited[v] = true;
        for(Integer adjNodes : adjacencyList.get(v)){   
            if(!visited[adjNodes]){
                topologicalDFS(adjNodes, st, visited);
            }
        }
        st.push(v);
    }

    //kahn's algorithm queue ingress approach for topological sort
    List<Integer> getTopologicalSortBFS(){
        int []ingress = new int[size];
        for(int i = 0; i< size; i++) {
            List<Integer> adjNodes = adjacencyList.get(i);
            for(Integer adjNode : adjNodes){
                ingress[adjNode]++;
            }
        }
         
        Queue<Integer> q = new LinkedList<>();
        for(int j = 0; j< size; j++){
            if(ingress[j] == 0)
                q.add(j);
        }

        int count = 0;
        List<Integer> output = new LinkedList<>();
        while(!q.isEmpty()){
            int node = q.poll();
            output.add(node);
            for(int adj : adjacencyList.get(node)){
                if(--ingress[adj] == 0)
                    q.add(adj);
            }
            count++;
        }
        if(count != size){
            System.err.print("Graph have cycles");
        }
        return output;
    }
    
     private boolean isCyclicUtil(int v, int parent, boolean[] visited) {
        visited[v] = true;
        for (Integer it : adjacencyList.get(v)) {
            if (!visited[it]) {
                if (isCyclicUtil(it, v, visited)) {
                    return true;
                }
            } else if (it != parent) {
                return true;
            }
        }
        return false;
    }

    boolean isUndirectedCycle() {
        boolean[] visited = new boolean[size + 1];
        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                if (isCyclicUtil(i, -1, visited)) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean isCyclicUtil(int v, boolean[] visited, boolean[] dfsVisited) {
        dfsVisited[v] = true;
        visited[v] = true;
        for (Integer it : adjacencyList.get(v)) {
            if (!visited[it]) {
                if (isCyclicUtil(it, visited, dfsVisited)) {
                    return true;
                }
            } else if (dfsVisited[it]) {
                return true;
            }
        }
        dfsVisited[v] = false;
        return false;
    }

    boolean isDirectedCycle() {
        boolean[] visited = new boolean[size+1];
        boolean[] dfsVisited = new boolean[size+1];
        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                if (isCyclicUtil(i, visited, dfsVisited)) {
                    return true;
                }
            }
        }
        return false;
    }
}
