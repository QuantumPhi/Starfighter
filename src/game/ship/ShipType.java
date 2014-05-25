package game.ship;

import game.ship.modules.Frame;
import game.util.resource.ImageLibrary;
import org.newdawn.slick.Image;

public enum ShipType {
    
    HUMAN_SHIP(ImageLibrary.HUMAN_STARFIGHTER.getImage(), new Frame(100, 1, 20, 100, 1), 10, 2, 0.01);
    
    private Image sprite;
    private Frame frame;
    private double maxSpeed;
    private double turn;
    private double accel;
    
    ShipType(Image s, Frame f, double ms, double t, double a) {
        sprite = s;
        frame = f;
        maxSpeed = ms;
        turn = t;
        accel = a;
    }
    
    public Image getSprite() { return sprite; }
    public Frame getFrame() { return frame; }
    public double maxSpeed() { return maxSpeed; }
    public double getTurn() { return turn; }
    public double getAccel() { return accel; }
}
