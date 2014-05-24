package game;

import game.state.StatePlaying;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/** You probably shouldn't be modifying this class. */
public class Game extends StateBasedGame {
    
    public static final int STATE_MENU = 0;
    
    public static final int VIEW_SIZE_X = 640;
    public static final int VIEW_SIZE_Y = 512;
    
    public Game() {
        super("Journey of Fear");
    }
    
    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new Game());
            setupAGC(app);
        } catch (SlickException e) {
            System.out.println("Error initializing game: " + e);
        }
    }
    
    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        this.addState(new StatePlaying(STATE_MENU));;
    }
    
    /** Sets up the window. */
    private static void setupAGC(AppGameContainer app) throws SlickException {
        app.setDisplayMode(VIEW_SIZE_X,VIEW_SIZE_Y,false);
        app.setShowFPS(true);
        app.setAlwaysRender(true);
        app.setTargetFrameRate(60);
        app.setVSync(true);
        app.setForceExit(true);
        app.setVerbose(false);
        app.start();
    }
}