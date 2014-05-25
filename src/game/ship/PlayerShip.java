package game.ship;

import game.ship.modules.projectiles.Projectile;
import game.ship.modules.weapons.LaserCannon;
import game.ship.modules.weapons.MissileLauncher;
import game.ship.modules.weapons.Weapon;
import game.util.Options;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Ellipse;

public class PlayerShip extends Ship {

    private Weapon[] weapons;
    private int currentWeapon;
    private Queue<Projectile> projectileQueue;
    private List<Projectile> projectiles;
    
    public PlayerShip(long id, ShipType type, List<Projectile> newProjectiles) {
        super(id,type);
        sprite = type.getSprite();
        projectileQueue = new ConcurrentLinkedQueue<>();
        projectiles = newProjectiles;
        initWeapons();
    }
    
    private void initWeapons() {
        weapons = new Weapon[2];
        weapons[0] = new LaserCannon();
        weapons[1] = new MissileLauncher();
    }
    
    @Override
    public void update(GameContainer container, int delta) {
        
        Input input = container.getInput();
        
        if(input.isKeyDown(Options.SPEED_UP.key()))
            velocity.accelerate(accel,delta, velocity.getAccelLimit());
        else if(input.isKeyDown(Options.SLOW_DOWN.key()))
            velocity.decelerate(accel,delta, velocity.getDecelLimit());
        else
            velocity.friction(friction, delta);
        if(input.isKeyDown(Options.TURN_RIGHT.key()))
            velocity.turnLeft(turn,delta);
        if(input.isKeyDown(Options.TURN_LEFT.key()))
            velocity.turnRight(turn,delta);
        if(input.isKeyPressed(Options.SWITCH_WEAPON.key())) {
            currentWeapon++;
            currentWeapon %= weapons.length;
        }
        if(input.isKeyPressed(Options.FIRE_WEAPON.key())) {
            Projectile p = weapons[currentWeapon].fire(this);
            if(p != null) {
                projectiles.add(p);
                projectileQueue.add(p);
            }
        }
        
        flare = Math.max(flare - delta, 0);
        
        weapons[currentWeapon].update(delta);
        
        x += velocity.getSpeed()*Math.cos(velocity.getAngle()*Math.PI/180);
        y -= velocity.getSpeed()*Math.sin(velocity.getAngle()*Math.PI/180);
    }

    @Override
    public void render(Graphics g) {
        Image img = sprite.copy();
        img.setCenterOfRotation(32,32);
        img.rotate(-(float)velocity.getAngle()+90);
        img.draw((float)x,(float)y,4.0f);
        if(flare == 2000) {
            g.draw(/*Image*/, x, y, /*Alpha from flare*/);
        }
    }
    
    public Queue<Projectile> getProjectileQueue() { return projectileQueue; }
}
