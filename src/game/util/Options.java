package game.util;

import org.newdawn.slick.Input;

public enum Options {
    
    SPEED_UP(Input.KEY_W),
    SLOW_DOWN(Input.KEY_S),
    TURN_RIGHT(Input.KEY_D),
    TURN_LEFT(Input.KEY_A),
    
    SHOOT_LASER(Input.KEY_S),
    FIRE_WEAPON(Input.KEY_SPACE),
    SWITCH_WEAPON(Input.KEY_E);
    
    private int key;
    
    public int key() { return key; }
    
    Options(int k) {
        key = k;
    }
}
