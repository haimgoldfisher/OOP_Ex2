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

public class testing {

    @Test
    public void load_Test()
    {
        MyDirectedWeightedGraphAlgorithms graphAlgorithms = new MyDirectedWeightedGraphAlgorithms();
        graphAlgorithms.load("data/G1.json");
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



}
