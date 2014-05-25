package game.network;

import game.ship.modules.projectiles.Laser;
import java.util.TimerTask;

public class LaserTask extends TimerTask {
    
    private Laser laser;
    
    public LaserTask(Laser l) {
        laser = l;
    }
    
    @Override
    public void run() {
        laser.serverUpdate(16);
    }
}
