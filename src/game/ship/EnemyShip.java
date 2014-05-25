package game.ship;

import game.network.DataPacket;
import game.util.resource.ImageLibrary;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

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
    
    /** @param pkt Data packet containing ship data **/
    public EnemyShip(DataPacket pkt) {
        super(pkt);
        pkt.update(this);
    }
    
    @Override
    public void update(GameContainer container, int delta) {
        ex += velocity.getSpeed()*Math.cos(velocity.getAngle()*Math.PI/180);
        ey -= velocity.getSpeed()*Math.sin(velocity.getAngle()*Math.PI/180);
    }
    
    @Override
    public void render(Graphics g) {
        sprite = ImageLibrary.HUMAN_STARFIGHTER.getImage();
        g.drawImage(sprite,(float)ex,(float)ey);
    }
}
