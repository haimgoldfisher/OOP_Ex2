import api.EdgeData;
import api.GeoLocation;
import api.NodeData;
import com.google.gson.*;
import org.junit.Test;

import javax.swing.text.html.parser.Entity;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Map;

import static org.junit.Assert.*;

public class Algo_test {
    MyDirectedWeightedGraphAlgorithms G1 = new MyDirectedWeightedGraphAlgorithms();
    MyDirectedWeightedGraphAlgorithms G2 = new MyDirectedWeightedGraphAlgorithms();
    MyDirectedWeightedGraphAlgorithms G3 = new MyDirectedWeightedGraphAlgorithms();


    @Test
    public void load_Test()
    {
        MyDirectedWeightedGraphAlgorithms graphAlgorithms = new MyDirectedWeightedGraphAlgorithms();
        graphAlgorithms.load("data/G1.json");
        System.out.println(graphAlgorithms.getGraph().getNode(0));
        graphAlgorithms.save("output");
    }


    @Test
    public void node_Test()
    {
        MyGeoLocation g = new MyGeoLocation(1,2,3);
        Node n = new Node(1, g);
    }

    @Test
    public void graph_Test()
    {

    }

    @Test
    public void create_new_rand_Graph_Test()
    {
        MyDirectedWeightedGraphAlgorithms algo = new MyDirectedWeightedGraphAlgorithms();
        algo.initRandomGraph(4);
        //algo.isConnected();
        algo.center();
        //algo.save("output");
    }

    @Test
    public void isConnected_Test()
    {
        G1.load("data/G1.json");
        G2.load("data/G2.json");
        G3.load("data/G3.json");
        assertTrue(G1.isConnected());
        assertTrue(G2.isConnected());
        assertTrue(G3.isConnected());
        G1.getGraph().removeNode(0);
        G1.getGraph().removeNode(10);
        assertFalse(G1.isConnected());
        G1.getGraph().connect(16, 9, 1.5);
        assertTrue(G1.isConnected());
    }

    @Test
    public void center_Test()
    {
        G1.load("data/G1.json");
        G2.load("data/G2.json");
        G3.load("data/G3.json");
        assertEquals(G1.center().getKey(), 8);
        assertEquals(G2.center().getKey(), 0);
        assertEquals(G3.center().getKey(), 40);
        G2.getGraph().removeNode(1);
        G2.getGraph().removeNode(8);
        assertNull(G2.center());
        G2.getGraph().connect(26, 27, 0.7);
        assertNotNull(G2.center());
    }

    @Test
    public void TPS_Test()
    {

    }


}