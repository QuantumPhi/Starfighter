package game.ship;

import game.network.DataPacket;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class EnemyShip extends Ship {
    
    private int estX, estY;

    /** @param id ID for ship **/
    public EnemyShip(long id) {
        super(id);
    }
    
    /** @param pkt Data packet containing ship data **/
    public EnemyShip(DataPacket pkt) {
        this((long)pkt.get(DataPacket.ID));
        pkt.update(this);
    }
    
    @Override
    public void update(GameContainer container, int delta) {
        
    }

    @Override
    public void render(Graphics g) {
    }
}
