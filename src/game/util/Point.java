package game.util;

public class Point {
    
    public double x, y;
    
    public Point(double px, double py) {
        x = px;
        y = py;
    }
    
    public boolean intersects(Circle other, double cx, double cy, double angle) {
        return other.isInCircle(cx + 4*x*Math.cos(angle), cy + 4*y*Math.sin(angle));
    }
}
