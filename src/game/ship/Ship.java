package game.ship;

import game.network.DataPacket;
import game.ship.modules.Frame;
import game.ship.modules.projectiles.Projectile;
import game.ship.modules.weapons.LaserCannon;
import game.ship.modules.weapons.MissileLauncher;
import game.ship.modules.weapons.Weapon;
import game.ship.util.Vector;
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
    
    protected ShipType type;
    
    protected Frame ship;
    protected Weapon[] weapons;
    protected int currentWeapon;
    
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
        friction = accel / 0.25;
        initWeapons();
    }
    
    public Ship(long i) {
        id = i;
    }
    
    public Ship(long i, ShipType type) {
        this(i,type.maxSpeed(),type.getTurn(),type.getAccel());
        this.type = type;
    }
    
    public Ship(DataPacket p) {
        id = (long)p.getClient();
        type = ShipType.values()[p.getInt(DataPacket.TYPE)];
        velocity = new Vector(type.maxSpeed());
    }
    
    private void initWeapons() {
        weapons = new Weapon[2];
        weapons[0] = new LaserCannon();
        weapons[1] = new MissileLauncher();
    }
    
    public void resolveHit(Projectile p) {
        ship.resolveHit(p);
    }
    
    public abstract void update(GameContainer container, int delta);
    public abstract void render(Graphics g);
    
    public long getID() { return id; }
    
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
