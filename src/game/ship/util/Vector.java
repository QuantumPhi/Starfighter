package game.ship.util;

public class Vector {
    
    private double maxSpd;
    private double speed;
    private double angle;
    
    public Vector(double s) {
        maxSpd = s;
    }
    
    public void accelerate(double accel, int delta, double limit) {
        speed = Math.min(speed + accel * delta, maxSpd);
        if(Math.abs(speed) <= 0.1)
            speed = 0;
    }
    
    public void decelerate(double decel, int delta, double limit) {
        accelerate(-decel, delta, limit);
    }
    
    public void friction(double friction, int delta) {
        if(speed >= 0) decelerate(friction, delta, 0);
        else accelerate(friction, delta, 0);
    }
    
    public void turnRight(double turn, int delta) {
        angle += turn;
        angle %= 360;
    }
    
    public void turnLeft(double turn, int delta) {
        angle -= turn;
        angle = angle < 0 ? 360 + angle : angle;
    }
    
    public double getSpeed() { return speed; }
    public double getAngle() { return angle; }
    
    public double getAccelLimit() { return maxSpd; }
    public double getDecelLimit() { return -maxSpd / 2; }
    
    public void setSpeed(double s) { speed = s; }
    public void setAngle(double a) { angle = a; }
}
