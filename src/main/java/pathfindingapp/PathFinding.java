package pathfindingapp;


import java.awt.Color;
import java.util.*;

public class PathFinding {
    public ArrayList<Node> path;
    private ArrayList<Node> nodes;
    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;
    private Node startNode;
    private Node goalNode;
    private int gridWidth;

    public PathFinding(ArrayList<Node> nodes, int start, int end, int gridWidth) {
        this.nodes = nodes;
        this.openList = new ArrayList<>();
        this.closedList = new ArrayList<>();
        this.path = new ArrayList<>();
        this.gridWidth = gridWidth;

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

    // Get neighboring nodes (up, down, left, right) with diagonal neighbors included
public ArrayList<Node> getNeighbors(Node node) {
    ArrayList<Node> neighbors = new ArrayList<>();
    int index = nodes.indexOf(node);
    int gridSize = gridWidth * gridWidth;

    // Top-left corner
    if (index == 0) {
        neighbors.add(nodes.get(index + 1));                  // Right
        neighbors.add(nodes.get(index + gridWidth));          // Down
        neighbors.add(nodes.get(index + gridWidth + 1));      // Down-Right
    }
    // Top-right corner
    else if (index == gridWidth - 1) {
        neighbors.add(nodes.get(index - 1));                  // Left
        neighbors.add(nodes.get(index + gridWidth));          // Down
        neighbors.add(nodes.get(index + gridWidth - 1));      // Down-Left
    }
    // Bottom-left corner
    else if (index == gridSize - gridWidth) {
        neighbors.add(nodes.get(index + 1));                  // Right
        neighbors.add(nodes.get(index - gridWidth));          // Up
        neighbors.add(nodes.get(index - gridWidth + 1));      // Up-Right
    }
    // Bottom-right corner
    else if (index == gridSize - 1) {
        neighbors.add(nodes.get(index - 1));                  // Left
        neighbors.add(nodes.get(index - gridWidth));          // Up
        neighbors.add(nodes.get(index - gridWidth - 1));      // Up-Left
    }
    // Left edge
    else if (index % gridWidth == 0) {
        neighbors.add(nodes.get(index + 1));                  // Right
        neighbors.add(nodes.get(index - gridWidth));          // Up
        neighbors.add(nodes.get(index + gridWidth));          // Down
        neighbors.add(nodes.get(index - gridWidth + 1));      // Up-Right
        neighbors.add(nodes.get(index + gridWidth + 1));      // Down-Right
    }
    // Right edge
    else if (index % gridWidth == gridWidth - 1) {
        neighbors.add(nodes.get(index - 1));                  // Left
        neighbors.add(nodes.get(index - gridWidth));          // Up
        neighbors.add(nodes.get(index + gridWidth));          // Down
        neighbors.add(nodes.get(index - gridWidth - 1));      // Up-Left
        neighbors.add(nodes.get(index + gridWidth - 1));      // Down-Left
    }
    // Top edge
    else if (index < gridWidth) {
        neighbors.add(nodes.get(index - 1));                  // Left
        neighbors.add(nodes.get(index + 1));                  // Right
        neighbors.add(nodes.get(index + gridWidth));          // Down
        neighbors.add(nodes.get(index + gridWidth - 1));      // Down-Left
        neighbors.add(nodes.get(index + gridWidth + 1));      // Down-Right
    }
    // Bottom edge
    else if (index >= gridSize - gridWidth) {
        neighbors.add(nodes.get(index - 1));                  // Left
        neighbors.add(nodes.get(index + 1));                  // Right
        neighbors.add(nodes.get(index - gridWidth));          // Up
        neighbors.add(nodes.get(index - gridWidth - 1));      // Up-Left
        neighbors.add(nodes.get(index - gridWidth + 1));      // Up-Right
    }
    // Inside grid
    else {
        neighbors.add(nodes.get(index - 1));                  // Left
        neighbors.add(nodes.get(index + 1));                  // Right
        neighbors.add(nodes.get(index - gridWidth));          // Up
        neighbors.add(nodes.get(index + gridWidth));          // Down
        neighbors.add(nodes.get(index - gridWidth - 1));      // Up-Left
        neighbors.add(nodes.get(index - gridWidth + 1));      // Up-Right
        neighbors.add(nodes.get(index + gridWidth - 1));      // Down-Left
        neighbors.add(nodes.get(index + gridWidth + 1));      // Down-Right
    }

    return neighbors;
}


    public ArrayList<Node> getPath() {
        return path;
    }
}
