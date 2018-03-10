import java.util.*;
import java.io.*;

public class Main {

    static int dim = 30;
    static int[][] M = new int[dim][dim];

    public static void main(String[] args) {
        Stack<Node> stack = new Stack<>();
        Random rand = new Random();
        
        stack.push(new Node(0,0));
        while (!stack.empty()) {
            Node next = stack.pop();
            if (M[next.x][next.y] != 1 && legalMove(next)) {
                M[next.x][next.y] = 1;
                ArrayList<Node> neighbors = new ArrayList<>();
                for (int r = next.x-1; r < next.x+2; r++) {
                    for (int c = next.y-1; c < next.y+2; c++) {
                        if (r >= 0 && c >= 0 && r < M.length && c < M[0].length
                            && (r == next.x || c == next.y)
                            && !(r == next.x && c == next.y) ) {
                                neighbors.add(new Node(r,c));
                            }
                        
                    }
                }
                while (!neighbors.isEmpty()) {
                    int targetIndex = rand.nextInt(neighbors.size());
                    stack.push(neighbors.get(targetIndex));
                    neighbors.remove(targetIndex);
                }
            }
        }

        for (int i = 0; i < M.length; i++) {
            System.out.println(Arrays.toString(M[i]));
        }
        printMaze(M);
    }

    static boolean legalMove(Node node) {
        int numNeighboringOnes = 0;
        for (int r = node.x-1; r < node.x+2; r++) {
            for (int c = node.y-1; c < node.y+2; c++) {
                if (r >= 0 && c >= 0 && r < M.length && c < M[0].length
                    && !(r == node.x && c == node.y) && M[r][c] == 1) {
                    numNeighboringOnes++;
                }
            }
        }
        return (numNeighboringOnes < 3);
    }

    static void printMaze(int[][] maze) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                sb.append(M[i][j] == 1 ? " " : "*");
                sb.append("  "); 
            }
            sb.append("\n");
        }
        String mazeString = sb.toString();
        System.out.println(mazeString);
    }
}