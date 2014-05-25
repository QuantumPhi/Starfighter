package game.ship;

import game.util.Options;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class PlayerShip extends Ship {

    /** @param id ID for ship **/
    public PlayerShip(long id) {
        super(id);
    }
    
    @Override
    public void update(GameContainer container, int delta) {
        Input input = container.getInput();
        if(input.isKeyPressed(Options.SPEED_UP.key()))
            velocity.accelerate(accel, delta);
        if(input.isKeyPressed(Options.SLOW_DOWN.key()))
            velocity.decelerate(accel, delta);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sprite,(float)x,(float)y);
    }
}
