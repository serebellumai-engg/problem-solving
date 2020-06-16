package algo;


public class BackTracking {

    public static void main(String []args){
        int maze[][] = { { 1, 0, 0, 0 }, 
                         { 1, 1, 0, 1 }, 
                         { 0, 1, 0, 0 }, 
                         { 1, 1, 1, 1 } }; 
  
        RatMaze ratMaze = new RatMaze(maze);
        ratMaze.solve();
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