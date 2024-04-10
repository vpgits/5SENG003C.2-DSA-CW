import java.util.List;

public class Main {
    public static void main(String[] args) {

        int rows = 10;
        int cols = 10;
        Cell[][] grid = new Cell[rows][cols];

        Cell start = null;
        Cell end = null;

// Initialize the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }

// Set walls and rocks
        String[] puzzle = {
                ".....0...S",
                "....0.....",
                "0.....0..0",
                "...0....0.",
                ".F......0.",
                ".0........",
                ".......0..",
                ".0.0..0..0",
                "0.........",
                ".00.....0."
        };

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char symbol = puzzle[i].charAt(j);
                if (symbol == '0') {
                    grid[i][j].setIsObstacle(true);
                } else if (symbol == 'S') {
                    start = grid[i][j];
                } else if (symbol == 'F') {
                    end = grid[i][j];
                }
            }
        }

        List<Cell> path = ModifiedAStar.findPath(grid, start, end);
        if (path != null) {
            // Create a 2D array to represent the grid
            String[][] gridDisplay = new String[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    gridDisplay[i][j] = grid[i][j].getIsObstacle() ? "[0]" : "[ ]";
                }
            }

            // Mark the cells in the path
            for (Cell cell : path) {
                if (!cell.equals(start) && !cell.equals(end)) {
                    gridDisplay[cell.getX()][cell.getY()] = "[*]";
                } else if (cell.equals(start)) {
                    gridDisplay[cell.getX()][cell.getY()] = "[S]";
                } else {
                    gridDisplay[cell.getX()][cell.getY()] = "[F]";
                }
            }

            // Print the grid
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.print(gridDisplay[i][j]);
                }
                System.out.println();
            }
        } else {
            System.out.println("No path found");
        }

    }
}
