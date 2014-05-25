package game.ship.modules.weapons;

import game.ship.util.Vector;
import org.newdawn.slick.Image;

public abstract class Weapon {
    
    protected Image projectile;
    protected int damage;
    protected Vector velocity;
    
    /**
     * @param p Projectile of weapon
     * @param d Damage of weapon
     */
    public Weapon(Image p, int d) {
        projectile = p;
        damage = d;
    }
    
    public abstract void update();
    public abstract void render();
    
    public int getDamage() { return damage; }
}
