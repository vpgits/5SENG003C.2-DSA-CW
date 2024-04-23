public class Cell {
    private int x; // x-position of the cell
    private int y; // y-position of the cell
    private double cost; // cost from the starting cell to the current cell
    private double heuristic; // heuristic cost from the cell to the goal
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

    public Cell(int x, int y, boolean isObstacle) {
        this.x = x;
        this.y = y;
        this.isObstacle = isObstacle;
        this.heuristic = 0;
        this.cost = Integer.MAX_VALUE;
        this.parent = null;

    }

    /**
     * Get the total cost of the current cell
     * 
     * @return the total cost of the current cell
     */
    public double getTotalCost() {
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(double heuristic) {
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
