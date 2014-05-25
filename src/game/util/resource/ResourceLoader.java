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
        image.setCenterOfRotation(8,8);
        image.setFilter(Image.FILTER_NEAREST);
        return image;
    }
}