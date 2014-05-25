package game.ship.modules.weapons;

import game.ship.Ship;
import game.ship.modules.projectiles.Laser;

public class LaserCannon extends Weapon {
    
    public LaserCannon() {
        super(new Laser());
    }

    @Override
    public void fire(Ship s) {
        
    }
}
