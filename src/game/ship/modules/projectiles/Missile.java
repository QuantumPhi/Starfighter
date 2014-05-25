package game.ship.modules.projectiles;

import game.ship.EnemyShip;
import game.ship.Ship;
import game.ship.util.Vector;
import game.util.resource.ImageLibrary;
import org.newdawn.slick.Graphics;

public class Missile extends Projectile {
    
    private static final double SPEED = 15;
    private static long missilesCreated = 0;
    
    private EnemyShip target;
    private int damage;
    
    public Missile() {
        super(ImageLibrary.MISSILE.getImage());
    }
    
    public Missile(long id, Ship s) {
        this();
        velocity = new Vector(SPEED);
        velocity.setAngle(s.getAngle());
    }
    
    public Missile(long id, Ship s, EnemyShip t) {
        this();
        velocity = new Vector(SPEED);
        velocity.setAngle(Math.toDegrees(Math.atan2(t.getY() - s.getY(), t.getX() - s.getX())));
        target = t;
    }
    
    @Override
    public Projectile createProjectile(Ship s) {
        Missile m = new Missile(missilesCreated, s);
        m.setX(s.getX());
        m.setY(s.getY());
        missilesCreated++;
        return m;
    }
    
    public Projectile createProjectile(Ship s, EnemyShip target) {
        Missile m = new Missile(missilesCreated, s, target);
        m.setX(s.getX());
        m.setY(s.getY());
        missilesCreated++;
        return m;
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
