package game.ship.modules.projectiles;

import game.network.DataPacket;
import game.ship.Ship;
import game.ship.util.Vector;
import game.util.Rectangle;
import game.util.resource.ImageLibrary;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Laser extends Projectile {
    
    private static final double SPEED = 30;
    private static long lasersCreated = 0;
    private int damage;
    private Rectangle mask = new Rectangle(7, 0, 8, 15);
    
    public Laser() {
        super(ImageLibrary.LASER.getImage());
    }
    
    public Laser(DataPacket p) {
        super(p);
        velocity = new Vector(SPEED);
        p.update(this);
    }
    
    public Laser(long id, Ship s) {
        super(id, s.getID(), ImageLibrary.LASER.getImage());
        velocity = new Vector(SPEED);
        velocity.setSpeed(SPEED);
        velocity.setAngle(s.getAngle());
    }
    
    @Override
    public Projectile createProjectile(Ship s) {
        Laser l = new Laser(lasersCreated, s);
        l.setX(s.getX());
        l.setY(s.getY());
        lasersCreated++;
        return l;
    }

    @Override
    public void update(int delta) {
        ex += velocity.getSpeed() * Math.cos(Math.toRadians(velocity.getAngle()));
        ey -= velocity.getSpeed() * Math.sin(Math.toRadians(velocity.getAngle()));
    }
    
    public void serverUpdate(int delta) {
        setX(x+velocity.getSpeed() * Math.cos(Math.toRadians(velocity.getAngle())));
        setY(y+velocity.getSpeed() * Math.sin(Math.toRadians(velocity.getAngle())));
    }

    @Override
    public void render(Graphics g) {
        Image img = sprite.copy();
        img.setCenterOfRotation(32,32);
        img.rotate(-(float)velocity.getAngle()+90);
        img.draw((float)ex,(float)ey,4.0f);
    }
    
    public Rectangle getMask() { return mask; }
    
    @Override
    public int getShieldDamage() {
        return baseDamage + damage;
    }
    
    @Override
    public int getHullDamage() {
        return baseDamage;
    }
}
