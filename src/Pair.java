import java.util.Comparator;

public class Pair implements Comparator<Pair> {
    private int key;
    private double distance;

    public Pair() {
    }

    public Pair(int key, double distance) {
        this.key = key;
        this.distance = distance;
    }

    public int getKey() {
        return key;
    }

    public double getDistance() {
        return distance;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public int compare(Pair nd1, Pair nd2) {
        if (nd1.distance < nd2.distance) {
            return -1;
        }
        if (nd1.distance > nd2.distance) {
            return 1;
        }

        return 0;
    }
}
