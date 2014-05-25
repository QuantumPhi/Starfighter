package game.util;

public class Point {
    
    public double x, y;
    
    public Point(double px, double py) {
        x = px;
        y = py;
    }
    
    public boolean intersects(Circle other, double lx, double ly, double angle) {
        return other.isInCircle(lx+x*Math.cos(Math.toRadians(angle)),ly+y*Math.sin(Math.toRadians(angle)));
    }
}
