package game.ship.modules.weapons;

import game.ship.modules.projectiles.Laser;

public class LaserCannon extends Weapon {
    
    public LaserCannon() {
        super(new Laser());
        delay = 500;
    }
}
