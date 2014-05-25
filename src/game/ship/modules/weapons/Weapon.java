package game.ship.modules.weapons;

import game.ship.Ship;
import game.ship.modules.projectiles.Projectile;

public abstract class Weapon {
    
    protected Projectile template;
    
    /**
     * @param projectile Projectile of weapon
     */
    public Weapon(Projectile projectile) {
        template = projectile;
    }
    
    public abstract void fire(Ship s);
    
    public int getDamage() { return template.getBaseDamage(); }
}
