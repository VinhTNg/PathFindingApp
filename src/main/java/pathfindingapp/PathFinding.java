package pathfindingapp;

import java.util.*;

public class PathFinding {
    private ArrayList<Node> nodes;
    private ArrayList<Node> path;
    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;

    public PathFinding(ArrayList<Node> nodes, int start, int end) {
        this.nodes = nodes;
        openList = new ArrayList<Node>();
        openList.add(nodes.get(start));
        aStarAlgorithm();
    }

    private ArrayList<Node> aStarAlgorithm() {
        for(int i = 0; i < nodes.size(); i++) {
            nodes.get(i).gDegree = Integer.MAX_VALUE;
            nodes.get(i).calculateFDegree();
        }
        return path;
    }
}