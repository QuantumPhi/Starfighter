package game.network;

import game.ship.EnemyShip;
import game.ship.modules.projectiles.Laser;
import game.ship.modules.projectiles.Projectile;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class LaserTask extends TimerTask {
    
    private Laser laser;
    private List<EnemyShip> ships;
    private List<Projectile> projectiles;
    private Timer timer;
    private int counter;
    private static int num = 0;
    
    public LaserTask(Laser l, List<EnemyShip> s, List<Projectile> p, Timer t) {
        laser = l;
        ships = s;
        projectiles = p;
        timer = t;
        counter = 0;
    }
    
    @Override
    public void run() {
        counter++;
        if (counter > 40) {
            projectiles.remove(laser);
            timer.cancel();
            timer.purge();
            return;
        }
        laser.serverUpdate(16);
        for(EnemyShip s : ships) {
            if(s.getID() == laser.getParentID())
                continue;
            if(laser.getMask().intersects(s.getMask(),laser.getX(),laser.getY(),laser.getAngle())) {
                if(!laser.hasHitTarget()) {
                    s.resolveHit(laser);
                    System.out.println("Laser hit #" + num++ + " registered");
                    laser.hitTarget();
                    projectiles.remove(laser);
                }
            }
        }
    }
}
