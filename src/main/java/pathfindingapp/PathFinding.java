package pathfindingapp;


import java.awt.Color;
import java.util.*;

public class PathFinding {
    private ArrayList<Node> nodes;
    public ArrayList<Node> path;
    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;
    private Node startNode;
    private Node goalNode;

    public PathFinding(ArrayList<Node> nodes, int start, int end, int gridWidth) {
        this.nodes = nodes;
        this.openList = new ArrayList<>();
        this.closedList = new ArrayList<>();
        this.path = new ArrayList<>();

        this.startNode = nodes.get(start);
        this.goalNode = nodes.get(end);

        // Set start node initial gDegree and fDegree
        startNode.gDegree = 0;
        startNode.hDegree = calculateDistance(startNode, goalNode);
        startNode.calculateFDegree();
        
        openList.add(startNode); // Add start node to open list
        aStarAlgorithm();
    }

    private void aStarAlgorithm() {
        while (!openList.isEmpty()) {
            // Sort openList by fDegree (lowest first)
            openList.sort(Comparator.comparingDouble(n -> n.fDegree));

            // Get the node with the lowest fDegree
            Node current = openList.get(0);
            
            // If we've reached the goal, reconstruct the path
            if (current.equals(goalNode)) {
                reconstructPath(current);
                return;
            }

            openList.remove(current);
            closedList.add(current);

            // Explore neighbors
            for (Node neighbor : getNeighbors(current)) {
                if (closedList.contains(neighbor)) continue; // Skip if already evaluated

                if(neighbor.getColor() == Color.BLACK) {
                    continue; // Skip if neighbor is an obstacle
                }
                
                if (!openList.contains(neighbor)) {
                    openList.add(neighbor); // Add new neighbor to open list
                }

                // This path is the best until now. Record it
                neighbor.parent = current;
                neighbor.gDegree = calculateDistance(neighbor, current);
                neighbor.hDegree = calculateDistance(neighbor, goalNode);
                neighbor.calculateFDegree();
            }
        }
    }

    //calculate the distance between two nodes
    private double calculateDistance(Node a, Node b) {
        return Math.sqrt((double) (Math.pow(Math.abs(a.getX() - b.getX()), 2) + Math.pow(Math.abs(a.getY() - b.getY()), 2)));
    }

    // Backtrack from the goal node to reconstruct the path
    private void reconstructPath(Node current) {
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path); // Reverse the path to start from the beginning
    }

    // Get neighboring nodes (up, down, left, right)
    private ArrayList<Node> getNeighbors(Node node) {
        ArrayList<Node> neighbors = new ArrayList<>();
        int index = nodes.indexOf(node);

        // Calculate positions of neighbors on a 2D grid
        if(index % 49 == 0) {
            //right edge
            if(index == 49) {
                neighbors.add(nodes.get(48));
                neighbors.add(nodes.get(99));
                neighbors.add(nodes.get(98));
            } else if(index == 2499) {
                neighbors.add(nodes.get(2449));
                neighbors.add(nodes.get(2448));
                neighbors.add(nodes.get(2498));
            } else {
                neighbors.add(nodes.get(index - 1));
                neighbors.add(nodes.get(index - 50));
                neighbors.add(nodes.get(index - 51));
                neighbors.add(nodes.get(index + 50));
                neighbors.add(nodes.get(index + 51));
            }
        } else if(index % 50 == 0) {
            //left edge
            if(index == 0) {
                neighbors.add(nodes.get(1));
                neighbors.add(nodes.get(50));
                neighbors.add(nodes.get(51));
            } else if(index == 2450) {
                neighbors.add(nodes.get(2451));
                neighbors.add(nodes.get(2400));
                neighbors.add(nodes.get(2401));
            } else {
                neighbors.add(nodes.get(index + 1));
                neighbors.add(nodes.get(index - 50));
                neighbors.add(nodes.get(index - 49));
                neighbors.add(nodes.get(index + 50));
                neighbors.add(nodes.get(index + 51));
            }
        } else {
            if(index < 50) {
                //top edge
                neighbors.add(nodes.get(index - 1));
                neighbors.add(nodes.get(index + 1));
                neighbors.add(nodes.get(index + 50));
                neighbors.add(nodes.get(index + 49));
                neighbors.add(nodes.get(index + 51));
            } else if(index > 2450) {
                //bottom edge
                neighbors.add(nodes.get(index - 1));
                neighbors.add(nodes.get(index + 1));
                neighbors.add(nodes.get(index - 50));
                neighbors.add(nodes.get(index - 49));
                neighbors.add(nodes.get(index - 51));
            } else {
                neighbors.add(nodes.get(index - 1));
                neighbors.add(nodes.get(index + 1));
                neighbors.add(nodes.get(index - 50));
                neighbors.add(nodes.get(index + 50));
                neighbors.add(nodes.get(index - 49));
                neighbors.add(nodes.get(index + 49));
                neighbors.add(nodes.get(index - 51));
                neighbors.add(nodes.get(index + 51));
            }
        }
        return neighbors;
    }

    public ArrayList<Node> getPath() {
        return path;
    }
}
