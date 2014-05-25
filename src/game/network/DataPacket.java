package game.network;

public final class DataPacket {

    public static final int MAX_SIZE = 32;
    
    public static final int ID = 0;
    public static final int X = 8;
    public static final int Y = 16;
    public static final int DIR = 24;
    
    private byte[] data;
        
    public DataPacket(byte[] data) {
        this.data = data;
    }
    
    public DataPacket(Ship s) {
        data = new byte[MAX_SIZE];
        /*
        add(e.id,ID);
        add(e.x,X);
        add(e.y,Y);
        add(e.dir,DIR);
        */
    }
    
    public void add(double d, int pos) {
        long l = Double.doubleToLongBits(d);
        data[pos] = (byte) (l >> 48);
        data[pos+1] = (byte) (l >> 32);
        data[pos+2] = (byte) (l >> 16);
        data[pos+3] = (byte) (l);
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
        arr[pos] = (byte) (l >> 48);
        arr[pos+1] = (byte) (l >> 32);
        arr[pos+2] = (byte) (l >> 16);
        arr[pos+3] = (byte) (l);
    }
    
    public byte[] getBytes() {
        return data;
    }
    
    public double getClient() {
        return get(ID);
    }
    
    public void update(EnemyShip e) {
        e.x = this.get(X);
        e.y = this.get(Y);
        e.dir = this.get(DIR);
    }
}