package game.ship.modules;

import game.ship.modules.projectiles.Projectile;

public class Frame {
    
    public Shield shield;
    public Hull hull;
    public Energy core;
    
    /**
     * @param is Health of the shields
     * @param rs Regeneration rate of the shields
     * @param ih Health of the hull
     * @param ee Energy of the core
     * @param re Regeneration rate of the core
     */
    public Frame(int is, int rs, int ih, int ee, int re) {
        shield = new Shield(is, rs);
        hull = new Hull(ih);
        core = new Energy(ee, re);
    }
    
    public void resolveHit(Projectile p) {
        if (shield.integrity > 0)
            shield.integrity = Math.max(shield.integrity-p.getShieldDamage(),0);
        else
            hull.integrity = Math.max(hull.integrity-p.getHullDamage(),0);
    }
    
    public void regenShields() {
        core.useEnergy(1);
        shield.energize(1);
    }
    
    public Shield getShield() { return shield; }
    public Hull getHull() { return hull; }
    public Energy getEnergy() { return core; }
}
