package game.ship;

import game.ship.modules.projectiles.Laser;
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
import org.newdawn.slick.Input;

public class PlayerShip extends Ship {

    private Weapon[] weapons;
    private int currentWeapon;
    private Queue<Projectile> projectileQueue;
    private List<Projectile> projectiles;
    private List<EnemyShip> enemies;
    
    public PlayerShip(long id, ShipType type, List<Projectile> newProjectiles, List<EnemyShip> newEnemies) {
        super(id,type);
        sprite = type.getSprite();
        projectileQueue = new ConcurrentLinkedQueue<>();
        projectiles = newProjectiles;
        enemies = newEnemies;
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
        
        if (flare>0)
            flare--;
        
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
        
        if (input.isKeyPressed(Input.KEY_5))
            resolveHit(new Laser());
        
        if(input.isKeyPressed(Options.FIRE_WEAPON.key())) {
            Projectile p = weapons[currentWeapon].fire(this);
            if(p != null) {
                projectiles.add(p);
                projectileQueue.add(p);
            }
        }
        
        weapons[currentWeapon].update(delta);
        
        x += velocity.getSpeed()*Math.cos(velocity.getAngle()*Math.PI/180);
        y -= velocity.getSpeed()*Math.sin(velocity.getAngle()*Math.PI/180);
        
        mask.setX(x);
        mask.setY(y);
    }
    
    @Override
    public void render(Graphics g) {
        super.render(g);
        /*
        EnemyShip closest = null;
        double distance = 9001;
        
        for (EnemyShip e : enemies) {
            if (e.getID() == id)
                continue;
            double dist = Math.hypot(e.x-x,e.y-y);
            if (dist < distance) {
                closest = e;
                distance = dist;
            }
        }
        
        if (closest == null)
            return;
        
        int dir = (int)Math.round(MathHelper.dirTo((float)x,(float)y,(float)closest.x,(float)closest.y));
        g.drawLine((float)(32*Math.cos(Math.toRadians(dir))+x),(float)(32*Math.sin(Math.toRadians(dir))+y),
                (float)(64*Math.cos(Math.toRadians(dir))+x),(float)(64*Math.sin(Math.toRadians(dir))+y));
        */
    }
    
    public Queue<Projectile> getProjectileQueue() { return projectileQueue; }
}
