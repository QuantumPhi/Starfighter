package game.network;

import game.ship.EnemyShip;
import game.ship.modules.projectiles.Missile;
import java.util.List;
import java.util.TimerTask;

public class MissileTask extends TimerTask {
    
    private Missile missile;
    private List<EnemyShip> ships;
    
    public MissileTask(Missile m, List<EnemyShip> s) {
        missile = m;
        ships = s;
    }
    
    @Override
    public void run() {
        missile.serverUpdate(16,ships);
    }
}
