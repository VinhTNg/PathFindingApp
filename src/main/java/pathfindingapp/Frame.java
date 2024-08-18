package pathfindingapp;

import javax.swing.JFrame;

/**
 * Class to store main frame for the app.
 */
public class Frame extends JFrame {
    int width;
    int height;
    Panel pathFinding;

    /**
     * Constructor for the Frame class.
     * @param width width of the frame
     * @param height height of the frame
     */
    public Frame(int width, int height) {
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
}
