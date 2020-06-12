package ds;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graphs {

    public static void main(final String []args){
        GraphAdj adj = new GraphAdj(6);
        adj.addEdge(5,0);
        adj.addEdge(5,2);
        adj.addEdge(2,3);
        adj.addEdge(3,1); 
        adj.addEdge(4,1);
        //adj.printGraph();
        System.out.println();
        System.out.println(adj.getTopologicalSortDFS());
        System.out.println();
        System.out.println(adj.getTopologicalSortBFS());
    

        /*GraphMatrix mat = new GraphMatrix(5);
        mat.addEdge(0,1);
        mat.addEdge(0,2);
        mat.addEdge(1,4);
        mat.addEdge(2,4); 
        mat.addEdge(3,4);
        mat.addEdge(0,4);
        mat.printGraph();
        final GraphMatrix mat = new GraphMatrix(5);
        final int matrix [][] = { { 1, 1, 0, 0, 0 }, 
        { 0, 1, 0, 0, 1 }, 
        { 1, 0, 0, 1, 1 }, 
        { 0, 0, 0, 0, 0 }, 
        { 1, 0, 1, 0, 1 } };
        mat.graphMatrix = matrix;
        /*mat.addEdge(0,1);
        mat.addEdge(0,2);
        mat.addEdge(1,4);
        mat.addEdge(2,4); 
        mat.addEdge(3,4);
        mat.addEdge(0,4);*/
        //System.out.println("Count is "+mat.countIslands());
    }
}

class GraphMatrix{
    int graphMatrix[][];
    private final int size;
    public GraphMatrix(final int size){
        graphMatrix = new int[size][size];
        this.size = size;
    }

    void addEdge(final int u, final int v){
        graphMatrix[u][v] = 1;
        graphMatrix[v][u] = 1;
    }

    void printGraph(){
        for(int i=0;i<size;i++){
            System.out.print("AdjacencyMatrix of "+i+"->");
            for(int j=0;j<size; j++){
                if(graphMatrix[i][j] == 1){
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }



    boolean isSafe(final int row, final int column, final boolean [][]visited){
        return (row >= 0) && (row <size) && (column>=0) && (column <size) && graphMatrix[row][column]== 1 && !visited[row][column];
    }

    void dFS(final int row, final int column, final boolean visited[][]){
        final int rowNbr[] = new int[] { -1, -1, -1, 0, 0, 1, 1, 1 }; 
        final int colNbr[] = new int[] { -1, 0, 1, -1, 1, -1, 0, 1 }; 
        visited[row][column] = true;
        for(int k = 0; k< 8; k++){
            if(isSafe(row + rowNbr[k], column + colNbr[k], visited)){
                dFS(row + rowNbr[k], column + colNbr[k], visited);
            }
        }
    }

    int countIslands(){

        final boolean visited[][] = new boolean[size][size];
        int count = 0;
        for(int i = 0; i< size; i++){
            for(int j = 0; j< size; j++){
                if(graphMatrix[i][j] ==1 && !visited[i][j]){
                    dFS(i, j, visited);
                    ++count;
                }
            }
        }
        return count;
    }
}

class GraphAdj {
    private ArrayList<ArrayList<Integer>> adjacencyList = null;
    private int size;

    public GraphAdj(int size){
        adjacencyList = new ArrayList<ArrayList<Integer>>(size);
        this.size = size;
        for (int i = 0; i < size; i++) 
            adjacencyList.add(new ArrayList<Integer>()); 
    }

    void addEdge(final int u, final int v){
        adjacencyList.get(u).add(v);
        //adjacencyList.get(v).add(u);
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
}