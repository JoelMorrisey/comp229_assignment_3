import java.util.*;

class PathFinder {

    private class Node {
        int costSoFar;
        Cell current;
        List<Cell> pathSoFar;
        Node(Cell c, int cost, List<Cell> psf){
            costSoFar = cost;
            current = c;
            pathSoFar = psf;
        }
    }
    public List<Cell> findPath(Cell from, Cell to, Grid land, Set<Cell> occupied){
        System.out.println("pathfinding..."+from.toString()+ "->"+to.toString());
        land.doToEachCell((c) -> c.ticked = false);

        Queue<Node> frontier = new PriorityQueue<Node>(1000, (a, b) -> Integer.compare(a.costSoFar, b.costSoFar));
        
        frontier.add(new Node(from, 0, new ArrayList<Cell>()));
        Node curr = frontier.remove();

        Set<Cell> marked = new HashSet<Cell>();
        while (curr.current != to){
            List<Cell> neighbours = land.getRadius(curr.current, 1, false);
            neighbours.removeAll(occupied);
            for (Cell c: neighbours){
                if (!marked.contains(c)) {
                    marked.add(c);
                    List<Cell> newPath = new ArrayList<Cell>(curr.pathSoFar);
                    newPath.add(c);
                    frontier.add(new Node(c, curr.costSoFar + c.movementCost(), newPath));
                }
            }
            if (frontier.size() > 0){
                curr = frontier.remove();
            } else {
                break;
            }
        }
        System.out.println("... path found");
        return curr.pathSoFar;
    }

    public int pathCost(List<Cell> path){
        int ret = 0;
        for(Cell c: path){
            ret = ret + c.movementCost();
        }
        return ret;
    }


}