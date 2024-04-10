import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModifiedAStar {
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // directions to move in the grid, no diagonal movements

    private static List<Cell> getNeighbours(Cell cell, Cell[][] grid){
        List<Cell> neighbours = new ArrayList<>();
        for (int[] direction: directions){
            int x = cell.getX() + direction[0];
            int y = cell.getY() + direction[1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && !grid[x][y].getIsObstacle()){
                neighbours.add(grid[x][y]);
            }
        }
        return neighbours;
    }

    private static int heuristic( Cell a, Cell b){
        return Math.abs (a.getX() - b.getX()) + Math.abs(a.getY() - b.getY()) - 1; // Hypothetical scenario where the player slides at a given direction therefore the heuristic is reduced by 1
    }

    private static List<Cell> reconstructPath( Cell current){
        List<Cell> path = new ArrayList<>();
        while (current != null){
            path.add(current);
            current = current.getParent();
        }
        Collections.reverse(path);
        return path;
    }

    public static List<Cell> findPath( Cell[][] grid, Cell start, Cell end){
        List<Cell> path = new ArrayList<>();
        List<Cell> openSet = new ArrayList<>();
        List<Cell> closedSet = new ArrayList<>();
        openSet.add(start);
        start.setCost(0);
        start.setHeuristic(heuristic(start, end));

        while (!openSet.isEmpty()){
            Cell current = openSet.getFirst();
            for (Cell cell: openSet){
                if (cell.getTotalCost() < current.getTotalCost()){
                    current = cell;
                }
            }
            openSet.remove(current);

            if (current.equals(end)){
                return reconstructPath(current);
            }

            closedSet.add(current);

            for (Cell neighbour: getNeighbours(current, grid)){
                if (closedSet.contains(neighbour)){
                    continue;
                }

                int newCost = current.getCost() + 1;
                if (newCost < neighbour.getCost() || !openSet.contains(neighbour)){
                    neighbour.setCost(newCost);
                    neighbour.setHeuristic(heuristic(neighbour, end));
                    neighbour.setParent(current);
                    if (!openSet.contains(neighbour)){
                        openSet.add(neighbour);
                    }
                }
            }
        }
        return path;
    }

}
