package game.ship.modules.projectiles;

import game.ship.Ship;
import game.ship.util.Vector;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Projectile {
    
    protected Image sprite;
    protected Vector velocity;
    protected int x, y;
    protected int baseDamage;
    
    public Projectile(Image s) {
        sprite = s;
    }
    
    public abstract void update(int delta); 
    public abstract void render(Graphics g);
    
    public abstract Projectile createProjectile(Ship s);
    
    public int getBaseDamage() { return baseDamage; }
    public abstract int getShieldDamage();
    public abstract int getHullDamage();
}
