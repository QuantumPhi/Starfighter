package game.ship.modules.weapons;

import game.ship.Ship;
import game.ship.modules.projectiles.Laser;
import game.util.resource.ImageLibrary;

public class LaserCannon extends Weapon {
    
    public LaserCannon() {
        super(new Laser(ImageLibrary.LASER.getImage()));
    }

    @Override
    public void update() {
    }

    @Override
    public void render() {
    }

    @Override
    public void fire(Ship s) {
        
    }
}
