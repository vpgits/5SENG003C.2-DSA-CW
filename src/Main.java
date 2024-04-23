import java.util.List;

public class Main {
    public static void main(String[] args) {

        // int rows = 10;
        // int cols = 10;
        // Cell[][] grid = new Cell[rows][cols];
        //
        // Cell start = null;
        // Cell end = null;
        //
        //// Initialize the grid
        // for (int i = 0; i < rows; i++) {
        // for (int j = 0; j < cols; j++) {
        // grid[i][j] = new Cell(i, j);
        // }
        // }

        // Set walls and rocks
        // String[] puzzle = {
        // ".....0...S",
        // "....0.....",
        // "0.....0..0",
        // "...0....0.",
        // ".F......0.",
        // ".0........",
        // ".......0..",
        // ".0.0..0..0",
        // "0.........",
        // ".00.....0."
        // };

        // Grid grid = Utils.parser("puzzle.txt");
        //
        //
        // List<Cell> path = ModifiedAStar.findPath(grid, grid.getStart(),
        // grid.getEnd());
        // if (path != null) {
        // // Create a 2D array to represent the grid
        // String[][] gridDisplay = new String[rows][cols];
        // for (int i = 0; i < rows; i++) {
        // for (int j = 0; j < cols; j++) {
        // gridDisplay[i][j] = grid[i][j].getIsObstacle() ? "[0]" : "[ ]";
        // }
        // }
        //
        // // Mark the cells in the path
        // for (Cell cell : path) {
        // if (!cell.equals(start) && !cell.equals(end)) {
        // gridDisplay[cell.getX()][cell.getY()] = "[*]";
        // } else if (cell.equals(start)) {
        // gridDisplay[cell.getX()][cell.getY()] = "[S]";
        // } else {
        // gridDisplay[cell.getX()][cell.getY()] = "[F]";
        // }
        // }
        //
        // // Print the grid
        // for (int i = 0; i < rows; i++) {
        // for (int j = 0; j < cols; j++) {
        // System.out.print(gridDisplay[i][j]);
        // }
        // System.out.println();
        // }
        // System.out.println("Path: " + ModifiedAStar.printPath(path));
        // System.out.println("length: " + path.size());
        // } else {
        // System.out.println("No path found");
        // }

        Grid grid = Utils.parser("benchmark_series/puzzle_2560.txt");
        long startTime = System.currentTimeMillis();
        List<Cell> path = ModifiedAStar.findPath(grid.getGrid(), grid.getStart(), grid.getEnd());
        long endTime = System.currentTimeMillis();

        if (!path.isEmpty()) {
            // int rows = grid.getRows();
            // int cols = grid.getCols();

            // // Create a 2D array to represent the grid
            // String[][] gridDisplay = new String[rows][cols];
            // for (int i = 0; i < rows; i++) {
            //     for (int j = 0; j < cols; j++) {
            //         Cell cell = grid.getCell(i, j);
            //         gridDisplay[i][j] = cell.getIsObstacle() ? "[0]" : "[ ]";
            //     }
            // }

            // // Mark the cells in the path
            // for (Cell cell : path) {
            //     if (!cell.equals(grid.getStart()) && !cell.equals(grid.getEnd())) {
            //         gridDisplay[cell.getX()][cell.getY()] = "[*]";
            //     } else if (cell.equals(grid.getStart())) {
            //         gridDisplay[cell.getX()][cell.getY()] = "[S]";
            //     } else {
            //         gridDisplay[cell.getX()][cell.getY()] = "[F]";
            //     }
            // }

            // // Print the grid
            // for (int i = 0; i < rows; i++) {
            //     for (int j = 0; j < cols; j++) {
            //         System.out.print(gridDisplay[i][j]);
            //     }
            //     System.out.println();
            // }
            // System.out.println("Path: " + ModifiedAStar.printPath(path));
            System.out.println("length: " + path.size());
            System.out.println("\nTime taken: " + (endTime - startTime) + "ms\n");
        } else {
            System.out.println("No path found");
        }

    }
}
