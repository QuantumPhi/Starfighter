package game.galaxy;

import game.Game;
import game.util.resource.ImageLibrary;
import org.newdawn.slick.Graphics;

public class Star {
    
    private final int x;
    private final int y;
    private final int depth;
    
    public Star(int nx, int ny, int nd) {
        x = nx;
        y = ny;
        depth = nd;
    }
    
    public void render(Graphics g, int cx, int cy) {
        
        double sx = x+depth*cx/15.0;
        double sy = y+depth*cy/15.0;
        
        if (cx-16<sx && cy-16<sy && cx+Game.VIEW_SIZE_X>sx && cy+Game.VIEW_SIZE_Y>sy)
            g.drawImage(ImageLibrary.STAR.getImage(),(float)sx,(float)sy);
    }
}
