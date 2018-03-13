import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;
import java.util.Arrays;

class MazeGenerator {
    
    private Stack<Node> stack = new Stack<>();
    private Random rand = new Random();
    private int[][] maze;
    private int dimension;

    MazeGenerator(int dim) {
        maze = new int[dim][dim];
        dimension = dim;
    }

    public void generateMaze() {
        stack.push(new Node(0,0));
        while (!stack.empty()) {
            Node next = stack.pop();
            if (maze[next.x][next.y] != 1 && legalMove(next)) {
                maze[next.x][next.y] = 1;
                ArrayList<Node> neighbors = findNeighbors(next);
                randomlyAddNodesToStack(neighbors);
            }
        }
    }

    public String rawMaze() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : maze) {
            sb.append(Arrays.toString(row) + "\n");
        }
        return sb.toString();
    }

    public String symbolicMaze() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                sb.append(maze[i][j] == 1 ? " " : "*");
                sb.append("  "); 
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private boolean legalMove(Node node) {
        int numNeighboringOnes = 0;
        for (int r = node.x-1; r < node.x+2; r++) {
            for (int c = node.y-1; c < node.y+2; c++) {
                if (r >= 0 && c >= 0 && r < dimension && c < dimension
                    && !(r == node.x && c == node.y) && maze[r][c] == 1) {
                    numNeighboringOnes++;
                }
            }
        }
        return (numNeighboringOnes < 3);
    }

    private void randomlyAddNodesToStack(ArrayList<Node> nodes) {
        int targetIndex;
        while (!nodes.isEmpty()) {
            targetIndex = rand.nextInt(nodes.size());
            stack.push(nodes.remove(targetIndex));
        }
    }

    private ArrayList<Node> findNeighbors(Node node) {
        ArrayList<Node> neighbors = new ArrayList<>();
        for (int r = node.x-1; r < node.x+2; r++) {
            for (int c = node.y-1; c < node.y+2; c++) {
                if (r >= 0 && c >= 0 && r < dimension && c < dimension
                    && (r == node.x || c == node.y)
                    && !(r == node.x && c == node.y)) {
                        neighbors.add(new Node(r,c));
                    }
            }
        }
        return neighbors;
    }
}