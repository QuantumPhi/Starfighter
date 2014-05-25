package game.ship.modules.projectiles;

import game.ship.Ship;
import game.ship.util.Vector;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Projectile {
    
    protected long id;
    protected long parentID;
    protected Image sprite;
    protected Vector velocity;
    protected double x, y;
    protected int baseDamage;
    
    public Projectile(Image s) {
        sprite = s;
    }
    
    public Projectile(long i, long si, Image s) {
        this(s);
        id = i;
        parentID = si;
    }
    
    public abstract void update(int delta); 
    public abstract void render(Graphics g);
    
    public abstract Projectile createProjectile(Ship s);
    
    public long getID() { return id; }
    public long getParentID() { return parentID; }
    public double getX() { return x; }
    public double getY() { return y; }
    public double getSpeed() { return velocity.getSpeed(); }
    public double getAngle() { return velocity.getAngle(); }
    
    public void setX(double px) { x = px; }
    public void setY(double py) { y = py; }
    public void setSpeed(double speed) { velocity.setSpeed(speed); }
    public void setAngle(double angle) { velocity.setAngle(angle); }
    
    public int getBaseDamage() { return baseDamage; }
    public abstract int getShieldDamage();
    public abstract int getHullDamage();
}
