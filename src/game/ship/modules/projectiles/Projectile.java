package game.ship.modules.projectiles;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Projectile {
    
    protected Image sprite;
    protected int baseDamage;
    
    public Projectile(Image s) {
        sprite = s;
    }
    
    public void update(int delta) {
        
    }
    
    public void render(Graphics g) {
        
    }
    
    public int getBaseDamage() { return baseDamage; }
    public abstract int getShieldDamage();
    public abstract int getHullDamage();
}
