public class Cell {
    private int x; // x-position of the cell
    private int y; // y-position of the cell
    private int cost; //cost from the starting cell to the current cell
    private int heuristic; // heuristic cost from the cell to the goal
    private boolean isObstacle; // check if the cell is an obstacle
    private Cell parent; // parent of the current cell

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.isObstacle = false;
        this.cost = Integer.MAX_VALUE;
        this.heuristic = 0;
        this.parent = null;


    }
    public Cell(int x, int y, boolean isObstacle){
        this.x = x;
        this.y = y;
        this.isObstacle = isObstacle;
        this.heuristic = 0;
        this.cost = Integer.MAX_VALUE;
        this.parent = null;

    }

    /**
     * Get the total cost of the current cell
     * @return the total cost of the current cell
     */
    public int getTotalCost() {
        return cost + heuristic;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public Cell getParent() {
        return parent;
    }

    public void setParent(Cell parent) {
        this.parent = parent;
    }

    public boolean getIsObstacle() {
        return isObstacle;
    }
    public void setIsObstacle(boolean isObstacle) {
        this.isObstacle = isObstacle;
    }
}
