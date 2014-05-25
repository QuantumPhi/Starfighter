package game.network;

import game.ship.EnemyShip;
import game.ship.Ship;
import game.ship.modules.projectiles.Laser;
import game.ship.modules.projectiles.Projectile;
import java.nio.ByteBuffer;

public final class DataPacket {

    public static final int MAX_SIZE = 52;
    
    public static final int ID = 0;
    public static final int X = 8;
    public static final int Y = 16;
    public static final int DIR = 24;
    public static final int SPEED = 32;
    public static final int TYPE = 40;
    public static final int PARENT = 44;
    
    private byte[] data;
        
    public DataPacket(byte[] data) {
        this.data = data;
    }
    
    public DataPacket(Projectile p) {
        data = new byte[MAX_SIZE];
        addDouble(p.getID(),ID);
        addDouble(p.getX(),X);
        addDouble(p.getY(),Y);
        addDouble(p.getAngle(),DIR);
        addDouble(p.getSpeed(),SPEED);
        addInt(p instanceof Laser ? -2 : -1,TYPE);
        addDouble(p.getParentID(),PARENT);
    }
    
    public DataPacket(Ship s) {
        data = new byte[MAX_SIZE];
        addDouble(s.getID(),ID);
        addDouble(s.getX(),X);
        addDouble(s.getY(),Y);
        addDouble(s.getAngle(),DIR);
        addDouble(s.getSpeed(),SPEED);
        addInt(s.getType().ordinal(),TYPE);
    }
    
    public final void add(int i, int pos) {
        data[pos] = (byte) (i >> 24);
        data[pos+1] = (byte) (i >> 16);
        data[pos+2] = (byte) (i >> 8);
        data[pos+3] = (byte) (i);
    }
    
    public void addDouble(double d, int pos) {
        addDouble(data,d,pos);
    }
    
    public void addInt(int i, int pos) {
        data[pos] = (byte) (i >> 24);
        data[pos+1] = (byte) (i >> 16);
        data[pos+2] = (byte) (i >> 8);
        data[pos+3] = (byte) (i);
    }
    
    public double getDouble(int pos) {
        return ByteBuffer.wrap(data,pos,8).getDouble();
    }
    
    public int getInt(int pos) {
        int n = 0;
        for (int i=0;i<4;i++) {
            n <<= 8;
            n |= (int)data[i+pos] & 0xFF;
        }
        return n;
    }
    
    public static void addDouble(byte[] arr, double d, int pos) {
        ByteBuffer.wrap(arr,pos,8).putDouble(d);
    }
    
    public byte[] getBytes() {
        return data;
    }
    
    public double getClient() {
        return getDouble(ID);
    }
    
    public void update(EnemyShip s) {
        s.setX(getDouble(X));
        s.setY(getDouble(Y));
        s.setAngle(getDouble(DIR));
        s.setSpeed(getDouble(SPEED));
    }
    
    public void update(Projectile p) {
        p.setX(getDouble(X));
        p.setY(getDouble(Y));
        p.setAngle(getDouble(DIR));
        p.setSpeed(getDouble(SPEED));
    }
}