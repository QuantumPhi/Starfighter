package game.util.resource;

import org.newdawn.slick.Image;

public enum ImageLibrary {
    
    HUMAN_STARFIGHTER("spr_human_starfighter.png");
    
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