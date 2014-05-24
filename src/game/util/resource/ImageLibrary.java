package game.util.resource;

import org.newdawn.slick.Image;

public enum ImageLibrary {
    
    PLAYER_SHIP("player_ship.png",false);
    
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