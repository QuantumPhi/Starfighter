package game.ship;

import game.network.DataPacket;
import org.newdawn.slick.GameContainer;

public class EnemyShip extends Ship {
    
    private double ex,ey;
    
    @Override public void setX(double px) { x = px; ex = px; }
    @Override public void setY(double py) { y = py; ey = py; }
    
    /** 
     * @param id ID for the ship 
     * @param type Maximum speed of the ship
     **/
    public EnemyShip(long id, ShipType type) {
        super(id, type);
    }
    
    public EnemyShip(long id) {
        super(id);
    }
    
    /** @param p Data packet containing ship data **/
    public EnemyShip(DataPacket p) {
        super(p);
        p.update(this);
    }
    
    @Override
    public void update(GameContainer container, int delta) {
        ex += velocity.getSpeed()*Math.cos(velocity.getAngle()*Math.PI/180);
        ey -= velocity.getSpeed()*Math.sin(velocity.getAngle()*Math.PI/180);
        mask.setX(ex);
        mask.setY(ey);
    }
}
