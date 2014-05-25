package game.ship.modules.weapons;

import game.ship.EnemyShip;
import game.ship.Ship;
import game.ship.modules.projectiles.Missile;

public class MissileLauncher extends Weapon {
    
    public MissileLauncher() {
        super(new Missile());
        delay = 1000;
    }
    
    public void fire(Ship s, EnemyShip t) { }
}
