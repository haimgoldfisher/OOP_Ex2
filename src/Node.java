import api.EdgeData;
import api.GeoLocation;

import java.util.ArrayList;
import java.util.HashSet;

//import api.NodeData;
public class Node implements api.NodeData {
    private int key;
    private GeoLocation location;
    private double weight;
    private String info;
    private int tag;
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
        return this.getLocation();
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
}
