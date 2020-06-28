package algo;


public class BackTracking {

    public static void main(String []args){
        int maze[][] = { { 1, 0, 0, 0 }, 
                         { 1, 1, 0, 1 }, 
                         { 0, 1, 0, 0 }, 
                         { 1, 1, 1, 1 } }; 
  
        RatMaze ratMaze = new RatMaze(maze);
        ratMaze.solve();

        int []input = { 2, 4, 10, 1, 99, 3};
        SubSetSum subSetSum = new SubSetSum(input, 5);
        subSetSum.solve();

        int mColor[][] = { 
            { 0, 1, 1, 1 }, 
            { 1, 0, 1, 0 }, 
            { 1, 1, 0, 1 }, 
            { 1, 0, 1, 0 }, 
        }; 
        GraphMColoring mColoring = new GraphMColoring(mColor, 3);
        mColoring.graphColoring();

        int hamGraph[][] = {{0, 1, 0, 1, 0}, 
            {1, 0, 1, 1, 1}, 
            {0, 1, 0, 0, 1}, 
            {1, 1, 0, 0, 1}, 
            {0, 1, 1, 1, 0}, 
        }; 
        HamiltonianCycle hCycle = new HamiltonianCycle(hamGraph, 5);
        hCycle.verifyIfHamiltonian();
    }
}

class RatMaze {
/**
 *  Time Complexity: O(2^(n^2)).
    The recursion can run upperbound 2^(n^2) times.
    Space Complexity: O(n^2).
    Output matrix is required so an extra space of size n*n is needed.
 */

    int [][]maze = null;
    int N = 0;
    public RatMaze(int [][]maze){
        this.maze = maze;
        N = maze.length;
    }

    public boolean solve(){
        int [][]solution = new int[N][N];
        if(!solveMaze(maze, 0, 0, solution)){
            return false;
        }
        printOutput(solution);
        return true;
    }

    private void printOutput(int [][]solution){
        for(int i = 0; i< N; i++){
            for(int j=0; j< N; j++){
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean isSafe(int maze[][], int i, int j){
        return i >= 0 && i <N && 
        j >=0 && j < N && 
        maze[i][j]== 1;
    }

    private boolean solveMaze(int [][]maze, int i, int j, int [][]solution){
        if(i == N-1 && j == N-1 && maze[i][j] == 1){
            solution[i][j] = 1;
            return true;
        }
        if(isSafe(maze, i, j)) {
            solution[i][j] = 1;
            if(solveMaze(maze, i+1,j, solution)){
                return true;
            }
            if(solveMaze(maze, i, j+1, solution)){
                return true;
            }
            solution[i][j] = 0;
            return false;
        }
        return false;
    }

}


class SubSetSum {

    /**
     * Time Complexity: O(2^(n^2)).The recursion can run upperbound 2^(n^2) times.
     */
    private int[] input = null;
    private int targetSum;
    private int subSetCount;

    public SubSetSum(int []input, int targetSum){
        this.input = input;
        this.targetSum = targetSum;
    }
  

    public void solve(){
        solveSubSet(0, 0);
        System.out.println("Number of subsets "+subSetCount);
    }

    //{ 2, 4, 10, 1, 99, 3};
    private void solveSubSet(int startIndex, int sumSoFar){
        if(targetSum == sumSoFar){
            subSetCount++;
            if(startIndex < input.length){
                solveSubSet(startIndex, sumSoFar - input[startIndex-1]);
            }
        } else {
            for(int i = startIndex; i < input.length;i++){
                solveSubSet(i+1, sumSoFar + input[i]);
            }
        }
    }
}

class GraphMColoring {


    private int[][] adjMatrix = null;
    private int V;
    private int m;
    private int[] color;

    public GraphMColoring(int[][] adjMatrix, int m){
        this.adjMatrix = adjMatrix;
        V = 4;
        this.m = m;
        color = new int[V];
    }

    public void graphColoring(){
        if(!isGraphColoringPossible(0)){
            System.err.println("Solution not possible");
            return;
        }
        System.out.print("Graph Coloring is");
        for(int i = 0 ;i<V;i++){
            System.out.print(color[i] + " ");
        }
        System.out.println();
    }

    private boolean isValid(int v, int c){
        for(int i = 0; i <V; i++){
            if(adjMatrix[v][i] == 1 && c == color[i])
                return false;
        }
        return true;
    }

    private boolean isGraphColoringPossible(int v){
        if(v == V){
            return true;
        }
        for(int i = 1; i <= m ;i++){
            if(isValid(v, i)){
                color[v] = i;
                if(isGraphColoringPossible(v+1))
                    return true;
                color[v] = 0;
            }
        }
        return false;
    }
}

class HamiltonianCycle {

    private int [][]adjMatrix = null;
    private int vertex;
    private int []path;
    

    public HamiltonianCycle(int [][]adjMatrix, int vertex){
        this.adjMatrix = adjMatrix;
        this.vertex = vertex;
        path = new int[vertex];
    }

    private boolean isValidMove(int position, int v) {
        if(adjMatrix[path[position-1]][v] == 0){
            return false;
        }
        for(int i = 0; i < position ; i++){
            if(path[i] == v)
                return false;
        }
        return true;
    }

    public void verifyIfHamiltonian(){
        for(int i = 0; i<vertex; i++){
            path[i] = -1;
        }
        path[0] = 0;
        if(!isHamiltonian(1)){
            System.err.println("No hamiltonian graph possible");
            return;
        }
        System.out.println("Solution exists");
        for(int i = 0; i< vertex; i++){
            System.out.print(path[i] + " ");
        }
        System.out.println(path[0]);
    }

    private boolean isHamiltonian(int position){

        if(position == vertex){
            if(adjMatrix[path[position-1]][path[0]] == 1){
                return true;
            } else {
                return false;
            }
        }
        for(int v = 1; v < vertex; v++){
            if(isValidMove(position, v)){
                path[position] = v;
                if(isHamiltonian(position +1)){
                    return true;
                }
                path[position]= -1;
            }
        }
        return false;
    }
}