package game.network;

import game.ship.EnemyShip;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    
    private int port;
    private DatagramSocket socket;
    private int clientCounter = 0;
    private List<EnemyShip> players;
    private Map<Integer,Long> ping;
    private List<Integer> killIds;
    private boolean running = true;
    private long iteration;
    
    public static void main(String[] args) {
        new Server(9999).start();
    }
    
    public Server(int port) {
        players = new CopyOnWriteArrayList<EnemyShip>();
        ping = new ConcurrentHashMap<Integer,Long>();
        killIds = new ArrayList<Integer>();
        iteration = 0;
        this.port = port;
    }
    
    public boolean isKillId(int id) {
        return killIds.contains(id);
    }
    
    public void killId(int id) {
        killIds.remove(new Integer(id));
        players.remove(new EnemyShip(id));
    }
    
    public void start() {
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            throw new NetworkException("Error binding socket to " + port + ": " + e);
        }
        
        Thread handshakeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                ServerSocket socket = null;
                try {
                    socket = new ServerSocket(port);
                } catch (IOException e) {
                   throw new NetworkException("Error creating TCP socket: " + e);
                }
                while (running) {
                    Socket clientSocket = null;
                    try {
                        clientSocket = socket.accept();
                    } catch (IOException e) {
                        throw new NetworkException("Error accepting socket: " + e);
                    }
                    OutputStream out = null;
                    try {
                        out = clientSocket.getOutputStream();
                    } catch (IOException e) {
                        throw new NetworkException("Error getting output stream: " + e);
                    }
                    try {
                        out.write(clientCounter);
                    } catch (IOException e) {
                        throw new NetworkException("Error sending handshake data: " + e);
                    }
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        throw new NetworkException("Error closing handshake socket: " + e);
                    }
                    ping.put(clientCounter,iteration);
                    System.out.println("New client! " + clientCounter);
                    clientCounter++;
                }
            }
        });
        handshakeThread.start();
        
        // Kicks dc'ed clients.
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                iteration++;
                for (Map.Entry<Integer,Long> entry : ping.entrySet()) {
                    long oldIteration = entry.getValue();
                    if (iteration-oldIteration > 5) {
                        killIds.add(entry.getKey());
                        ping.remove(entry.getKey());
                    }
                }
            }
        },200,200);
        
        Thread recieveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] bytes = new byte[DataPacket.MAX_SIZE];
                DatagramPacket recvPacket = new DatagramPacket(bytes,bytes.length);
                DataPacket packet;
                int clientId;
                boolean updated;
                
                while (running) {
                    try {
                        socket.receive(recvPacket);
                    } catch (IOException e) {
                        System.out.println("Unable to recieve data: " + e);
                    }
                    
                    packet = new DataPacket(recvPacket.getData());
                    clientId = (int) packet.getClient();
                    
                    //System.out.println(clientId);
                    
                    updated = false;
                    for (EnemyShip e : players) {
                        //System.out.println(e.getID());
                        if (e.getID() == clientId) {
                            ping.put(clientId,iteration);
                            packet.update(e);
                            updated = true;
                            break;
                        }
                    }
                    
                    if (updated)
                        continue;
                    
                    players.add(new EnemyShip(packet));
                    System.out.println("Adding new enemy. ID: " + packet.get(DataPacket.ID));
                    
                    Runnable r = new ServerSendThread(players,socket,Server.this,
                            recvPacket.getAddress(),recvPacket.getPort(),clientId);
                    new Thread(r).start();
                }
            }
        });
        recieveThread.start();
    }
}