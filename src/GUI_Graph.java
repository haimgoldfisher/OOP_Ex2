import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class GUI_Graph extends JFrame {
    DirectedWeightedGraph graph;


    public GUI_Graph(MyDirectedWeightedGraphAlgorithms graphAlgorithms){
        this.graph = graphAlgorithms.getGraph();
        setTitle("Graph");
        setSize(1000, 1000);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        Iterator<EdgeData> edgeIT = this.graph.edgeIter();
        int x1,x2,y1,y2;
        while (edgeIT.hasNext()){
            Edge e = (Edge) edgeIT.next();
            x1 = (int) this.graph.getNode(e.getSrc()).getLocation().x();
            y1 = (int) this.graph.getNode(e.getSrc()).getLocation().y();
            x2 = (int) this.graph.getNode(e.getDest()).getLocation().x();
            y2 = (int) this.graph.getNode(e.getDest()).getLocation().y();
            g.drawLine(x1*10, y1*10, x2*10, y2*10);
        }
        Iterator<NodeData> nodeIT = this.graph.nodeIter();
        while (nodeIT.hasNext()){
            Node n = (Node) nodeIT.next();
            g.setColor(Color.RED);
            g.fillOval((int)n.getLocation().x()*10, (int)n.getLocation().y()*10, 50, 50);
            g.setColor(Color.BLACK);
            g.drawOval((int)n.getLocation().x()*10, (int)n.getLocation().y()*10, 50, 50);
        }
    }



    public static void main(String[] args) {
        MyDirectedWeightedGraphAlgorithms graphAlgorithms = new MyDirectedWeightedGraphAlgorithms();
        graphAlgorithms.load("data/G1.json");
        new GUI_Graph(graphAlgorithms);
    }
}
