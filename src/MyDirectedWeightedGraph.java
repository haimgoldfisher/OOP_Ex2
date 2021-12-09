import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.awt.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MyDirectedWeightedGraph implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> key_node;
    private HashMap<ArrayList<Integer>, EdgeData> keys_edge;
    private int mc = 0;

    public MyDirectedWeightedGraph() {
        this.key_node = new HashMap<Integer, NodeData>();
        this.keys_edge = new HashMap<ArrayList<Integer>, EdgeData>();
    }

    public MyDirectedWeightedGraph(DirectedWeightedGraph graph) // copy constructor
    {
        MyDirectedWeightedGraph g = (MyDirectedWeightedGraph) graph;
        this.key_node = new HashMap<Integer, NodeData>();
        this.keys_edge = new HashMap<ArrayList<Integer>, EdgeData>(g.keys_edge);
        for (int key : g.key_node.keySet()) {
            Node curr_node = (Node) g.key_node.get(key);
            Node new_nd = new Node(curr_node);
            this.key_node.put(key, new_nd);
        }
    }

    public void setKey_node(HashMap<Integer, NodeData> key_node) {
        this.key_node = key_node;
    }

    public void setKeys_edge(HashMap<ArrayList<Integer>, EdgeData> keys_edge) {
        this.keys_edge = keys_edge;
    }

    public HashMap<Integer, NodeData> getKey_node() {
        return key_node;
    }

    public HashMap<ArrayList<Integer>, EdgeData> getKeys_edge() {
        return keys_edge;
    }

    public void setMc(int mc) {
        this.mc = mc;
    }


    @Override
    public NodeData getNode(int key) {
        return key_node.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        ArrayList<Integer> edge_keys = new ArrayList<>();
        edge_keys.add(src);
        edge_keys.add(dest);
        return keys_edge.get(edge_keys);
    }

    @Override
    public void addNode(NodeData n) {
        int new_key = n.getKey();
        Node new_node = (Node) n;
        key_node.put(new_key, new_node);
        mc++;
    }

    @Override
    public void connect(int src, int dest, double w) {
//        int[] new_edge_keys = {src, dest};
        ArrayList<Integer> new_edge_keys = new ArrayList<>(2);
        new_edge_keys.add(src);
        new_edge_keys.add(dest);
        Edge new_edge = new Edge(src, dest, w);
        keys_edge.put(new_edge_keys, new_edge);//add the new edge to the edges HashMap.
        Node dest_node = (Node) key_node.get(dest);
        dest_node.parents_ids.add(src);//add the src_id to the parents_ids HashSet of dest_node.
        Node src_node = (Node) key_node.get(src);
        src_node.children_ids.add(dest);//add the dest_id to the children_ids HashSet of src_node.
        src_node.edges_to_children.add(new_edge);//add the Edge to the Edge Hash set of src_node.
        mc++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        Collection<NodeData> nodes = key_node.values();
        return nodes.iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        Collection<EdgeData> edges = keys_edge.values();
        return edges.iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        Node nd = (Node) key_node.get(node_id);
        HashSet<EdgeData> edges = nd.edges_to_children;
        return edges.iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        mc++;
        Node curr_node = (Node) key_node.get(key);
        for (int parent_id : curr_node.parents_ids) {
//            ArrayList<Integer> edge_keys = new ArrayList<>();
            ArrayList<Integer> edge_keys =new ArrayList<>();
            edge_keys.add(parent_id);
            edge_keys.add(key);
            keys_edge.remove(edge_keys);
            Node parent_node = (Node) key_node.get(key);
            parent_node.children_ids.remove(key);
        }
        curr_node.parents_ids.clear();
        for (int child_id : curr_node.children_ids) {
            ArrayList<Integer> edge_keys = new ArrayList<>();
            edge_keys.add(key);
            edge_keys.add(child_id);
            keys_edge.remove(edge_keys);
            Node child_node = (Node) key_node.get(key);
            child_node.parents_ids.remove(key);
        }
        curr_node.children_ids.clear();
        curr_node.edges_to_children.clear();
        key_node.remove(key);
        return curr_node;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        mc++;
        ArrayList<Integer> edge_keys = new ArrayList<>(2);
        edge_keys.add(src);
        edge_keys.add(dest);
        Edge edge = (Edge) keys_edge.get(edge_keys);
        Node src_node = (Node) key_node.get(src);
        src_node.children_ids.remove(dest);
        src_node.edges_to_children.remove(edge);
        Node dest_node = (Node) key_node.get(dest);
        dest_node.parents_ids.remove(src);
        keys_edge.remove(edge_keys);
        return edge;
    }

    public int BFS_search(Node start) // a BFS algo, it returns the num of discovered nodes
    {
        int discoveredNodes = 0;
        Queue<Node> Q = new LinkedList<Node>(); // bfs algo works on a queue
        Node v = null;
        Q.add(start); // add the source node to the queue
        while (!Q.isEmpty()) {
            v = Q.remove(); // remove & return the first node in the queue
            v.discovered = true; // mark this node as discovered
            discoveredNodes++; // every time a node is being discovered - raise the counter
            if (v.parents_ids.size() > 0)
                for (int par : v.parents_ids) {
                    Node parent = (Node) this.getNode(par);
                    if (!parent.discovered)
                        Q.add(parent);
                }
            if (v.children_ids.size() > 0)
                for (int chil : v.children_ids) {
                    Node child = (Node) this.getNode(chil);
                    if (!child.discovered)
                        Q.add(child);
                }
        }
        return discoveredNodes; // the num of nodes which the algo has found
    }

    @Override
    public int nodeSize() {
        return this.key_node.size();
    }

    @Override
    public int edgeSize() {
        return this.keys_edge.size();
    }

    @Override
    public int getMC() {
        return this.mc;
    }


    public int myDFS(int start) {
        int counter = 0;
        HashMap<Integer, Boolean> key_visited = new HashMap<>();
        HashSet<Integer> keys = new HashSet<>(this.key_node.keySet());
        for (int key : keys) {
            key_visited.put(key, false);//0=white,1=gray,2=black.
        }
        Stack<Integer> s = new Stack<>();
        s.push(start);
        key_visited.put(start, true);
        counter++;
        while (!s.isEmpty()) {
            int curr_id = s.pop();
            Node curr_node = (Node) this.key_node.get(curr_id);
            for (int child : curr_node.children_ids) {
                if (!key_visited.get(child)) {
                    s.push(child);
                    key_visited.put(child, true);
                    counter++;
                }
            }
        }
        return counter;
    }
}
