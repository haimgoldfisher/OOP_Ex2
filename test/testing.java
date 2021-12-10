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
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testing {
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

    @Test void create_new_rand_Graph_Test()
    {
        MyDirectedWeightedGraphAlgorithms algo = new MyDirectedWeightedGraphAlgorithms();
        algo.initRandomGraph(1);
        algo.save("output");
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
    }

    @Test
    public void TPS_Test()
    {

    }


}
