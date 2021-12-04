import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.HashMap;
import java.util.Iterator;

public class MyDirectedWeightedGraph implements DirectedWeightedGraph {
    HashMap<Integer,Node> key_node;
    HashMap<int[],Edge> keys_edge;

    @Override
    public NodeData getNode(int key) {
        return key_node.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        int[] edge_keys = {src,dest};
        return keys_edge.get(edge_keys);
    }

    @Override
    public void addNode(NodeData n) {
        int new_key = n.getKey();
        Node new_node = (Node)n;
        key_node.put(new_key,new_node);
    }

    @Override
    public void connect(int src, int dest, double w) {
        int[] new_edge_keys = {src,dest};
        Edge new_edge = new Edge(src,dest,w);
        keys_edge.put(new_edge_keys,new_edge);
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return null;
    }

    @Override
    public NodeData removeNode(int key) {
        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        return null;
    }

    @Override
    public int nodeSize() {
        return 0;
    }

    @Override
    public int edgeSize() {
        return 0;
    }

    @Override
    public int getMC() {
        return 0;
    }
}
