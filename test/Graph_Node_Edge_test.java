import org.junit.Test;
import static org.junit.Assert.*;

public class Graph_Node_Edge_test {

    @Test
    public void myLoc_Test()
    {
        int x1 = 1;
        int x2 = 2;
        int y1 = 1;
        int y2 = 2;
        int z1 = 1;
        int z2 = 2;
        MyGeoLocation loc1 = new MyGeoLocation(x1,y1,z1);
        MyGeoLocation loc2 = new MyGeoLocation(x2,y2,z2);
        MyGeoLocation loc3 = new MyGeoLocation(x1,y1,z1); // saame params as loc1
        assertNotSame(loc1, loc2);
        assertNotSame(loc2, loc3);
        assertNotSame(loc1, loc3); // address is not the same
        assertEquals(0, loc1.distance(loc3), 0.0); // values are the same
    }

    @Test
    public void node_Test()
    {
        MyGeoLocation loc1 = new MyGeoLocation(0,1,0);
        MyGeoLocation loc2 = new MyGeoLocation(1,0,0);
        MyGeoLocation loc3 = new MyGeoLocation(1,1,0);
        MyGeoLocation loc4 = new MyGeoLocation(0,0,0);
        Node n1 = new Node(0, loc1);
        Node n2 = new Node(1, loc2);
        Node n3 = new Node(2, loc3);
        Node n4 = new Node(3, loc4);
        assertEquals(n1.getKey(), 0);
        assertEquals(n2.getKey(), 1);
        assertEquals(n1.getLocation().distance(n3.getLocation()), n4.getLocation().distance(n2.getLocation()), 0.0);
        n3.setLocation(loc1);
        assertEquals(0, n1.getLocation().distance(n3.getLocation()), 0.0);
    }

    @Test
    public void edge_Test()
    {
        Edge e1 = new Edge(0,1,1);
        Edge e2 = new Edge(1,2,0.5);
        Edge e3 = new Edge(2,3,1);
        Edge e4 = new Edge(3,0,1);
        assertEquals(1, e1.getDest());
        assertEquals(3, e4.getSrc());
        e3.setSrc_key(0);
        e3.setDest_key(3);
        assertEquals(0, e3.getSrc());
        assertEquals(3, e3.getDest());
        assertEquals(1, e2.getWeight(), 0.0);
        assertEquals(e1.getWeight(), e4.getWeight(), 0.0);
        assertNotEquals(e2.getWeight(), e3.getWeight());
    }

    @Test
    public void myDirectedWeightedGraph_Test()
    {

    }
}
