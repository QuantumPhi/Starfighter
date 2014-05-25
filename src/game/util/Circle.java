package game.util;

import game.ship.ShipType;

public class Circle {
    
    private double radius;
    private double x, y;
    
    public Circle(ShipType type) {
        radius = type.getRadius();
    }
    
    public boolean isInCircle(double ox, double oy) {
        return Math.hypot(x-ox,y-ox) <= radius;
    }
    
    public double getRadius() { return radius; }
    public double getX() { return x; }
    public double getY() { return y; }
    
    public void setX(double px) { x = px; }
    public void setY(double py) { y = py; }
}
