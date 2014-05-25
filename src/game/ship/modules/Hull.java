package game.ship.modules;

import game.ship.properties.Regenerable;

public class Hull implements Regenerable {
    
    private int maxInt;
    private int integrity;
    private int regen;
    
    /** *  
     * @param i Health of the hull
     * @param r Regeneration rate of the hull
     **/
    public Hull(int i, int r) {
        maxInt = r;
        integrity = i;
        regen = r;
    }
    
    public int integrity() { return integrity; }
    
    public void resolveHit(int damage) {
        integrity -= damage;
    }

    @Override
    public void regenerate(int delta) {
        
    }
}
