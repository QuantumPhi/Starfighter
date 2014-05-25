package game.state;

import game.Game;
import game.network.ClientNetworkHandler;
import game.ship.EnemyShip;
import game.ship.PlayerShip;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StatePlaying extends BasicGameState {
    
    public static final String ip = "127.0.0.1";
    public static final int port = 9999;
    
    private int id;
    private ClientNetworkHandler network;
    private PlayerShip player;
    private List<EnemyShip> enemies;
    private boolean ready = false;
    
    public void connected() { ready = true; }
    public void setPlayer(PlayerShip player) { this.player = player; }
    public StatePlaying(int id) {
        this.id = id;
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException { }
    
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        enemies = new ArrayList<EnemyShip>();
        network = new ClientNetworkHandler(ip,port,player,enemies,this);
        network.start();
    }
    
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        if (!ready) {
            g.drawString("Connecting...",Game.VIEW_SIZE_X/2-60,Game.VIEW_SIZE_Y/2-20);
            return;
        }
        System.out.println(player);
        player.render(g);
        for(EnemyShip e : enemies)
            e.render(g);
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (!ready) {
            return;
        }
        player.update(container, delta);
        for(EnemyShip e : enemies)
            e.update(container, delta);
    }
    
    @Override
    public int getID() {
        return id;
    }
}
