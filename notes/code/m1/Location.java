public class Location {
    double x, y;

    public Location(double xInitial, double yInitial) {
        x = xInitial;
        y = yInitial;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static double distance(Location p1, Location p2) {
        if(p1 == null || p2 == null) return Double.NaN;
        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static Location midpoint(Location p1, Location p2) {
        if(p1 == null || p2 == null) return null;
        return new Location((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2);
    }

    public void shift(double dx, double dy) {
        x += dx;
        y += dy;
    }

    public void rotate90() {
        double xNew, yNew;
        xNew = y;
        yNew = -x;
        x = xNew;
        y = yNew;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Location answer;
        try {
            answer = (Location) super.clone();
        } catch(CloneNotSupportedException e) {
            return null;
        }
        return answer;
    }

    @Override
    public String toString() {
        return "(x=" + x + " y=" + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Location) {
            Location tmp = (Location) obj;
            return (tmp.x == x) && (tmp.y == y);
        } else {
            return false;
        }
    }
}