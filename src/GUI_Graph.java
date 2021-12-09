import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class GUI_Graph extends JPanel {

    MyDirectedWeightedGraph graph_data;
    private int kRADIUS = 10;
    private int screen_W;
    private int screen_H;
    double min_x = Double.MAX_VALUE;
    double max_x = Double.MIN_VALUE;
    double min_y = Double.MAX_VALUE;
    double max_y = Double.MIN_VALUE;

    public GUI_Graph(MyDirectedWeightedGraph graph,int h,int w){
        screen_H=h;
        screen_W=w;
        graph_data=graph;
        Init();
    }

    private void Init() {
        screen_W = screen_W-200;
        screen_H = screen_H-200;
        for (NodeData nd: graph_data.getKey_node().values()) {
            MyGeoLocation loc = (MyGeoLocation) nd.getLocation();
            if(loc.x()<min_x){
                min_x = loc.x();
            }
            if(loc.x()>max_x){
                max_x = loc.x();
            }
            if(loc.y()<min_y){
                min_y = loc.y();
            }
            if(loc.y()>max_y){
                max_y = loc.y();
            }
        }


    }

    public void paintComponent(Graphics g)
    {
        for (EdgeData edge: graph_data.getKeys_edge().values()) {
            int src = edge.getSrc();
            int dest = edge.getDest();
            NodeData src_nd = graph_data.getKey_node().get(src);
            NodeData dest_nd = graph_data.getKey_node().get(dest);
//            MyGeoLocation src_loc = (MyGeoLocation) src_nd.getLocation();
            MyGeoLocation src_loc = (MyGeoLocation) src_nd.getLocation();
            MyGeoLocation dest_loc = (MyGeoLocation) dest_nd.getLocation();
            g.setColor(Color.BLACK);
            int x1 =(int) ((dest_loc.x()-min_x)/(max_x-min_x)*screen_W)+kRADIUS;
            int y1 = (int) ((dest_loc.y()-min_y)/(max_y-min_y)*screen_H)+kRADIUS;
            int x2 =(int) ((src_loc.x()-min_x)/(max_x-min_x)*screen_W)+kRADIUS;
            int y2 = (int) ((src_loc.y()-min_y)/(max_y-min_y)*screen_H)+kRADIUS;
            drawArrowLine(g,x1, y1,x2,y2,20,7, edge.getWeight());
            g.drawString(String.format("%.2f", edge.getWeight()),(int) ((x1+x2) / 2), (int) ((y1+y2) / 2));
//            double dist = src_loc.distance(dest_loc);
//            g.drawString(String.format("%.2f", dist),
//                    (int) ((dest_loc.x() + src_loc.x()) / 2),
//                    (int) ((dest_loc.y() + src_loc.y()) / 2));
        }
        for (NodeData nd: graph_data.getKey_node().values()) {
            MyGeoLocation loc = (MyGeoLocation) nd.getLocation();
            g.setColor(Color.BLUE);
            int x =(int) ((loc.x()-min_x)/(max_x-min_x)*screen_W);
            int y = (int) (int) ((loc.y()-min_y)/(max_y-min_y)*screen_H);
            g.fillOval(x , y,2 * kRADIUS, 2 * kRADIUS);
            g.setColor(Color.BLACK);
            g.drawOval(x , y,2 * kRADIUS, 2 * kRADIUS);
            g.drawString(""+nd.getKey(),x+3,y+13);
        }
    }

    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h,double w) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }
}

