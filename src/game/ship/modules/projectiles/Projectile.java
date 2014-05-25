package game.ship.modules.projectiles;

import game.network.DataPacket;
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
    protected double ex, ey;
    protected int baseDamage;
    
    public Projectile(Image s) {
        sprite = s;
        baseDamage = 5;
    }
    
    public Projectile(DataPacket p) {
        id = (long)p.getClient();
        parentID = (long)p.getDouble(DataPacket.PARENT);
        baseDamage = 5;
    }
    
    public Projectile(long i, long si, Image s) {
        this(s);
        id = i;
        parentID = si;
        baseDamage = 5;
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
    
    public void setX(double px) { x = px; ex = px; }
    public void setY(double py) { y = py; ey = py; }
    public void setSpeed(double speed) { velocity.setSpeed(speed); }
    public void setAngle(double angle) { velocity.setAngle(angle); }
    
    public int getBaseDamage() { return baseDamage; }
    public abstract int getShieldDamage();
    public abstract int getHullDamage();
    
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Projectile))
            return false;
        
        Projectile p = (Projectile) o;
        
        return p.getID() == id && p.getParentID() == parentID;
    }
    
    public boolean equals(int id, int parentID) {
        return this.id == id && this.parentID == parentID;
    }
}
