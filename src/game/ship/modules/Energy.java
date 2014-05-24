package game.ship.modules;

public class Energy {
    
    private int maxEn;
    private int energy;
    private int regen;
    
    /**
     * @param e Energy of the ship
     * @param r Regeneration rate of energy
     */
    public Energy(int e, int r) {
        maxEn = e;
        energy = e;
        regen = r;
    }
    
    public boolean useEnergy(int quantity) {
        if(energy - quantity >= 0) {
            energy -= quantity;
            return true;
        } else
            return false;
    }
    
    public void regenerate(int delta) {
        
    }
}
