package ds.graphs;

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

