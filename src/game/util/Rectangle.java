package game.util;

public class Rectangle {
    
    private double ax, ay, bx, by;
    
    public Rectangle(double px, double py, double qx, double qy) {
        ax = px;
        ay = py;
        bx = qx;
        by = qy;
    }
    
    public boolean intersects(Circle o) {
        boolean iax = Math.hypot(o.getX() - ax, o.getY() - ay) <= o.getRadius();
        boolean ibx = Math.hypot(o.getX() - ax, o.getY() - by) <= o.getRadius();
        boolean icx = Math.hypot(o.getX() - bx, o.getY() - ay) <= o.getRadius();
        boolean idx = Math.hypot(o.getX() - bx, o.getY() - by) <= o.getRadius();
        
        return iax || ibx || icx || idx;
    }
}
