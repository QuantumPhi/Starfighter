package game.util;

public class Rectangle {
    
    private double x, y, ax, ay, bx, by, r;
    
    public Rectangle(double px, double py, double qx, double qy) {
        ax = px;
        ay = py;
        bx = qx;
        by = qy;
    }
    
    public boolean intersects(Circle o) {
        boolean iax = o.isInCircle(x+4*ax*Math.cos(r), y+4*ay*Math.sin(r));
        boolean ibx = o.isInCircle(x+4*ax*Math.cos(r), y+4*by*Math.sin(r));
        boolean icx = o.isInCircle(x+4*bx*Math.cos(r), y+4*ay*Math.sin(r));
        boolean idx = o.isInCircle(x+4*bx*Math.cos(r), y+4*by*Math.sin(r));
        
        return iax || ibx || icx || idx;
    }
    
    public void setX(double px) { x = px; }
    public void setY(double py) { y = py; }
    public void setAngle(double pr) { r = pr; }
}
