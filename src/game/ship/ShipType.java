package game.ship;

import game.ship.modules.Frame;
import game.util.resource.ResourceLoader;
import org.newdawn.slick.Image;

public enum ShipType {
    
    HUMAN_SHIP(ResourceLoader.initializeImage("human_starfighter"));
    
    ShipType(Image sprite, Frame frame, double maxSpeed, double turn, double accel, double attack) {
        
    }
}
