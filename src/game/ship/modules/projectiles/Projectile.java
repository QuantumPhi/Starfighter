package game.ship.modules.projectiles;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Projectile {
    
    protected Image sprite;
    protected int baseDamage;
    
    public Projectile(Image s) {
        sprite = s;
    }
    
    public abstract void update(int delta); 
    public abstract void render(Graphics g);
    
    public abstract Projectile createProjectile();
    
    public int getBaseDamage() { return baseDamage; }
    public abstract int getShieldDamage();
    public abstract int getHullDamage();
}
