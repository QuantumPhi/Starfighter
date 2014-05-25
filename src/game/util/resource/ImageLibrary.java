package game.util.resource;

import org.newdawn.slick.Image;

public enum ImageLibrary {
    
    HUMAN_STARFIGHTER("human_starfighter.png"),
    ALIEN_STARFIGHTER("alien_starfighter.png"),
        
    LASER("laser.png"),
    MISSILE("missile.png");
    
    private String filepath;
    private Image image;
    
    ImageLibrary(String filepath) {
        this.filepath = filepath;
    }
    
    public Image getImage() {
        if (image == null)
            image = ResourceLoader.initializeImage(filepath);
        return image.copy();
    }
}