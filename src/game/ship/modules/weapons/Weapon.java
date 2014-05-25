package game.ship.modules.weapons;

import game.ship.util.Vector;

public abstract class Weapon {
    
    protected Projectile projectile;
    protected Vector velocity;
    
    /**
     * @param p Projectile of weapon
     */
    public Weapon(Projectile p) {
        projectile = p;
    }
    
    public abstract void update();
    public abstract void render();
    
    public abstract void fire();
    
    public int getDamage() { return projectile.getBaseDamage(); }
}
