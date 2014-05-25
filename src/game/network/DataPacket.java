package game.network;

import game.ship.EnemyShip;
import game.ship.Ship;

public final class DataPacket {

    public static final int MAX_SIZE = 40;
    
    public static final int ID = 0;
    public static final int X = 8;
    public static final int Y = 16;
    public static final int DIR = 24;
    public static final int SPEED = 32;
    
    private byte[] data;
        
    public DataPacket(byte[] data) {
        this.data = data;
    }
    
    public DataPacket(Ship s) {
        data = new byte[MAX_SIZE];
        add(s.getID(),ID);
        add(s.getX(),X);
        add(s.getY(),Y);
        add(s.getAngle(),DIR);
        add(s.getSpeed(),SPEED);
    }
    
    public void add(double d, int pos) {
        long l = Double.doubleToLongBits(d);
        add(data,d,pos);
    }
    
    public double get(int pos) {
        long n = 0;
        for (int i=0;i<8;i++) {
            n <<= 8;
            n |= (int)data[i+pos] & 0xFF;
        }
        return Double.longBitsToDouble(n);
    }
    
    public static void add(byte[] arr, double d, int pos) {
        long l = Double.doubleToLongBits(d);
        for (int i=0;i<4;i++)
            arr[pos+i] = (byte) (l >> 48-16*i);
    }
    
    public byte[] getBytes() {
        return data;
    }
    
    public double getClient() {
        return get(ID);
    }
    
    public void update(EnemyShip s) {
        s.setX(get(X));
        s.setY(this.get(Y));
        s.setAngle(this.get(DIR));
        s.setSpeed(this.get(SPEED));
    }
}