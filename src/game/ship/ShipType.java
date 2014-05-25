package game.ship;

import game.ship.modules.Frame;
import game.util.resource.ImageLibrary;
import org.newdawn.slick.Image;

public enum ShipType {
    
    HUMAN_SHIP(ImageLibrary.HUMAN_STARFIGHTER, new Frame(100, 1, 20, 100, 1), 10, 2, 0.01, 16),
    ALIEN_SHIP(ImageLibrary.ALIEN_STARFIGHTER, new Frame(100, 1, 20, 100, 1), 10, 2, 0.01, 16);
    
    private ImageLibrary sprite;
    private Frame frame;
    private double maxSpeed;
    private double turn;
    private double accel;
    private double radius;
    
    ShipType(ImageLibrary s, Frame f, double ms, double t, double a, double r) {
        sprite = s;
        frame = f;
        maxSpeed = ms;
        turn = t;
        accel = a;
        radius = r;
    }
    
    public Image getSprite() { return sprite.getImage(); }
    public Frame getFrame() { return frame; }
    public double maxSpeed() { return maxSpeed; }
    public double getTurn() { return turn; }
    public double getAccel() { return accel; }
    public double getRadius() { return radius; }
}
