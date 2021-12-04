import api.GeoLocation;

public class MyGeoLocation implements GeoLocation {
    private double  x;
    private double  y;
    private double  z;

    public MyGeoLocation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public MyGeoLocation(GeoLocation p) {
        this.x = p.x();
        this.y = p.y();
        this.z = p.z();
    }

    @Override
    public double x() {
        return x;
    }

    @Override
    public double y() {
        return y;
    }

    @Override
    public double z() {
        return z;
    }

    @Override
    public double distance(GeoLocation g) {
        double x_squared= (this.x - g.x())*(this.x - g.x());
        double y_squared= (this.y - g.y())*(this.y - g.y());
        double z_squared= (this.z - g.z())*(this.z - g.z());
        double d = Math.sqrt(x_squared+y_squared+z_squared);
        return d;
    }
}
