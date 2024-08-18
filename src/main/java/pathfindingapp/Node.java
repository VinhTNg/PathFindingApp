package pathfindingapp;

import java.awt.Graphics;
import java.awt.Color;

/**
 * Node class to store the color, fDegree, gDegree, and hDegree of a node to use in Grid.
 * @Author: Vinh Nguyen
 */
public class Node {
    
    public int gDegree; // Cost from start node to current node
    public int hDegree; // Heuristic cost from current node to goal node
    public int fDegree; // Total cost (g + h)
    public Node parent; // Parent node
    private Color color;
    private int x;
    private int y;
    private int width = 13;
    private int height = 13;
    
    
    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public int calculateFDegree() {
        this.fDegree = this.gDegree + this.hDegree;
        return this.fDegree;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return this.x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(x + 1, y + 1, width, height);
    }
}