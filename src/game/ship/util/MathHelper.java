package game.ship.util;

public class MathHelper {
    
    public static double getDir(double dirTo, double dir) {
        double cw;
        double ccw;
        
        if (dirTo > dir) {
            cw = dirTo-dir;
            ccw = (360+dir-dirTo)%360;
        } else {
            cw = (360+dirTo-dir)%360;
            ccw = dir-dirTo;
        }
        
        return ccw>cw ? 1.0 : -1.0;
    }
    
    public static double dirTo(double tx, double ty, double ox, double oy) {
        return Math.toDegrees(Math.atan2(oy-ty,tx-ox));
    }
}
