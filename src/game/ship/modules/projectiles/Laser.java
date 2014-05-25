package game.ship.modules.projectiles;

import game.ship.modules.projectiles.Projectile;
import game.util.resource.ImageLibrary;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Laser extends Projectile {
    
    protected int damage;
    
    public Laser(Image image) {
        super(image);
    }
    
    @Override
    public Projectile createProjectile() {
        return new Laser(ImageLibrary.LASER.getImage());
    }

    @Override
    public int getShieldDamage() {
        return baseDamage + damage;
    }
    
    @Override
    public int getHullDamage() {
        return baseDamage;
    }

    @Override
    public void update(int delta) {
    }

    @Override
    public void render(Graphics g) {
    }
}
