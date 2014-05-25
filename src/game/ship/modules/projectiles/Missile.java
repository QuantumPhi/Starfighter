package game.ship.modules.projectiles;

import game.ship.EnemyShip;
import game.ship.Ship;
import game.ship.util.Vector;
import game.util.resource.ImageLibrary;
import org.newdawn.slick.Graphics;

public class Missile extends Projectile {
    
    private static final double SPEED = 15;
    
    private EnemyShip target;
    private int damage;
    
    public Missile() {
        super(ImageLibrary.MISSILE.getImage());
    }
    
    public Missile(Ship s, Vector v) {
        this();
        velocity = v;
        velocity.setAngle(s.getAngle());
    }
    
    public Missile(Ship s, EnemyShip t) {
        this();
        velocity = new Vector(SPEED);
        velocity.setAngle(Math.toDegrees(Math.atan2(t.getY() - s.getY(), t.getX() - s.getX())));
        target = t;
    }

    public Projectile createProjectile(Ship s) {
        return new Missile(s, new Vector(SPEED));
    }
    
    public Projectile createProjectile(Ship s, EnemyShip target) {
        return new Missile(s, target);
    }
    
    @Override
    public void update(int delta) {
    }

    @Override
    public void render(Graphics g) {
    }
    
    @Override
    public int getShieldDamage() {
        return baseDamage;
    }

    @Override
    public int getHullDamage() {
        return baseDamage + damage;
    }
}
