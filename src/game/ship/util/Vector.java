package game.ship.util;

public class Vector {
    
    private double maxSpd;
    private double speed;
    private double angle;
    
    public Vector(double s) {
        maxSpd = s;
    }
    
    public void accelerate(double accel, int delta) {
        speed = Math.min(speed + accel * delta, maxSpd);
    }
    
    public void decelerate(double decel, int delta) {
        speed = Math.max(speed - decel * delta, -maxSpd / 2);
    }
    
    public void friction(double friction, int delta) {
        speed = speed >= 0 ? Math.max(speed - friction * delta, 0) : Math.min(speed + friction * delta, 0);
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
    
    public void setSpeed(double s) { speed = s; }
    public void setAngle(double a) { angle = a; }
}
