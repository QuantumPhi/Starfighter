package game.ship.modules.projectiles;

import game.network.DataPacket;
import game.ship.Ship;
import game.ship.util.Vector;
import game.util.Point;
import game.util.resource.ImageLibrary;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Laser extends Projectile {
    
    private static final double SPEED = 20;
    private static long lasersCreated = 0;
    private int damage;
        
    public Laser() {
        super(ImageLibrary.LASER.getImage());
        mask = new Point(7, 7);
    }
    
    public Laser(DataPacket p) {
        super(p);
        velocity = new Vector(SPEED);
        p.update(this);
        mask = new Point(7, 7);
    }
    
    public Laser(long id, Ship s) {
        super(id, s.getID(), ImageLibrary.LASER.getImage());
        velocity = new Vector(SPEED);
        velocity.setSpeed(SPEED);
        velocity.setAngle(s.getAngle());
        mask = new Point(7, 7);
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
        if (sprite == null)
            sprite = ImageLibrary.LASER.getImage();
        Image img = sprite.copy();
        img.setCenterOfRotation(32,32);
        img.rotate(-(float)velocity.getAngle()+90);
        img.draw((float)ex,(float)ey,4.0f);
    }
    
    @Override
    public int getShieldDamage() {
        return baseDamage + damage;
    }
    
    @Override
    public int getHullDamage() {
        return baseDamage;
    }
    
    @Override public Point getMask() { return mask; }
}
