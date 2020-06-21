package algo;

import java.util.*;
import ds.graphs.GraphAdj;
import ds.graphs.GraphMatrix;

public class GraphAlgorithms {


    public static void main(String []args){
        GraphAlgorithms algos = new GraphAlgorithms();
        algos.printShortestPathFromSourceUndirectedGraph();
        DijkstraShortestPath shortestPath = new DijkstraShortestPath(5);
        shortestPath.dijkstra(0);
    }

    List<Integer> getTopologicalSortDFS(GraphAdj adj){
        Stack<Integer> st = new Stack<>();
        int size = adj.getSize();
        boolean []visited = new boolean[size];
        for(int i = 0; i< size; i++){
            if(!visited[i]){
                topologicalDFS(adj, i, st, visited);
            }
        }
        List<Integer> output = new LinkedList<>();
        while(!st.isEmpty()){
         output.add(st.pop());
        }
        return output;
     }
 
     private void topologicalDFS(GraphAdj adj, int v, Stack<Integer> st, boolean []visited){
         visited[v] = true;
         for(Integer adjNodes : adj.getAdjacencyList().get(v)){   
             if(!visited[adjNodes]){
                 topologicalDFS(adj, adjNodes, st, visited);
             }
         }
         st.push(v);
     }
 
     //kahn's algorithm queue ingress approach for topological sort
     List<Integer> getTopologicalSortBFS(GraphAdj adjGraph){
         int size = adjGraph.getSize();
         int []ingress = new int[size];
         for(int i = 0; i< size; i++) {
             List<Integer> adjNodes = adjGraph.getAdjacencyList().get(i);
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
             for(int adj : adjGraph.getAdjacencyList().get(node)){
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


     public int countIslands(GraphMatrix gMatrix){
        int size = gMatrix.getSize();
        final boolean visited[][] = new boolean[size][size];
        int count = 0;
        for(int i = 0; i< size; i++){
            for(int j = 0; j< size; j++){
                if(gMatrix.getMatrix()[i][j] ==1 && !visited[i][j]){
                    gMatrix.dFS(i, j, visited);
                    ++count;
                }
            }
        }
        return count;
    }

    private boolean shortestPathBFS(GraphAdj adj,  int source, int destination, int pred[], int dist[]){
        int size = adj.getSize();
        boolean []visited = new boolean[size];
        for(int i=0; i<size; i++){
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }  
        visited[source] = true;
        dist[source] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        while(!q.isEmpty()){
                int u = q.remove();
                for(int j =0; j< adj.getAdjacencyList().get(u).size(); j++){
                    if(!visited[adj.getAdjacencyList().get(u).get(j)]){
                        visited[adj.getAdjacencyList().get(u).get(j)] = true;
                        dist[adj.getAdjacencyList().get(u).get(j)] = dist[u] + 1;
                        pred[adj.getAdjacencyList().get(u).get(j)] = u;
                        q.add(adj.getAdjacencyList().get(u).get(j));
                        if(adj.getAdjacencyList().get(u).get(j) == destination){
                            ds.Arrys.printArray(pred);
                            return true;
                        }
                            
                    }
                }
        }
          
        return false;
    }

    public void printShortestPathFromSourceUndirectedGraph(){
        int size = 8;
        GraphAdj adj = new GraphAdj(size, true);

        adj.addEdge( 0, 1); 
        adj.addEdge( 0, 3); 
        adj.addEdge( 1, 2); 
        adj.addEdge( 3, 4); 
        adj.addEdge( 3, 7); 
        adj.addEdge( 4, 5); 
        adj.addEdge( 4, 6); 
        adj.addEdge( 4, 7); 
        adj.addEdge( 5, 6); 
        adj.addEdge( 6, 7); 

        int source = 0, destination = 7;
        int []pred = new int[size];
        int []dist = new int[size];
        if(!shortestPathBFS(adj, source, destination, pred, dist)){
            System.err.println("No path exist between source and destination");
        }
        ds.Arrys.printArray(pred);
        LinkedList<Integer> path = new LinkedList<>();
        int crawl = destination;
        path.add(crawl);
        while(pred[crawl] != -1){
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }
        System.out.println("Shortest path length is: " + dist[destination]); 
        
        
        for(int k = path.size()-1; k >=0; k--){
            System.out.println(path.get(k) + " ");
        }
    }
}

class DijkstraShortestPath {

    private int V;
    private Set<Integer> settled;
    private PriorityQueue<WeightedNode> pq;
    private int []dist;
    private List<List<WeightedNode>> adj;

    public DijkstraShortestPath(int V){
        this.V = V;
        dist = new int[V];
        pq = new PriorityQueue<WeightedNode>(V, new WeightedNode());
        settled = new HashSet<>();
        init();
    }

    private void init(){
        adj = new ArrayList<List<WeightedNode>>(); 
        for (int i = 0; i < V; i++) { 
            List<WeightedNode> item = new ArrayList<WeightedNode>(); 
            adj.add(item); 
        } 
  
        // Inputs for the DPQ graph 
        adj.get(0).add(new WeightedNode(1, 9)); 
        adj.get(0).add(new WeightedNode(2, 6)); 
        adj.get(0).add(new WeightedNode(3, 5)); 
        adj.get(0).add(new WeightedNode(4, 3)); 
        adj.get(2).add(new WeightedNode(1, 2)); 
        adj.get(2).add(new WeightedNode(3, 4)); 
    }

    public void dijkstra(int src){
        for(int i=0; i<V; i++)
            dist[i] = Integer.MAX_VALUE;
        pq.add(new WeightedNode(src, 0));
        dist[src] = 0;
        while(settled.size() != V){
            int u = pq.remove().node;
            settled.add(u);
            processNeighbours(u);
        }

        System.out.println("The shorted path from node :"); 
        for (int i = 0; i < dist.length; i++) 
            System.out.println(src + " to " + i + " is "
                               + dist[i]); 
    }

    private void processNeighbours(int u){
        int edgeDistance = -1, newDistance = -1;
        for(int i=0;i< adj.get(u).size();i++){
            WeightedNode v = adj.get(u).get(i);
            if(!settled.contains(v.node)){
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;
                if(newDistance < dist[v.node]){
                    dist[v.node] = newDistance;
                }
                pq.add(new WeightedNode(v.node, dist[v.node]));
            }
        }
    }
}

class WeightedNode implements Comparator<WeightedNode>{
    int node;
    int cost;

    public WeightedNode(){

    }

    public WeightedNode(int node, int cost){
        this.node = node;
        this.cost = cost;
    }

    public int compare(WeightedNode node1, WeightedNode node2){
        if(node1.cost < node2.cost)
            return -1;
        if(node1.cost > node2.cost)
            return 1;
        return 0;
    }

}

