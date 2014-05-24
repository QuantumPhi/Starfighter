package game.util.resource;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ResourceLoader {
    
    public static Image initializeImage(String filepath) {
        Image image;
        try {
            image = new Image("assets/" + filepath);
        } catch (SlickException e) {
            throw new RuntimeException("Missing asset: " + filepath);
        }
        return image;
    }
}