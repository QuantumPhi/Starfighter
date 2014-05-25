package game.ship.modules.weapons;

public class Laser extends Projectile {
    
    protected int damage;

    @Override
    public int getShieldDamage() {
        return baseDamage + damage;
    }
    
    @Override
    public int getHullDamage() {
        return baseDamage;
    }
}
