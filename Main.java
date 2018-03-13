public class Main {
    public static void main(String[] args) {
        MazeGenerator mazeGenerator = new MazeGenerator(20);
        mazeGenerator.generateMaze();

        System.out.println("RAW MAZE\n" + mazeGenerator.rawMaze());
        System.out.println("SYMBOLIC MAZE\n" + mazeGenerator.symbolicMaze());
    }
}