package game.ship.modules;

public class Frame {
    
    private Shield shield;
    private Hull hull;
    
    /**
     * @param is Health of the shields
     * @param rs Regeneration rate of the shields
     * @param ih Health of the hull
     * @param rh Regeneration rate of the hull
     */
    public Frame(int is, int rs, int ih, int rh) {
        shield = new Shield(is, rs);
        hull = new Hull(ih, rh);
    }
    
    public void resolveHit(int damage) {
        int dmg = shield.integrity() - damage < 0 ? 
                Math.abs(shield.integrity() - damage) : 0;
        shield.resolveHit(damage - dmg);
        hull.resolveHit(dmg);
    }
}
