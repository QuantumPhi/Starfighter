package game.ship.util;

public class Vector {
    
    private double maxSpd;
    private double speed;
    private double angle;
    
    public Vector(double s) {
        maxSpd = s;
    }
    
    public void accelerate(double accel, int delta) {
        speed = speed + delta * accel <= speed ? 
                speed + delta * accel : speed;
    }
    
    public void decelerate(double accel, int delta) {
        speed = speed - delta * accel >= -speed / 2 ? 
                speed - delta * accel : -speed / 2; 
    }
    
    public void turnRight(int turn, int delta) {
        angle = angle + delta * turn < 360 ?
                angle + delta * turn : 0;
    }
    
    public void turnLeft(int turn, int delta) {
        angle = angle - delta * turn >= 0 ?
                angle - delta * turn : 360 + (angle - delta * turn);
    }
    
    public double getSpeed() { return speed; }
    public double getAngle() { return angle; }
    
    public void setSpeed(double s) { speed = s; }
    public void setAngle(double a) { angle = a; }
}
