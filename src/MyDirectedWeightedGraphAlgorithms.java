import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import com.google.gson.*;

import java.io.*;
import java.util.*;


import java.util.List;
import java.util.List;

public class MyDirectedWeightedGraphAlgorithms implements DirectedWeightedGraphAlgorithms {
    private MyDirectedWeightedGraph graph;
    HashMap<Integer,HashMap<Integer,Double>> id_distances;

    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = (MyDirectedWeightedGraph) g;
        this.id_distances = new HashMap<>();
        HashSet<Integer> keys = new HashSet<>(this.graph.getKey_node().keySet());
        for (int key : keys) {
            HashMap<Integer,Double> distances = DijkstraAlgorithm(key);
            id_distances.put(key,distances);
        }
    }


    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        return new MyDirectedWeightedGraph(this.graph);
    }

    @Override
    public boolean isConnected() {
        if (this.graph.nodeSize() > this.graph.edgeSize() + 1)
            return false; // in connected graph we have at least n-1 edges for n vertices
        Node n = (Node) this.graph.getNode(1); // ???
        return this.graph.BFS_search(n) == this.graph.nodeSize();
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        HashMap<Integer, Double> distances = new HashMap();
        ArrayList<Integer> not_visited = new ArrayList<>();
        HashSet<Integer> keys = new HashSet<>(this.graph.getKey_node().keySet());
        for (int key : keys) {
            distances.put(key, Double.MAX_VALUE);
            not_visited.add(key);
        }
        distances.put(src, (double) 0);
        while (!not_visited.isEmpty()) {
            int min_id = minDistance(not_visited, distances);
            Node curr_node = (Node) this.graph.getNode(min_id);
            not_visited.remove((Object) min_id);
            for (EdgeData edge : curr_node.edges_to_children) {
                Edge curr_edge = (Edge) edge;
                int curr_dest = curr_edge.getDest();
                if (!not_visited.contains(curr_dest)) {//necessary?
                    continue;
                }
                double curr_weight = distances.get(curr_dest);
                double new_weight = distances.get(min_id) + curr_edge.getWeight();
                if (new_weight < curr_weight) {
                    distances.put(curr_dest, new_weight);
                }
            }
        }
        double shortest = distances.get(dest);
        if (shortest == Double.MAX_VALUE) {
            return -1;
        } else {
            return shortest;
        }
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        LinkedList<NodeData> path = new LinkedList<>();
        HashMap<Integer, Integer> previous = new HashMap();
        HashMap<Integer, Double> distances = new HashMap();
        ArrayList<Integer> not_visited = new ArrayList<>();
        HashSet<Integer> keys = new HashSet<>(this.graph.getKey_node().keySet());
        for (int key : keys) {
            distances.put(key, Double.MAX_VALUE);
            not_visited.add(key);
        }
        distances.put(src, (double) 0);
        while (!not_visited.isEmpty()) {
            int curr_src = minDistance(not_visited, distances);
            Node curr_node = (Node) this.graph.getNode(curr_src);
            not_visited.remove((Object) curr_src);
            for (EdgeData edge : curr_node.edges_to_children) {
                Edge curr_edge = (Edge) edge;
                int curr_dest = curr_edge.getDest();
                if (!not_visited.contains(curr_dest)) {//necessary?
                    continue;
                }
                double curr_weight = distances.get(curr_dest);
                double new_weight = distances.get(curr_src) + curr_edge.getWeight();
                if (new_weight < curr_weight) {
                    distances.put(curr_dest, new_weight);
                    previous.put(curr_dest, curr_src);
                }
            }
        }
        int curr = dest;
        while (curr != src){
            NodeData curr_node = this.graph.getNode(curr);
            path.addFirst(curr_node);
            curr = previous.get(curr);
        }
        NodeData src_node = this.graph.getNode(src);
        path.addFirst(src_node);
        return path;
    }

    @Override
    public NodeData center() {

        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) throws JsonParseException // file = the name of the new JSON
    {
        Gson myGson = new Gson();
        try {
            myGson.toJson(this.graph, new FileWriter(file)); // convert the graph into new json
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        //String jsonStringForm = myGson.toJson(graph);
        return true;
    }

    @Override
    public boolean load(String file) throws JsonParseException  // file = the path of the JSON file
    {
        Gson myGson = new Gson();
        try {
            this.graph = myGson.fromJson(new FileReader(file), MyDirectedWeightedGraph.class);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
            return false;
        }
//        String jsonFields = "{'Nodes': '???', 'Edges': '???'}";
//        MyDirectedWeightedGraph graph = myGson.fromJson(jsonFields, MyDirectedWeightedGraph.class);
//        try {
//            JsonElement json = myGson.fromJson(new FileReader(file), JsonElement.class);
//        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return false;
//        }
//        String res = myGson.toJson(jsonFields);
        return true; // the file could not be loaded
    }

    private HashMap<Integer, Double> DijkstraAlgorithm(int src) {
        HashMap<Integer, Double> distances = new HashMap();
        ArrayList<Integer> not_visited = new ArrayList<>();
        HashSet<Integer> keys = new HashSet<>(this.graph.getKey_node().keySet());
        for (int key : keys) {
            distances.put(key, Double.MAX_VALUE);
            not_visited.add(key);
        }
        distances.put(src, (double) 0);
        while (!not_visited.isEmpty()) {
            int min_id = minDistance(not_visited, distances);
            Node curr_node = (Node) this.graph.getNode(min_id);
            not_visited.remove((Object) min_id);
            for (EdgeData edge : curr_node.edges_to_children) {
                Edge curr_edge = (Edge) edge;
                int curr_dest = curr_edge.getDest();
                if (!not_visited.contains(curr_dest)) {//necessary?
                    continue;
                }
                double curr_weight = distances.get(curr_dest);
                double new_weight = distances.get(min_id) + curr_edge.getWeight();
                if (new_weight < curr_weight) {
                    distances.put(curr_dest, new_weight);
                }
            }
        }
        return distances;
    }

    private int minDistance(ArrayList<Integer> not_visited, HashMap<Integer, Double> distances) {
        int ans_id = -1;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < not_visited.size(); i++) {
            int curr_id = not_visited.get(i);
            double curr_distance = distances.get(curr_id);
            if (curr_distance < min) {
                min = curr_distance;
                ans_id = curr_id;
            }
        }
        return ans_id;
    }

}
