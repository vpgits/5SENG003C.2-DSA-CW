public class Grid {
    private Cell[][] grid;
    private Cell start;
    private Cell end;

    public Grid(int rows, int cols) {
        grid = new Cell[rows][cols];
        this.start = null;
        this.end = null;
    }

    public Cell getStart() {
        return start;
    }

    public void setStart(Cell start) {
        this.start = start;
    }

    public Cell getEnd() {
        return end;
    }

    public void setEnd(Cell end) {
        this.end = end;
    }

    public Cell getCell(int x, int y) {
        return grid[x][y];
    }

    public void setCell(int x, int y, Cell cell) {
        grid[x][y] = cell;
    }

    public int getRows() {
        return grid.length;
    }

    public int getCols() {
        return grid[0].length;
    }
    public Cell[][] getGrid() {
        return grid;
    }
}
