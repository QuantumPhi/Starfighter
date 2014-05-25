package game.network;

import game.ship.EnemyShip;
import game.ship.modules.projectiles.Laser;
import java.util.List;
import java.util.TimerTask;

public class LaserTask extends TimerTask {
    
    private Laser laser;
    private List<EnemyShip> ships;
    
    public LaserTask(Laser l, List<EnemyShip> s) {
        laser = l;
        ships = s;
    }
    
    @Override
    public void run() {
        laser.serverUpdate(16);
        for(EnemyShip s : ships) {
            if(laser.getMask().intersects(s.getMask())) {
                s.resolveHit(laser);
                return;
            }
        }
    }
}
