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
    private double attack;
    
    private Frame ship;
    private Weapon[] weapons;
    private Energy core;
    
    public void resolveHit(int damage) {
        ship.resolveHit(damage);
    }
    
    public abstract void update(GameContainer container, int delta);
    public abstract void render(Graphics g);
    
    public void setID(long i) { id = i; }
    
    public long getID() { return id; }
}
