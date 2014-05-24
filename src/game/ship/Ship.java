package game.ship;

import game.ship.modules.Energy;
import game.ship.modules.Frame;
import game.ship.modules.Weapon;
import org.newdawn.slick.Image;

public class Ship {
    
    private long id;
    
    Image sprite;
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
}
