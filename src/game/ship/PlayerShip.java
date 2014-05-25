package game.ship;

import game.util.Options;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public class PlayerShip extends Ship {

    /** 
     * @param id ID for the ship 
     * @param type Type of the ship
     **/
    public PlayerShip(long id, ShipType type) {
        super(id,type);
        sprite = type.getSprite();
    }
    
    @Override
    public void update(GameContainer container, int delta) {
        
        Input input = container.getInput();
        
        if(input.isKeyDown(Options.SPEED_UP.key())) {
            velocity.accelerate(accel,delta);
        } if(input.isKeyDown(Options.SLOW_DOWN.key()))
            velocity.decelerate(accel,delta);
        if(input.isKeyDown(Options.TURN_RIGHT.key()))
            velocity.turnLeft(turn,delta);
        if(input.isKeyDown(Options.TURN_LEFT.key()))
            velocity.turnRight(turn,delta);
        
        x += velocity.getSpeed()*Math.cos(velocity.getAngle()*Math.PI/180);
        y -= velocity.getSpeed()*Math.sin(velocity.getAngle()*Math.PI/180);
    }

    @Override
    public void render(Graphics g) {
        Image img = sprite.copy();
        img.setCenterOfRotation(32,32);
        img.rotate(-(float)velocity.getAngle()+90);
        img.draw((float)x,(float)y,4.0f);
    }
}
