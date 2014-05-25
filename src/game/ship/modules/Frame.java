package game.ship.modules;

import game.ship.modules.weapons.Projectile;

public class Frame {
    
    private Shield shield;
    private Hull hull;
    private Energy core;
    
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
        int dmgS = shield.integrity() - p.getShieldDamage();
        int dmgH = dmgS < 0 ? (int)((p.getShieldDamage() + dmgS) / (double)(p.getShieldDamage()) / p.getHullDamage()) : 0;
        shield.resolveHit(dmgS < 0 ? shield.integrity() : dmgS);
        hull.resolveHit(dmgH);
    }
    
    public void regenShields() {
        core.useEnergy(1);
        shield.energize(1);
    }
    
    public Shield getShield() { return shield; }
    public Hull getHull() { return hull; }
    public Energy getEnergy() { return core; }
}
