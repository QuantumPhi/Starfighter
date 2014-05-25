package game.util.resource;

import org.newdawn.slick.Image;

public enum ImageLibrary {
    
    HUMAN_STARFIGHTER("spr_human_starfighter.png",false);
    
    private String filepath;
    private Image image;
    private boolean bg;
    
    ImageLibrary(String filepath, boolean bg) {
        this.filepath = filepath;
        this.bg = bg;
    }
    
    public Image getImage() {
        if (image == null)
            image = ResourceLoader.initializeImage(filepath);
        return image.copy();
    }
}