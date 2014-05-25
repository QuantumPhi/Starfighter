package game.ship;

import game.ship.modules.Frame;
import game.ship.modules.Weapon;
import game.ship.util.Vector;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Ship {
    
    protected long id;
    
    protected double x, y;
    
    protected Image sprite;
    protected Vector velocity;
    protected double turn;
    protected double accel;
    
    protected Frame ship;
    protected Weapon[] weapons;
    
    /** 
     * @param i ID for the ship 
     * @param maxSpeed Maximum speed of the ship
     **/
    public Ship(long i, double maxSpeed) {
        id = i;
        velocity = new Vector(maxSpeed);
    }
    
    public Ship(long i, ShipType type) {
        id = i;
        sprite = type.getSprite();
        velocity = new Vector(type.maxSpeed());
        turn = type.getTurn();
        accel = type.getAccel();
        
    }
    
    public void resolveHit(int damage) {
        ship.resolveHit(damage);
    }
    
    public abstract void update(GameContainer container, int delta);
    public abstract void render(Graphics g);
    
    public long getID() { return id; }
    
    public double getX() { return x; }
    public double getY() { return y; }
    public double getSpeed() { return velocity.getSpeed(); }
    public double getAngle() { return velocity.getAngle(); }
    
    public void setX(double px) { x = px; }
    public void setY(double py) { y = py; }
    public void setSpeed(double speed) { velocity.setSpeed(speed); }
    public void setAngle(double angle) { velocity.setAngle(angle); }
}
