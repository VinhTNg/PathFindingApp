package pathfindingapp;

import java.util.ArrayList;

public class TestMain {
    Panel panel;
    ArrayList<Integer> coor;

    /**
     * Constructor for the TestMain class.
     */
    public TestMain() {
        panel = new Panel();
    }

    public int findNode(int startX, int startY) {
        if(startY < 100 || startX > 800) {
            throw new IllegalArgumentException("Invalid start location");
        }
        else {
            int collumn = (int) Math.floor(startX / panel.nodeWidth);
            int row = (int) Math.floor((startY - 100) / panel.nodeHeight);
            int target = collumn + row * 50;
    
            return target;
        }
    }
}
