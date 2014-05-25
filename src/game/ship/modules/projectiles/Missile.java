package game.ship.modules.projectiles;

import game.network.DataPacket;
import game.ship.EnemyShip;
import game.ship.Ship;
import game.ship.util.MathHelper;
import game.ship.util.Vector;
import game.util.Point;
import game.util.Rectangle;
import game.util.resource.ImageLibrary;
import java.util.List;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Missile extends Projectile {
    
    private static final double SPEED = 15;
    private static long missilesCreated = 0;
    
    private EnemyShip target;
    private int damage;
    private double turningCircle = 0.01;
    
    public Missile() {
        super(ImageLibrary.MISSILE.getImage());
        mask = new Point(7, 7);
    }
    
    public Missile(DataPacket p) {
        super(p);
        velocity = new Vector(SPEED);
        p.update(this);
        mask = new Point(7, 7);
    }
    
    public Missile(long id, Ship s) {
        this();
        velocity = new Vector(SPEED);
        velocity.setSpeed(SPEED);
        velocity.setAngle(s.getAngle());
        mask = new Point(7, 7);
    }
    
    public Missile(long id, Ship s, EnemyShip t) {
        this();
        velocity = new Vector(SPEED);
        velocity.setSpeed(SPEED);
        velocity.setAngle(Math.toDegrees(Math.atan2(t.getY() - s.getY(), t.getX() - s.getX())));
        target = t;
        mask = new Point(7, 7);
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
        ex += velocity.getSpeed() * Math.cos(Math.toRadians(velocity.getAngle()));
        ey -= velocity.getSpeed() * Math.sin(Math.toRadians(velocity.getAngle()));
    }
    
    public void serverUpdate(int delta, List<EnemyShip> ships) {
        if (target != null) {
            double dirTo = MathHelper.dirTo(x,y,target.getX(),target.getY())+180;
            if (Math.abs(dirTo-velocity.getAngle()) > turningCircle)
                velocity.turnRight(turningCircle*MathHelper.getDir(dirTo,velocity.getAngle()),delta);
            setX(x+velocity.getSpeed() * Math.cos(Math.toRadians(velocity.getAngle())));
            setY(y+velocity.getSpeed() * Math.sin(Math.toRadians(velocity.getAngle())));
            return;
        }
        
        EnemyShip bestShip = null;
        double bestAngle = 9001;
        
        for (EnemyShip s : ships) {
            if (s.getID() == getParentID())
                continue;
             double dirTo = MathHelper.dirTo(x,y,s.getX(),s.getY());
             
             if (Math.abs(dirTo) < Math.abs(bestAngle)) {
                 bestAngle = dirTo;
                 bestShip = s;
             }
        }
        
        target = bestShip;
        
        if (target == null) {
            setX(x+velocity.getSpeed() * Math.cos(Math.toRadians(velocity.getAngle())));
            setY(y+velocity.getSpeed() * Math.sin(Math.toRadians(velocity.getAngle())));
        }
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
        return baseDamage;
    }

    @Override
    public int getHullDamage() {
        return baseDamage + damage;
    }
    
    public Point getMask() { return mask; }
}
