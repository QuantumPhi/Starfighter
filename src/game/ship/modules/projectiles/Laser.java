package game.ship.modules.projectiles;

import game.ship.Ship;
import game.ship.util.Vector;
import game.util.resource.ImageLibrary;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Laser extends Projectile {
    
    private static final double SPEED = 40;
    private int damage;
    
    public Laser() {
        super(ImageLibrary.LASER.getImage());
    }
    
    public Laser(Ship s, Vector v) {
        super(ImageLibrary.LASER.getImage());
        velocity = v;
        velocity.setAngle(s.getAngle());
    }
    
    @Override
    public Projectile createProjectile(Ship s) {
        return new Laser(s, new Vector(SPEED));
    }

    @Override
    public void update(int delta) {
        x += velocity.getSpeed() * Math.cos(velocity.getAngle());
        y -= velocity.getSpeed() * Math.sin(velocity.getAngle());
    }

    @Override
    public void render(Graphics g) {
        Image img = sprite.copy();
        img.setCenterOfRotation(32,32);
        img.rotate(-(float)velocity.getAngle()+90);
        img.draw((float)x,(float)y,4.0f);
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
