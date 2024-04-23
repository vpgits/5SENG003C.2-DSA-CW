import java.util.List;

public class Main {
    public static void main(String[] args) {

        Grid grid = Utils.parser("benchmark_series/puzzle_2560.txt");
        long startTime = System.currentTimeMillis();
        List<Cell> path = ModifiedAStar.findPath(grid.getGrid(), grid.getStart(), grid.getEnd());
        long endTime = System.currentTimeMillis();

        if (!path.isEmpty()) {
            // displayGrid(grid, path);
            // System.out.println("Path: " + ModifiedAStar.printPath(path));
            System.out.println("length: " + path.size());
            System.out.println("\nTime taken: " + (endTime - startTime) + "ms\n");
        } else {
            System.out.println("No path found");
        }

    }

    private static void displayGrid(Grid grid, List<Cell> path) {
        int rows = grid.getRows();
        int cols = grid.getCols();

        String[][] gridDisplay = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell cell = grid.getCell(i, j);
                gridDisplay[i][j] = cell.getIsObstacle() ? "[0]" : "[ ]";
            }
        }

        for (Cell cell : path) {
            if (!cell.equals(grid.getStart()) && !cell.equals(grid.getEnd())) {
                gridDisplay[cell.getX()][cell.getY()] = "[*]";
            } else if (cell.equals(grid.getStart())) {
                gridDisplay[cell.getX()][cell.getY()] = "[S]";
            } else {
                gridDisplay[cell.getX()][cell.getY()] = "[F]";
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(gridDisplay[i][j]);
            }
            System.out.println();
        }
    }
}
