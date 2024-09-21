import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import pathfindingapp.*;

public class PathFindingTest {
    /**
     * Test the algorithm using to find nodes to be colored.
     */
    @Test
    public void firstNode() {
        TestMain test = new TestMain();
        assertEquals(0, test.findNode(0, 100));
    }

    /**
     * Test the algorithm using to find nodes to be colored.
     */
    @Test
    public void secondNode() {
        TestMain test = new TestMain();
        assertEquals(1, test.findNode(15, 100));
    }

    @Test
    public void randomNode() {
        TestMain test = new TestMain();
        assertEquals(823, test.findNode(14 * 23, 100 + 14 * 16));
    }

    @Test
    public void findNeighborsTopLeft() {
        TestMain test = new TestMain();
        ArrayList<Node> neighbors = test.pathFinding.getNeighbors(test.panel.nodes.get(0));
        assertEquals(3, neighbors.size());
    }

    @Test
    public void findNeighborsTopRight() {
        TestMain test = new TestMain();
        ArrayList<Node> neighbors = test.pathFinding.getNeighbors(test.panel.nodes.get(49));
        assertEquals(3, neighbors.size());
    }

    @Test
    public void findNeighborsBottomLeft() {
        TestMain test = new TestMain();
        ArrayList<Node> neighbors = test.pathFinding.getNeighbors(test.panel.nodes.get(2450));
        assertEquals(3, neighbors.size());
    }
    
    @Test
    public void findNeighborsBottomRight() {
        TestMain test = new TestMain();
        ArrayList<Node> neighbors = test.pathFinding.getNeighbors(test.panel.nodes.get(2499));
        assertEquals(3, neighbors.size());
    }

    @Test
    public void findNeighborsLeftEdge() {
        TestMain test = new TestMain();
        ArrayList<Node> neighbors = test.pathFinding.getNeighbors(test.panel.nodes.get(50));
        assertEquals(5, neighbors.size());
    }

    @Test
    public void findNeighborsRightEdge() {
        TestMain test = new TestMain();
        ArrayList<Node> neighbors = test.pathFinding.getNeighbors(test.panel.nodes.get(99));
        assertEquals(5, neighbors.size());
    }

    @Test
    public void findNeighborsTopEdge() {
        TestMain test = new TestMain();
        ArrayList<Node> neighbors = test.pathFinding.getNeighbors(test.panel.nodes.get(25));
        assertEquals(5, neighbors.size());
    }

    @Test
    public void findNeighborsBottomEdge() {
        TestMain test = new TestMain();
        ArrayList<Node> neighbors = test.pathFinding.getNeighbors(test.panel.nodes.get(2475));
        assertEquals(5, neighbors.size());
    }

    @Test
    public void findNeighborsMiddle() {
        TestMain test = new TestMain();
        ArrayList<Node> neighbors = test.pathFinding.getNeighbors(test.panel.nodes.get(1230));
        assertEquals(8, neighbors.size());
    }
}
