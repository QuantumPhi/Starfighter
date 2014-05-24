package game.ship.modules;

public class Hull {
    
    private int maxInt;
    private int integrity;
    private int regen;
    
    /** @param i Health of the hull **/
    public Hull(int i, int r) {
        maxInt = r;
        integrity = i;
        regen = r;
    }
    
    public int integrity() { return integrity; }
    
    public void resolveHit(int damage) {
        integrity -= damage;
    }
}
