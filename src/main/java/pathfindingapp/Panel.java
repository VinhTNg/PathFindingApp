package pathfindingapp;

import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

/**
 * This class holds the panel for the GUI.
 */
public class Panel extends JPanel implements MouseListener, MouseMotionListener, ActionListener {
    //variables to control the size of the nodes
    public final int nodeWidth = 14;
    public final int nodeHeight = 14;
    private final int width = 700;
    private final int height = 800;
    private int startNode = -1;
    private int endNode = -1;
    
    //variables to control states of the panel
    private boolean start = false;
    private boolean maxLocation = false;
    private boolean obstacle = false;
    private boolean isDragging = false;
    private int location = 0;

    JButton startButton = new JButton();
    JButton resetButton = new JButton();


    ArrayList<Node> nodes = new ArrayList<Node>();
    
    /**
     * Constructor for the Panel class.
     * @param width width of the panel
     * @param height height of the panel
     */
    public Panel() {
        this.setPreferredSize(new Dimension(width, height));
        this.setVisible(true);
        

        startButton.setText("Start");
        // startButton.setBounds(50, 25, 200, 50);
        // startButton.setLocation(50, 25);
        startButton.addActionListener(this);
        this.add(startButton);

        resetButton.setText("Reset");
        // resetButton.setBounds(450, 25, 200, 50);
        // resetButton.setLocation(450, 25);
        resetButton.addActionListener(this);
        this.add(resetButton);

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        // Add 2500 nodes to the panel from high to low
        for (int i = 0; i < 50; i++) {
            for(int j = 0; j < 50; j++) {
                Node node = new Node();
                node.setColor(Color.WHITE);
                node.setY(i * 14 + 100);
                node.setX(j * 14);
                nodes.add(node);
            }
        }
        // this.repaint();
    }

    /**
     * Paint the panel.
     * @param g Graphics object
     * After nodes are added, fill node's color.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i <= 50; i++) {
            g.drawLine(i * nodeWidth, 100, i * nodeWidth, 800);
        }
        for (int i = 0; i <= 50; i++) {
            g.drawLine(0, i * nodeHeight + 100, 700, i * nodeHeight + 100);
        }
        for (Node node : nodes) {
            node.draw(g);
        }
    }

    /**
     * Method to handle setting the start point and destination point as black nodes.
     * @param e MouseEvent object
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if(!start){
            if (!maxLocation && e.getY() >= 100 && e.getY() <= 800) {
                int x = e.getX();
                int y = e.getY();
                int collumn = (int) Math.floor(x / nodeWidth);
                int row = (int) Math.floor((y - 100) / nodeHeight);
                int target = collumn + row * 50;
                
                if(startNode == -1) {
                    startNode = target;
                } else {
                    endNode = target;
                }
                nodes.get(target).setColor(Color.GRAY);
                location++;
                if(location == 2) {
                    maxLocation = true;
                    obstacle = true;
                }
                repaint();
            } else if (obstacle && e.getY() >= 100 && e.getY() <= 800) {
                int x = e.getX();
                int y = e.getY();
                int collumn = (int) Math.floor(x / nodeWidth);
                int row = (int) Math.floor((y - 100) / nodeHeight);
                int target = collumn + row * 50;
                if(nodes.get(target).getColor() == Color.WHITE) {
                    nodes.get(target).setColor(Color.BLACK);
                }
                repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        isDragging = true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!start) {
            if(isDragging && obstacle && e.getY() >= 100 && e.getY() <= 800) {
                double x = e.getX();
                double y = e.getY();
                int collumn = (int) Math.floor(x / nodeWidth);
                int row = (int) Math.floor((y - 100) / nodeHeight);
                int target = collumn + row * 50;

                //TODO: Fix the bug where returning to the panel from the bottom causes the top nodes to be colored.
                if(nodes.get(target).getColor() == Color.WHITE) {
                    if(e.getY() - nodes.get(target).getY() < 14) {
                        nodes.get(target).setColor(Color.BLACK);
                    }
                    nodes.get(target).setColor(Color.BLACK);
                }
                repaint();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isDragging = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton) {
            start = true;
            pathfindingapp.PathFinding pathFinding = new pathfindingapp.PathFinding(nodes, startNode, endNode, 700);
            for(Node node : pathFinding.path) {
                node.setColor(Color.BLUE);
                repaint();
            }
        }
        if(e.getSource() == resetButton) {
            start = false;
            maxLocation = false;
            obstacle = false;
            startNode = -1;
            endNode = -1;
            location = 0;
            for(Node node : nodes) {
                node.setColor(Color.WHITE);
            }
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
