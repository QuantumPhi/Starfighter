package game.ship.modules;

public class Hull {
    
    public int maxInt;
    public int integrity;
    
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
