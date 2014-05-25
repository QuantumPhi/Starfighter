package game.state;

import game.network.ClientNetworkHandler;
import game.ship.EnemyShip;
import game.ship.PlayerShip;
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
    
    public StatePlaying(int id) {
        this.id = id;
    }
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException { }
    
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        network = new ClientNetworkHandler(ip,port,player,enemies);
    }
    
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        player.render(g);
        for(EnemyShip e : enemies)
            e.render(g);
    }
    
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        player.update(container, delta);
        for(EnemyShip e : enemies)
            e.update(container, delta);
    }
    
    @Override
    public int getID() {
        return id;
    }
}
