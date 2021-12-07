import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class GUI_Graph extends JFrame {

    public GUI_Graph(MyDirectedWeightedGraphAlgorithms graphAlgorithms){
        setTitle("Graph");
        setSize(1000, 1000);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        paintEdgesNodes(super.getGraphics(), graphAlgorithms);
    }

//    @Override
//    public void paint(Graphics g)
//    {
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.RED);
//        g2d.fillOval(150, 150, 100, 100);
//    }

    public void paintEdgesNodes(Graphics g, MyDirectedWeightedGraphAlgorithms graphAlgorithms)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        Iterator<EdgeData> edgeIT = graphAlgorithms.getGraph().edgeIter();
        int x1,x2,y1,y2;
        while (edgeIT.hasNext()){
            Edge e = (Edge) edgeIT.next();
            x1 = (int) graphAlgorithms.getGraph().getNode(e.getSrc()).getLocation().x();
            y1 = (int) graphAlgorithms.getGraph().getNode(e.getSrc()).getLocation().y();
            x2 = (int) graphAlgorithms.getGraph().getNode(e.getDest()).getLocation().x();
            y2 = (int) graphAlgorithms.getGraph().getNode(e.getDest()).getLocation().y();
            g2d.drawLine(x1, y1, x2, y2);
        }
        Iterator<NodeData> nodeIT = graphAlgorithms.getGraph().nodeIter();
        while (nodeIT.hasNext()){
            Node n = (Node) nodeIT.next();
            g2d.setColor(Color.RED);
            g2d.fillOval((int)n.getLocation().x(), (int)n.getLocation().y(), 10, 10);
            g2d.setColor(Color.BLACK);
            g2d.drawOval((int)n.getLocation().x(), (int)n.getLocation().y(), 10, 10);
        }
    }

    public static void main(String[] args) {
        MyDirectedWeightedGraphAlgorithms graphAlgorithms = new MyDirectedWeightedGraphAlgorithms();
        graphAlgorithms.load("data/G1.json");
        new GUI_Graph(graphAlgorithms);
    }
}
