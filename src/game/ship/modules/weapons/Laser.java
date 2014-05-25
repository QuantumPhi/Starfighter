package game.ship.modules.weapons;

import org.newdawn.slick.Image;

public class Laser extends Projectile {
    
    protected int damage;
    
    public Laser(Image image) {
        super(image);
    }

    @Override
    public int getShieldDamage() {
        return baseDamage + damage;
    }
    
    @Override
    public int getHullDamage() {
        return baseDamage;
    }
}
