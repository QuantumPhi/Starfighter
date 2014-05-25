package game.ship.modules.weapons;

import game.ship.Ship;
import game.ship.modules.projectiles.Projectile;

public class Weapon {
    
    protected Projectile template;
    protected int delay;
    protected int curDel = 0;
    
    /**
     * @param projectile Projectile of weapon
     */
    public Weapon(Projectile projectile) {
        template = projectile;
    }
    
    public void update(int delta) {
        if(curDel > 0)
            curDel -= delta;
        curDel = Math.max(curDel, 0);
    }
    
    public Projectile fire(Ship s) {
        if(curDel == 0) {
            Projectile p = template.createProjectile(s);
            curDel = delay;
            return p;
        }
        return null;
    }
    
    public int getDamage() { return template.getBaseDamage(); }
}
