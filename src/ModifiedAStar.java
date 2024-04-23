import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class ModifiedAStar {
    private static final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    private static List<Cell> getNeighbours(Cell cell, Cell[][] grid, Cell end) {
        List<Cell> neighbours = new ArrayList<>();
        for (int[] direction : directions) {
            Cell temp = slip(cell, grid, direction, end);
            if (temp != null) {
                neighbours.add(temp);
            }
        }
        return neighbours;
    }

    private static Cell slip(Cell cell, Cell[][] grid, int[] direction, Cell end) {
        int x = cell.getX() + direction[0];
        int y = cell.getY() + direction[1];

        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y].getIsObstacle()) {
            return null;
        }

        while (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && !grid[x][y].getIsObstacle()) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];

            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length ||
                    grid[nextX][nextY].getIsObstacle()) {
                return grid[x][y];
            }
            if (nextX == end.getX() && nextY == end.getY())
                return grid[nextX][nextY];

            x = nextX;
            y = nextY;
        }

        return null;
    }

    // private static int heuristic(Cell a, Cell b) {
    // return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    // }

    private static double cost(Cell a, Cell b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY()) + b.getHeuristic();
    }

    private static double heuristic(Cell a, Cell b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    private static List<Cell> reconstructPath(Cell current) {
        List<Cell> path = new ArrayList<>();
        while (current != null) {
            path.add(current);
            current = current.getParent();
        }
        Collections.reverse(path);
        return path;
    }

    public static String printPath(List<Cell> path) {
        StringBuilder sb = new StringBuilder();
        Cell prev = null;
        for (int i = 0; i < path.size(); i++) {
            Cell cell = path.get(i);
            sb.append("Step ").append(i + 1).append(": ");
            if (cell.getParent() == null) {
                sb.append("Start at [").append(cell.getY() + 1).append(",").append(cell.getX() + 1).append("] \n");
                prev = cell;
                continue;
            }
            if (prev != null) {
                if (cell.getX() - prev.getX() > 0) {
                    sb.append("Move Down to ");
                } else if (cell.getX() - prev.getX() < 0) {
                    sb.append("Move Up to ");
                } else if (cell.getY() - prev.getY() > 0) {
                    sb.append("Move Right to ");
                } else if (cell.getY() - prev.getY() < 0) {
                    sb.append("Move Left to ");

                }
            }
            sb.append("[").append(cell.getY() + 1).append(",").append(cell.getX() + 1).append("] \n");
            if (i == path.size() - 1) {
                sb.append("Step ").append(i + 2).append(": ").append("Done!\n");
            }
            prev = cell;
        }

        return sb.toString();
    }

    public static List<Cell> findPath(Cell[][] grid, Cell start, Cell end) {

        Queue<Cell> openSet = new PriorityQueue<>(
                Comparator.comparingDouble(cell -> cell.getCost() + cell.getHeuristic()));
        boolean[][] closedSet = new boolean[grid.length][grid[0].length];
        openSet.add(start);

        while (!openSet.isEmpty()) {
            Cell current = openSet.poll();
            if (current == null) {
                break;
            }

            if (current.equals(end)) {
                return reconstructPath(current);
            }

            int x = current.getX();
            int y = current.getY();

            closedSet[x][y] = true;

            for (Cell neighbour : getNeighbours(current, grid, end)) {
                if (closedSet[neighbour.getX()][neighbour.getY()]) {
                    continue;
                }

                double newCost = current.getCost() + cost(current, neighbour);
                if (newCost < neighbour.getCost() || !openSet.contains(neighbour)) {
                    neighbour.setCost(newCost);
                    neighbour.setHeuristic(heuristic(neighbour, end));
                    neighbour.setParent(current);
                    openSet.add(neighbour);
                }
            }

        }

        return Collections.emptyList();
    }
}
