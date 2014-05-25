package game.ship.modules;

public class Frame {
    
    private Shield shield;
    private Hull hull;
    private Energy core;
    
    /**
     * @param is Health of the shields
     * @param rs Regeneration rate of the shields
     * @param ih Health of the hull
     * @param rh Regeneration rate of the hull
     * @param ee Energy of the core
     * @param re Regeneration rate of the core
     */
    public Frame(int is, int rs, int ih, int rh, int ee, int re) {
        shield = new Shield(is, rs);
        hull = new Hull(ih, rh);
        core = new Energy(ee, re);
    }
    
    public void resolveHit(int damage) {
        int dmg = shield.integrity() - damage < 0 ? 
                Math.abs(shield.integrity() - damage) : 0;
        shield.resolveHit(damage - dmg);
        hull.resolveHit(dmg);
    }
    
    public void regenShields() {
        core.useEnergy(1);
        shield.energize(1);
    }
    
    public Shield getShield() { return shield; }
    public Hull getHull() { return hull; }
    public Energy getEnergy() { return core; }
}
