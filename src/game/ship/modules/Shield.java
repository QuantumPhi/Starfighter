package game.ship.modules;

import game.ship.properties.Regenerable;

public class Shield implements Regenerable {
    
    private int maxInt;
    private int integrity;
    private int regen;
    private int recentDamage = 500;
    
    /** 
     * @param i Health of the shield
     * @param r Regeneration rate of the shield
     **/
    public Shield(int i, int r) {
        maxInt = i;
        integrity = i;
        regen = r;
    }
    
    public void resolveHit(int damage) {
        integrity -= damage;
    }
    
    @Override
    public void regenerate(int delta) {
        
    }
    
    public int integrity() { return integrity; }
}
