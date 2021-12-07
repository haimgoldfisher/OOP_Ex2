import api.EdgeData;

public class Edge implements EdgeData {
//    private Node src;
//    private Node dest;
    private int src_key;
    private int dest_key;
    private double weight;
    private String info;
    private int tag;

    public Edge(int src_key, int dest_key, double weight) {
        this.src_key = src_key;
        this.dest_key = dest_key;
        this.weight = weight;
    }

    public void setSrc_key(int src_key) {
        this.src_key = src_key;
    }

    public void setDest_key(int dest_key) {
        this.dest_key = dest_key;
    }

    @Override
    public int getSrc() {
        return this.src_key;
    }

    @Override
    public int getDest() {
        return this.dest_key;
    }

    @Override
    public double getWeight() {
        return this.weight;
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
