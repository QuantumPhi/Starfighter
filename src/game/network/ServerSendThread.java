package game.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

public class ServerSendThread implements Runnable {
    
    private List<EnemyPlayer> players;
    private DatagramSocket socket;
    private Server server;
    private InetAddress destIp;
    private int destPort;
    private int id;
    
    public ServerSendThread(List<EnemyPlayer> players,
            DatagramSocket socket, Server server, InetAddress destIp,
            int destPort, int id) {
        this.players = players;
        this.socket = socket;
        this.server = server;
        this.destIp = destIp;
        this.destPort = destPort;
        this.id = id;
    }
    
    @Override
    public void run() {
        byte[] data;
        while(true) {
            if (server.isKillId(id)) {
                System.out.println("Client " + id + " disconnected.");
                server.killId(id);
                break;
            }
            for (EnemyPlayer e : players) {
                data = new DataPacket(e).getBytes();
                DatagramPacket packet = new DatagramPacket(data,data.length,destIp,destPort);
                try {
                    socket.send(packet);
                } catch (IOException ex) {
                    System.out.println("Failed to send data: " + ex);
                }
            }
        }
    }
}