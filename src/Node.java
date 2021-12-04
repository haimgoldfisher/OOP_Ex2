import api.GeoLocation;

//import api.NodeData;
public class Node implements api.NodeData {
    private int key;
    private GeoLocation location;
    private double weight;
    private String info;
    private int tag;

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
