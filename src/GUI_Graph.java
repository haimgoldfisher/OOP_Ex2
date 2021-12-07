import javax.swing.*;
import java.awt.*;

public class GUI_Graph extends JFrame {

    public GUI_Graph(){
        setTitle("Drawing a Circle");
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.fillOval(150, 150, 100, 100);
    }

    public void paintNode(Graphics g, Node n)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.fillOval((int)n.getLocation().x(), (int)n.getLocation().y(), 100, 100);
    }

    public static void main(String[] args) {

        new GUI_Graph();

    }
}
