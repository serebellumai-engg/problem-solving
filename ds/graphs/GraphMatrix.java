package ds.graphs;

public class GraphMatrix{
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

    public int getSize(){
        return size;
    }

    public int[][] getMatrix(){
        return graphMatrix;
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

    public void dFS(final int row, final int column, final boolean visited[][]){
        final int rowNbr[] = new int[] { -1, -1, -1, 0, 0, 1, 1, 1 }; 
        final int colNbr[] = new int[] { -1, 0, 1, -1, 1, -1, 0, 1 }; 
        visited[row][column] = true;
        for(int k = 0; k< 8; k++){
            if(isSafe(row + rowNbr[k], column + colNbr[k], visited)){
                dFS(row + rowNbr[k], column + colNbr[k], visited);
            }
        }
    }

    
}