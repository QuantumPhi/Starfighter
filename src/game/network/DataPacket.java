package game.network;

public class DataPacket {

    public static final int MAX_SIZE = 16;
    
    public static final int ID = 0;
    public static final int X = 4;
    public static final int Y = 8;
    public static final int DIR = 12;
    
    private byte[] data;
        
    public DataPacket(byte[] data) {
        this.data = data;
    }
    
    // Called by server send thread.
    public DataPacket(EnemyShip s) {
        data = new byte[MAX_SIZE];
        /*
        add(e.id,ID);
        add(e.x,X);
        add(e.y,Y);
        add(e.dir,DIR);
        */
    }
        
    public final void add(int i, int pos) {
        data[pos] = (byte) (i >> 24);
        data[pos+1] = (byte) (i >> 16);
        data[pos+2] = (byte) (i >> 8);
        data[pos+3] = (byte) (i);
    }
    
    public int get(int pos) {
        int n = 0;
        for (int i=0;i<4;i++) {
            n <<= 8;
            n |= (int)data[i+pos] & 0xFF;
        }
        return n;
    }
    
    public static void add(byte[] arr, int i, int pos) {
        arr[pos] = (byte) (i >> 24);
        arr[pos+1] = (byte) (i >> 16);
        arr[pos+2] = (byte) (i >> 8);
        arr[pos+3] = (byte) (i);
    }
    
    public byte[] getBytes() {
        return data;
    }
    
    public int getClient() {
        return get(ID);
    }
    
    public void update(EnemyShip e) {
        e.x = this.get(X);
        e.y = this.get(Y);
        e.dir = this.get(DIR);
    }
}