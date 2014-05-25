package game.ship.modules.weapons;

import game.ship.Ship;
import game.ship.modules.projectiles.Missile;

public class MissileLauncher extends Weapon {
    
    public MissileLauncher() {
        super(new Missile());
    }

    @Override
    public void fire(Ship s) {
        
    }   
}
