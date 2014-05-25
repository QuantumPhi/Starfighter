package game.network;

import game.ship.EnemyShip;
import game.ship.PlayerShip;
import game.ship.ShipType;
import game.ship.modules.projectiles.Laser;
import game.ship.modules.projectiles.Missile;
import game.ship.modules.projectiles.Projectile;
import game.state.StatePlaying;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Queue;

public class ClientNetworkHandler {
    
    private DatagramSocket socket;
    private Thread get;
    private Thread send;
    private PlayerShip player;
    private volatile boolean running = true;
    private InetAddress ip;
    private int port = 0;
    private List<EnemyShip> enemies;
    private List<Projectile> projectiles;
    private StatePlaying state;
    
    private int myClientId;
    private long responseTime = -1;
    
    public ClientNetworkHandler(String ip, int port, PlayerShip player, List<EnemyShip> enemies,
            List<Projectile> projectiles, StatePlaying state) {
        this.enemies = enemies;
        this.projectiles = projectiles;
        try {
            this.ip = InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            throw new NetworkException("Invalid ip: (" + ip + ") - " + e);
        }
        this.port = port;
        this.player = player;
        this.state = state;
    }
    
    public void start() {
        try {
            socket = new DatagramSocket();
        } catch(SecurityException e) {
            throw new NetworkException("Security error: " + e);
        } catch (SocketException e) {
            throw new NetworkException("Error binding socket to " + port + ": " + e);
        }
        
        handshake();
        
        player = new PlayerShip(myClientId, ShipType.HUMAN_SHIP);
        state.setPlayer(player);
        state.connected();
        
        
        get = new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] recvData = new byte[DataPacket.MAX_SIZE];
                DatagramPacket recvPacket = new DatagramPacket(recvData,recvData.length);

                while (running) {
                    try {
                        socket.receive(recvPacket);
                    } catch (IOException e) {
                        throw new NetworkException("Error receiving packet: " + e);
                    }
                    responseTime = System.currentTimeMillis();
                    DataPacket recvDataPacket = new DataPacket(recvData);
                    
                    double dataPacketType = recvDataPacket.getDouble(DataPacket.TYPE);
                    
                    System.out.println(dataPacketType);
                    
                    if (recvDataPacket.getClient() == myClientId && dataPacketType>=0)
                        continue;
                    
                    if (dataPacketType == -1) {
                        projectiles.add(new Laser(recvDataPacket));
                        continue;
                    }
                    
                    if (dataPacketType == -2) {
                        projectiles.add(new Missile(recvDataPacket));
                        continue;
                    }
                    
                    boolean updated = false;

                    for (EnemyShip e : enemies) {
                        if (recvDataPacket.getClient() == e.getID()) {
                            recvDataPacket.update(e);
                            updated = true;
                            break;
                        }
                    }

                    if (updated)
                        continue;

                    enemies.add(new EnemyShip(recvDataPacket));
                }
            }
        });
        get.start();
        
        send = new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] sendData;
                Queue<Projectile> pq = player.getProjectileQueue();
                while (running) {
                    if (responseTime != -1 && System.currentTimeMillis()-responseTime > 1000) {
                        System.out.println("Disconnecting.");
                        running = false;
                    }
                    
                    if (pq.size() > 0) {
                        sendData = new DataPacket(pq.poll()).getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,ip,port);
                        try {
                            socket.send(sendPacket);
                        } catch (IOException e) {
                            throw new NetworkException("Error sending packet: " + e);
                        }
                        continue;
                    }
                    
                    sendData = new DataPacket(player).getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,ip,port);
                    try {
                        socket.send(sendPacket);
                    } catch (IOException e) {
                        throw new NetworkException("Error sending packet: " + e);
                    }
                }
                if (socket != null && !socket.isClosed()) {
                    System.out.println("Closing socket.");
                    socket.close();
                }
            }
        });
        send.start();
    }

    private void handshake() {
        Socket handshakeSocket = null;
        try {
            handshakeSocket = new Socket(ip.getHostAddress(),port);
        } catch (IOException e) {
            throw new NetworkException("Error creating TCP socket: " + e);
        }
        
        InputStream in = null;
        try {
            in = handshakeSocket.getInputStream();
        } catch (IOException e) {
            throw new NetworkException("Error getting input stream: " + e);
        }
        try {
            myClientId = in.read();
        } catch (IOException e) {
            throw new NetworkException("Error recieving handshake data: " + e);
        }
        try {
            handshakeSocket.close();
        } catch (IOException e) {
            throw new NetworkException("Error closing handshake socket: " + e);
        }
        System.out.println(myClientId);
    }
}