import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import java.awt.*;
import java.util.*;

//import api.NodeData;
public class Node implements api.NodeData {
    private int key;
    private GeoLocation location;
    private double weight;
    private String info;
    private int tag;
    public boolean discovered = false;
    public HashSet<Integer> parents_ids;
    public HashSet<Integer> children_ids;
    public HashSet<EdgeData> edges_to_children;

    public Node(int key, GeoLocation location) {
        this.key = key;
        this.location = location;
        this.parents_ids = new HashSet<>();
        this.children_ids = new HashSet<>();
        this.edges_to_children = new HashSet<>();
    }

    @Override
    public int getKey() {
        return this.key;
    }

    @Override
    public GeoLocation getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.location = new MyGeoLocation(p);// maybe need to new Geo location.
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public HashSet<Integer> getParents_ids() {
        return parents_ids;
    }

    public void setParents_ids(HashSet<Integer> parents_ids) {
        this.parents_ids = parents_ids;
    }

    public HashSet<Integer> getChildren_ids() {
        return children_ids;
    }

    public void setChildren_ids(HashSet<Integer> children_ids) {
        this.children_ids = children_ids;
    }

    public HashSet<EdgeData> getEdges_to_children() {
        return edges_to_children;
    }

    public void setEdges_to_children(HashSet<EdgeData> edges_to_children) {
        this.edges_to_children = edges_to_children;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

}
