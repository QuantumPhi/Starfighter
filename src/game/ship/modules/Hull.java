package game.ship.modules;

import game.ship.properties.Regenerable;

public class Hull {
    
    private int maxInt;
    private int integrity;
    
    /**  
     * @param i Health of the hull
     **/
    public Hull(int i) {
        maxInt = i;
        integrity = i;
    }
    
    public int integrity() { return integrity; }
    
    public void resolveHit(int damage) {
        integrity -= damage;
    }
}
