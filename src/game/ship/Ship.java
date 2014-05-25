package game.ship;

import game.network.DataPacket;
import game.ship.modules.Frame;
import game.ship.modules.projectiles.Projectile;
import game.ship.util.Vector;
import game.util.Circle;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Ship {
    
    protected long id;
    
    protected double x, y;
    
    protected Image sprite;
    protected Vector velocity;
    protected double turn;
    protected double accel;
    protected double friction;
    protected Circle mask;
    
    protected ShipType type;
    
    protected Frame ship;
    
    public ShipType getType() { return type; }
    
    /**
     * @param i ID for the ship 
     * @param maxSpeed Maximum speed of the ship
     **/
    private Ship(long i, double maxSpeed, double t, double a) {
        id = i;
        velocity = new Vector(maxSpeed);
        turn = t;
        accel = a;
        friction = accel * 0.2;
    }
    
    public Ship(long i) {
        id = i;
    }
    
    public Ship(long i, ShipType type) {
        this(i,type.maxSpeed(),type.getTurn(),type.getAccel());
        mask = new Circle(type);
        this.type = type;
    }
    
    public Ship(DataPacket p) {
        id = (long)p.getClient();
        type = ShipType.values()[p.getInt(DataPacket.TYPE)];
        velocity = new Vector(type.maxSpeed());
    }
    
    public void resolveHit(Projectile p) {
        ship.resolveHit(p);
    }
    
    public abstract void update(GameContainer container, int delta);
    public abstract void render(Graphics g);
    
    public long getID() { return id; }
    
    public Circle getMask() { return mask; }
    
    public double getX() { return x; }
    public double getY() { return y; }
    public double getSpeed() { return velocity.getSpeed(); }
    public double getAngle() { return velocity.getAngle(); }
    
    public void setX(double px) { x = px; }
    public void setY(double py) { y = py; }
    public void setSpeed(double speed) { velocity.setSpeed(speed); }
    public void setAngle(double angle) { velocity.setAngle(angle); }
    
    @Override
    public boolean equals(Object o) {
        if(o == null || !(o instanceof Ship))
            return false;
        return id == ((Ship)o).getID();
    }
}
