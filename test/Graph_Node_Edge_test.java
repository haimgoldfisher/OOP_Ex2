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
       // Node n1 = new Node();
    }

    @Test
    public void edge_Test()
    {

    }

    @Test
    public void myDirectedWeightedGraph_Test()
    {

    }
}
