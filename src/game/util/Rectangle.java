package game.util;

public class Rectangle {
    
    private double x, y, ax, ay, bx, by;
    
    public Rectangle(double px, double py, double qx, double qy) {
        ax = px;
        ay = py;
        bx = qx;
        by = qy;
    }
    
    public boolean intersects(Circle o) {
        boolean iax = Math.hypot(o.getX() - (x + ax*4), o.getY() - (y + ay*4)) <= o.getRadius();
        boolean ibx = Math.hypot(o.getX() - (x + ax*4), o.getY() - (y + by*4)) <= o.getRadius();
        boolean icx = Math.hypot(o.getX() - (x + bx*4), o.getY() - (y + ay*4)) <= o.getRadius();
        boolean idx = Math.hypot(o.getX() - (x + bx*4), o.getY() - (y + by*4)) <= o.getRadius();
        
        return iax || ibx || icx || idx;
    }
    
    public void setX(double px) { x = px; }
    public void setY(double py) { y = py; }
}
