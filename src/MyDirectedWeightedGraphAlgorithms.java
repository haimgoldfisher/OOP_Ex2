import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;
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
//        if (this.graph.nodeSize() > this.graph.edgeSize() + 1)
//            return false; // in connected graph we have at least n-1 edges for n vertices
//        Node n = (Node) this.graph.getNode(1); // ???
//        return this.graph.BFS_search(n) == this.graph.nodeSize();
        TreeSet <Integer>keys = new TreeSet<>(this.graph.getKey_node().keySet());
        int key = keys.first();
        int visited = this.graph.myDFS(key);
        if(visited!=this.graph.nodeSize()){
            return false;
        }
        MyDirectedWeightedGraph reversed_g = reverse(graph);
        visited = reversed_g.myDFS(key);
        if(visited!=this.graph.nodeSize()){
            return false;
        }
        return true;
    }

    public static MyDirectedWeightedGraph reverse(MyDirectedWeightedGraph graph) {
        MyDirectedWeightedGraph ans = new MyDirectedWeightedGraph(graph);
        ArrayList<ArrayList<Integer>> keys_list= new ArrayList<>();
        for (ArrayList<Integer> key: ans.getKeys_edge().keySet()) {
            keys_list.add(key);
        }
        for (int i = 0; i < keys_list.size(); i++) {
            ArrayList<Integer> key = keys_list.get(i);
            EdgeData removed_edge = ans.removeEdge(key.get(0),key.get(1));
        }
        for (ArrayList<Integer> key: graph.getKeys_edge().keySet()) {
            EdgeData edge = graph.getEdge(key.get(0),key.get(1));
            ans.connect(key.get(1),key.get(0),edge.getWeight());
        }
        return ans;
    }


    @Override
    public double shortestPathDist(int src, int dest) {
        HashMap<Integer, Double> distances = new HashMap();
        HashSet<Integer> visited = new HashSet<>();
        HashSet<Integer> keys = new HashSet<>(this.graph.getKey_node().keySet());
        PriorityQueue<Pair> pq = new PriorityQueue<>(keys.size(),new Pair());
        for (int key : keys) {
            distances.put(key, Double.POSITIVE_INFINITY);
        }
        distances.put(src, (double) 0);
        pq.add(new Pair(src,0));
        while (visited.size()!= keys.size()) {
            if(pq.isEmpty()){
                break;
            }
            int curr_key = pq.remove().getKey();
            if (curr_key==dest){
                break;
            }
            if (visited.contains(curr_key)){
                continue;
            }
            visited.add(curr_key);
            Node curr_node = (Node) this.graph.getNode(curr_key);
            for (EdgeData edge : curr_node.edges_to_children) {
                Edge curr_edge = (Edge) edge;
                int curr_dest = curr_edge.getDest();
                if (visited.contains(curr_dest)) {//necessary?
                    continue;
                }
                double curr_weight = distances.get(curr_dest);
                double new_weight = distances.get(curr_key) + curr_edge.getWeight();
                if (new_weight < curr_weight) {
                    distances.put(curr_dest, new_weight);
                }
                pq.add(new Pair (curr_dest,distances.get(curr_dest)));
            }
        }
        double shortest = distances.get(dest);
        if (shortest == Double.POSITIVE_INFINITY) {
            return -1;
        } else {
            return shortest;
        }
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        LinkedList<NodeData> path = new LinkedList<>();
        if(src ==dest){
            path.add(this.graph.getKey_node().get(src));
            return path;
        }
        HashMap<Integer, Integer> previous = new HashMap();
        HashMap<Integer, Double> distances = new HashMap();
        HashSet<Integer> visited = new HashSet<>();
        HashSet<Integer> keys = new HashSet<>(this.graph.getKey_node().keySet());
        PriorityQueue<Pair> pq = new PriorityQueue<>(keys.size(),new Pair());
        for (int key : keys) {
            distances.put(key, Double.POSITIVE_INFINITY);
        }
        distances.put(src, (double) 0);
        pq.add(new Pair(src,0));
        while (visited.size()!= keys.size()) {
            if(pq.isEmpty()){
                break;
            }
            int curr_key = pq.remove().getKey();
            if (curr_key==dest){
                break;
            }
            if (visited.contains(curr_key)){
                continue;
            }
            visited.add(curr_key);
            Node curr_node = (Node) this.graph.getNode(curr_key);
            for (EdgeData edge : curr_node.edges_to_children) {
                Edge curr_edge = (Edge) edge;
                int curr_dest = curr_edge.getDest();
                if (visited.contains(curr_dest)) {
                    continue;
                }
                double curr_weight = distances.get(curr_dest);
                double new_weight = distances.get(curr_key) + curr_edge.getWeight();
                if (new_weight < curr_weight) {
                    distances.put(curr_dest, new_weight);
                    previous.put(curr_dest, curr_key);
                }
                pq.add(new Pair (curr_dest,distances.get(curr_dest)));
            }
        }
        if(distances.get(dest)== Double.POSITIVE_INFINITY){
            return null;
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
        if(!isConnected()){
            return null;
        }
        HashMap<Integer,Double> e = new HashMap<>();
        double rad = Double.POSITIVE_INFINITY;
        for (int src: id_distances.keySet()) {
            e.put(src, (double) 0);
        }
        for (int src: id_distances.keySet()) {
            for (int dest: id_distances.keySet()) {
                double curr_dist = id_distances.get(src).get(dest);
                e.put(src,Math.max(e.get(src),curr_dist));
            }
        }
        for (int key: e.keySet()) {
            rad = Math.min(rad,e.get(key));
        }
        for (int key: e.keySet()) {
            if(e.get(key) == rad){
                return this.graph.getNode(key);
            }
        }
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        ArrayList<List<NodeData>> all_paths = getAllPermutation(cities);
        int min_index = -1;
        double min_cost = Double.POSITIVE_INFINITY;
        for (int i = 0; i < all_paths.size(); i++) {
            List<NodeData> curr_path = all_paths.get(i);
            double curr_cost = calcCost(curr_path);
            if(curr_cost<min_cost){
                min_cost = curr_cost;
                min_index = i;
            }
        }
        if (min_cost == Double.POSITIVE_INFINITY){
            return null;
        }
        List<NodeData> shortest_path = all_paths.get(min_index);
        return shortest_path;
    }

    private double calcCost(List<NodeData> curr_path) {
        double cost = 0;
        for (int i = 0; i < curr_path.size()-1; i++) {
            NodeData src = curr_path.get(i);
            NodeData dest = curr_path.get(i+1);
            cost += id_distances.get(src.getKey()).get(dest.getKey());
        }
        return cost;
    }

    private static ArrayList<List<NodeData>> getAllPermutation(List<NodeData> cities){
        ArrayList<List<NodeData>> all_paths = new ArrayList<>();
        List<NodeData> curr_path = new ArrayList<>(cities);
        getAllPermutationRec(cities,curr_path,0,all_paths);
        return all_paths;
    }

    private static void getAllPermutationRec(List<NodeData> cities,List<NodeData> curr_path,int counter,ArrayList<List<NodeData>> all_paths){
        if(counter == cities.size()){
            List<NodeData> path_copy = new ArrayList<>(curr_path);
            all_paths.add(path_copy);
        }
        else {
            for (int i = counter; i < cities.size(); i++) {
                swap(curr_path, i, counter);
                getAllPermutationRec(cities, curr_path, counter + 1, all_paths);
                swap(curr_path, i, counter);
            }
        }
    }

    private static void swap(List<NodeData> x,int i,int j)
    {
        NodeData tempi = x.get(i);
        NodeData tempj = x.get(j);
        x.set(i,tempj);
        x.set(j,tempi);
    }

    @Override
    public boolean save(String file)
    {
        if (this.graph == null)
            return false;
        try {
            String outputPath = file;
            if (!outputPath.endsWith(".json"))
                outputPath += ".json";
            FileWriter fileWriter = new FileWriter(outputPath);
            Gson builder = new GsonBuilder().setPrettyPrinting().create();
            JsonElement json = new JsonObject();
            JsonArray Edges = new JsonArray();
            JsonArray Nodes = new JsonArray();
            Iterator<NodeData> nodeDataIterator = graph.nodeIter();
            int key, src, dest;
            double weight;
            while (nodeDataIterator.hasNext()) {
                JsonObject node = new JsonObject();
                NodeData currNode = nodeDataIterator.next();
                String strPos = currNode.getLocation().x()+","+currNode.getLocation().y()+","+currNode.getLocation().z();
                key = currNode.getKey();
                node.getAsJsonObject().addProperty("pos", strPos);
                node.getAsJsonObject().addProperty("id", key);
                Nodes.add(node);
            }
            Iterator<EdgeData> edgeDataIterator = graph.edgeIter();
            while (edgeDataIterator.hasNext()) {
                JsonObject edge = new JsonObject();
                EdgeData currEdge = edgeDataIterator.next();
                src = currEdge.getSrc();
                weight = currEdge.getWeight();
                dest = currEdge.getDest();
                edge.getAsJsonObject().addProperty("src", src);
                edge.getAsJsonObject().addProperty("w", weight);
                edge.getAsJsonObject().addProperty("dest", dest);
                Edges.add(edge);
            }
            json.getAsJsonObject().add("Edges", Edges);
            json.getAsJsonObject().add("Nodes", Nodes);
            fileWriter.write(builder.toJson(json));
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean load(String file) // the path of the JSON file
    {
        try {
            FileReader fileReader = new FileReader(file);
            GsonBuilder builder = new GsonBuilder();
            JsonDeserializer<MyDirectedWeightedGraph> deserializer = new JsonDeserializer<MyDirectedWeightedGraph>() {
                @Override
                public MyDirectedWeightedGraph deserialize(JsonElement json, Type typeOf, JsonDeserializationContext context) throws JsonParseException {
                    JsonObject jsonObj = json.getAsJsonObject();
                    MyDirectedWeightedGraph newGraph = new MyDirectedWeightedGraph();
                    JsonArray jsonArrayNodes = jsonObj.get("Nodes").getAsJsonArray();
                    double weight, x, y, z;
                    for (JsonElement currNode : jsonArrayNodes.getAsJsonArray()) {
                        int key = currNode.getAsJsonObject().get("id").getAsInt();
                        String posStr = currNode.getAsJsonObject().get("pos").getAsString();
                        String[] posParams = posStr.split(",");
                        x = Double.parseDouble(posParams[0]);
                        y = Double.parseDouble(posParams[1]);
                        z = Double.parseDouble(posParams[2]);
                        MyGeoLocation pos = new MyGeoLocation(x, y, z);
                        newGraph.addNode(new Node(key, pos));
                    }
                    JsonArray jsonArrayEdges = jsonObj.get("Edges").getAsJsonArray();
                    int src, dest;
                    for (JsonElement currEdge : jsonArrayEdges.getAsJsonArray()) {
                        src = currEdge.getAsJsonObject().get("src").getAsInt();
                        weight = currEdge.getAsJsonObject().get("w").getAsDouble();
                        dest = currEdge.getAsJsonObject().get("dest").getAsInt();
                        newGraph.connect(src, dest, weight);
                    }
                    return newGraph;
                }
            };
            builder.registerTypeAdapter(MyDirectedWeightedGraph.class, deserializer);
            Gson customGson = builder.create();
            MyDirectedWeightedGraph graph = customGson.fromJson(fileReader, MyDirectedWeightedGraph.class);
            System.out.println(graph);
            this.graph = graph;
            fileReader.close();
            return true;
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

//    private HashMap<Integer, Double> DijkstraAlgorithm(int src) {
//        HashMap<Integer, Double> distances = new HashMap();
//        ArrayList<Integer> not_visited = new ArrayList<>();
//        HashSet<Integer> keys = new HashSet<>(this.graph.getKey_node().keySet());
//        for (int key : keys) {
//            distances.put(key, Double.POSITIVE_INFINITY);
//            not_visited.add(key);
//        }
//        distances.put(src, (double) 0);
//        while (!not_visited.isEmpty()) {
//            int min_id = minDistance(not_visited, distances);
//            if(min_id == -1){
//                break;
//            }
//            Node curr_node = (Node) this.graph.getNode(min_id);
//            not_visited.remove((Object) min_id);
//            for (EdgeData edge : curr_node.edges_to_children) {
//                Edge curr_edge = (Edge) edge;
//                int curr_dest = curr_edge.getDest();
//                if (!not_visited.contains(curr_dest)) {//necessary?
//                    continue;
//                }
//                double curr_weight = distances.get(curr_dest);
//                double new_weight = distances.get(min_id) + curr_edge.getWeight();
//                if (new_weight < curr_weight) {
//                    distances.put(curr_dest, new_weight);
//                }
//            }
//        }
//        return distances;
//    }
//
//    private int minDistance(ArrayList<Integer> not_visited, HashMap<Integer, Double> distances) {
//        int ans_id = -1;
//        double min = Double.POSITIVE_INFINITY;
//        for (int i = 0; i < not_visited.size(); i++) {
//            int curr_id = not_visited.get(i);
//            double curr_distance = distances.get(curr_id);
//            if (curr_distance < min) {
//                min = curr_distance;
//                ans_id = curr_id;
//            }
//        }
//        return ans_id;
//    }

    public HashMap<Integer, Double> DijkstraAlgorithm(int src) {
        HashMap<Integer, Double> distances = new HashMap();
        HashSet<Integer> visited = new HashSet<>();
        HashSet<Integer> keys = new HashSet<>(this.graph.getKey_node().keySet());
        PriorityQueue<Pair> pq = new PriorityQueue<>(keys.size(),new Pair());
        for (int key : keys) {
            distances.put(key, Double.POSITIVE_INFINITY);
        }
        distances.put(src, (double) 0);
        pq.add(new Pair(src,0));
        while (visited.size()!= keys.size()) {
            if(pq.isEmpty()){
                break;
            }
            int curr_key = pq.remove().getKey();
            if (visited.contains(curr_key)){
                continue;
            }
            visited.add(curr_key);
            Node curr_node = (Node) this.graph.getNode(curr_key);
            for (EdgeData edge : curr_node.edges_to_children) {
                Edge curr_edge = (Edge) edge;
                int curr_dest = curr_edge.getDest();
                if (visited.contains(curr_dest)) {//necessary?
                    continue;
                }
                double curr_weight = distances.get(curr_dest);
                double new_weight = distances.get(curr_key) + curr_edge.getWeight();
                if (new_weight < curr_weight) {
                    distances.put(curr_dest, new_weight);
                }
                pq.add(new Pair (curr_dest,distances.get(curr_dest)));
            }
        }
        return distances;
    }

}
