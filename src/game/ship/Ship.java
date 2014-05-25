package game.ship;

import game.ship.modules.Energy;
import game.ship.modules.Frame;
import game.ship.modules.Weapon;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public abstract class Ship {
    
    private long id;
    
    Image sprite;
    private double curSpd;
    private double speed;
    private double accel;
    private double turn;
    private double angle;
    private double attack;
    
    private Frame ship;
    private Weapon[] weapons;
    private Energy core;
    
    public void resolveHit(int damage) {
        ship.resolveHit(damage);
    }
    
    public abstract void update(GameContainer container, int delta);
    public abstract void render(Graphics g);
    
    public void accelerate(int delta) {
        curSpd = curSpd + delta * accel <= speed ? 
                curSpd + delta * accel : speed;
    }
    
    public void decelerate(int delta) {
        curSpd = curSpd - delta * accel >= -speed / 2 ? 
                curSpd - delta * accel : -speed / 2; 
    }
    
    public void turnRight(int delta) {
        angle = angle + delta * turn < 360 ?
                angle + delta * turn : 0;
    }
    
    public void turnLeft(int delta) {
        angle = angle - delta * turn >= 0 ?
                angle - delta * turn : 360 + (angle - delta * turn);
    }
    
    public void setID(long i) { id = i; }
    
    public long getID() { return id; }
}
