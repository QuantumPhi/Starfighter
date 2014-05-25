package game.state;

import game.Game;
import game.galaxy.Star;
import game.network.ClientNetworkHandler;
import game.ship.EnemyShip;
import game.ship.PlayerShip;
import game.ship.modules.projectiles.Projectile;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StatePlaying extends BasicGameState {
    
    public static final String ip = "127.0.0.1";
    public static final int port = 9999;
    
    public static final int WORLD_SIZE_X = Game.VIEW_SIZE_X*8;
    public static final int WORLD_SIZE_Y = Game.VIEW_SIZE_Y*8;
    
    private int id;
    private ClientNetworkHandler network;
    private PlayerShip player;
    private List<EnemyShip> enemies;
    private List<Projectile> projectiles;
    private boolean ready = false;
    
    private List<Star> stars;
    
    private int camX;
    private int camY;
    
    public void connected() { ready = true; }
    public void setPlayer(PlayerShip player) { this.player = player; }
    
    public StatePlaying(int id) {
        this.id = id;
        this.camX = 0;
        this.camY = 0;
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException { }
    
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        enemies = new CopyOnWriteArrayList<EnemyShip>();
        projectiles = new CopyOnWriteArrayList<Projectile>();
        network = new ClientNetworkHandler(ip,port,player,enemies,projectiles,this);
        network.start();
        stars = new ArrayList<Star>();
        
        for (int i=0;i<4000;i++) {
            stars.add(new Star((int)(Math.random()*WORLD_SIZE_X),
                    (int)(Math.random()*WORLD_SIZE_Y),(int)(5+Math.random()*5)));
        }
    }
    
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        if (!ready) {
            g.drawString("Connecting...",Game.VIEW_SIZE_X/2-60,Game.VIEW_SIZE_Y/2-20);
            return;
        }
        
        camX = (int)(player.getX()-Game.VIEW_SIZE_X/2);
        camY = (int)(player.getY()-Game.VIEW_SIZE_Y/2);
        
        g.translate(-(float)camX,-(float)camY);
        
        for (Star s : stars) {
            s.render(g,camX,camY);
        }
        
        for(Projectile p : projectiles)
            p.render(g);
        
        player.render(g);
        
        for(EnemyShip e : enemies)
            e.render(g);
        
        g.drawString("Ping: " + network.getPing(),camX+Game.VIEW_SIZE_X-100,camY+10);
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (!ready) {
            return;
        }
        player.update(container,delta);
        
        for(EnemyShip e : enemies)
            e.update(container,delta);
        for(Projectile p : projectiles)
            p.update(delta);
    }
    
    @Override
    public int getID() {
        return id;
    }
}
