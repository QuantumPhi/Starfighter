package game.util;

import game.ship.ShipType;

public class Circle {
    
    private double radius;
    private double x, y;
    
    public Circle(ShipType type) {
        radius = type.getRadius();
    }
    
    public boolean intersects(Circle o) {
        return Math.sqrt(Math.pow(o.getX() - x, 2) + Math.pow(o.getY() - y, 2)) 
                <= o.getRadius() + radius;
    }
    
    public double getRadius() { return radius; }
    public double getX() { return x; }
    public double getY() { return y; }
    
    public void setX(double px) { x = px; }
    public void setY(double py) { y = py; }
}
