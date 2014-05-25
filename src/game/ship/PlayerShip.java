package game.ship;

import game.util.Options;
import game.util.resource.ImageLibrary;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class PlayerShip extends Ship {

    /** 
     * @param id ID for the ship 
     * @param maxSpeed Maximum speed of the ship
     **/
    public PlayerShip(long id, double maxSpeed) {
        super(id, maxSpeed);
        sprite = ImageLibrary.HUMAN_STARFIGHTER.getImage();
    }
    
    @Override
    public void update(GameContainer container, int delta) {
        
        System.out.println(x + " " + y + " " + velocity.getSpeed());
        
        Input input = container.getInput();
        
        if(input.isKeyDown(Options.SPEED_UP.key()))
            velocity.accelerate(accel,delta);
        if(input.isKeyDown(Options.SLOW_DOWN.key()))
            velocity.decelerate(accel,delta);
        if(input.isKeyDown(Options.TURN_RIGHT.key()))
            velocity.turnRight(15,delta);
        if(input.isKeyDown(Options.TURN_LEFT.key()))
            velocity.turnLeft(15,delta);
        
        x += velocity.getSpeed()*Math.cos(velocity.getAngle()*Math.PI/180);
        y -= velocity.getSpeed()*Math.sin(velocity.getAngle()*Math.PI/180);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sprite,(float)x,(float)y);
    }
}
