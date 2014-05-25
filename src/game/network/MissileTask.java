package game.network;

import game.ship.EnemyShip;
import game.ship.modules.projectiles.Missile;
import game.ship.modules.projectiles.Projectile;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MissileTask extends TimerTask {
    
    private Missile missile;
    private List<EnemyShip> ships;
    private List<Projectile> projectiles;
    private Timer timer;
    
    public MissileTask(Missile m, List<EnemyShip> s, List<Projectile> p, Timer t) {
        missile = m;
        ships = s;
        projectiles = p;
        timer = t;
    }
    
    @Override
    public void run() {
        missile.serverUpdate(16,ships);
        timer.cancel();
        timer.purge();
    }
}
