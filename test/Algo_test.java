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
import java.util.*;
import java.util.concurrent.TimeoutException;

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
    public void graph_Test()
    {
        MyGeoLocation loc1 = new MyGeoLocation(0,1,0);
        MyGeoLocation loc2 = new MyGeoLocation(1,0,0);
        MyGeoLocation loc3 = new MyGeoLocation(1,1,0);
        MyGeoLocation loc4 = new MyGeoLocation(0,0,0);
        Node n1 = new Node(0, loc4);
        Node n2 = new Node(1, loc1);
        Node n3 = new Node(2, loc3);
        Node n4 = new Node(3, loc2);
        MyDirectedWeightedGraph graph = new MyDirectedWeightedGraph();
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.connect(0,1, 6);
        graph.connect(1,2,1);
        graph.connect(2,3,2);
        graph.connect(3,0,4);
        graph.connect(3,1,8);
        graph.connect(0,2,9);
        assertEquals(2, n1.children_ids.size());
        assertEquals(1, n2.children_ids.size());
        assertEquals(2, n2.parents_ids.size());
    }

    @Test
    public void iter_Test()
    {
        MyGeoLocation loc1 = new MyGeoLocation(0,1,0);
        MyGeoLocation loc2 = new MyGeoLocation(1,0,0);
        MyGeoLocation loc3 = new MyGeoLocation(1,1,0);
        MyGeoLocation loc4 = new MyGeoLocation(0,0,0);
        Node n1 = new Node(0, loc4);
        Node n2 = new Node(1, loc1);
        Node n3 = new Node(2, loc3);
        Node n4 = new Node(3, loc2);
        MyDirectedWeightedGraph graph = new MyDirectedWeightedGraph();
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.connect(0,1, 6);
        graph.connect(1,2,1);
        graph.connect(2,3,2);
        graph.connect(3,0,4);
        graph.connect(3,1,8);
        graph.connect(0,2,9);

        int conter = 0;
        Iterator<NodeData> nodeIT = graph.nodeIter();
        while (nodeIT.hasNext()){
            nodeIT.next();
            conter++;
        }
        assertEquals(4, conter);
        conter = 0;
        Iterator<EdgeData> edgeIT = graph.edgeIter();
        while (edgeIT.hasNext()){
            edgeIT.next();
            conter++;
        }
        assertEquals(6, conter);
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
        G1.getGraph().connect(9, 16, 1.5);
        G1.getGraph().connect(16, 9, 9.5);
        assertTrue(G1.isConnected());
    }

    @Test
    public void dfs_Test()
    {
        G1.load("data/G1.json");
        MyDirectedWeightedGraph graph = (MyDirectedWeightedGraph) G1.getGraph();
        assertEquals(graph.myDFS(0), graph.nodeSize());
        assertEquals(graph.myDFS(0), graph.myDFS(16));

        graph.removeNode(0);
        assertEquals(16, graph.myDFS(1));
        graph.removeNode(10);
        assertEquals(9, graph.myDFS(1));
    }

    @Test
    public void dijkstra_Test()
    {
        G1.load("data/G1.json");
        assertEquals(1.232037506070033 , G1.DijkstraAlgorithm(0).get(1), 0.0);
    }

    @Test
    public void shortest_path_Test()
    {
        G1.load("data/G1.json");
        System.out.println(G1.shortestPath(0, 6));
        assertEquals(0, G1.shortestPath(0, 6).get(0).getKey());
        assertEquals(4, G1.shortestPath(0, 6).size());
        assertEquals(6, G1.shortestPath(0, 6).get(3).getKey());
        G1.getGraph().removeNode(0);
        G1.getGraph().removeNode(10);
        assertNull(G1.shortestPath(1, 11));
    }

    @Test
    public void shortest_path_dist_Test()
    {
        G1.load("data/G1.json");
        assertEquals(4.827508242889207, G1.shortestPathDist(0, 6), 0.0);
        G1.getGraph().removeNode(0);
        G1.getGraph().removeNode(10);
        assertEquals(-1, G1.shortestPathDist(1, 11), 0.0);
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
        G2.getGraph().connect(27, 26, 5.1);
        assertNotNull(G2.center());
    }

    @Test
    public void TPS_Test()
    {
        G3.load("data/G3.json");
        LinkedList<NodeData> cities = new LinkedList<NodeData>();
        cities.add(G3.getGraph().getNode(0));
        cities.add(G3.getGraph().getNode(10));
        cities.add(G3.getGraph().getNode(20));
        cities.add(G3.getGraph().getNode(35));
        assertEquals(G3.tsp(cities).get(0).getKey(), 0);
        assertEquals(G3.tsp(cities).size(), 14);
        assertEquals(G3.tsp(cities).get(13).getKey(), 20);
    }

    @Test
    public void create_new_rand_Graph_Test() // for inner using - RunTime calc
    {
        MyDirectedWeightedGraphAlgorithms algo = new MyDirectedWeightedGraphAlgorithms();
        algo.initRandomGraph(5);
//        algo.isConnected();
//        algo.shortestPath(0,999999);
//        algo.shortestPathDist(0,999999);
        algo.center();
        System.out.println(algo.center());
//        LinkedList<NodeData> cities = new LinkedList<NodeData>();
//        cities.add(algo.getGraph().getNode(0));
//        cities.add(algo.getGraph().getNode(2500));
//        cities.add(algo.getGraph().getNode(5000));
//        cities.add(algo.getGraph().getNode(7500));
//        algo.tsp(cities);
//        algo.save("output");
    }
}