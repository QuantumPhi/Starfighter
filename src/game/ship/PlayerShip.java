package game.ship;

import game.util.Options;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

public class PlayerShip extends Ship {

    @Override
    public void update(GameContainer container, int delta) {
        Input input = container.getInput();
        if(input.isKeyPressed(Options.SPEED_UP.key()))
            accelerate(delta);
        if(input.isKeyPressed(Options.SLOW_DOWN.key()))
            decelerate(delta);
    }

    @Override
    public void render(Graphics g) {
    }
}
