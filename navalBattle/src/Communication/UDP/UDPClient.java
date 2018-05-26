package Communication.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class UDPClient {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;
 
    private byte[] buf;
 
    public UDPClient(InetAddress address, int port) {
        try {
            socket = new DatagramSocket();
        } catch(java.net.SocketException e) {
            System.err.println("Failed to create UDP Client with IP " + address + " on port " + port);
        }

        this.address = address;
        this.port = port;
    }

    // TODO to be called inside a thread
    public String sendUDP(String msg) {
        buf = msg.getBytes();

        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        try {
            socket.send(packet);
            return msg;
        } catch (java.io.IOException e) {
            System.err.println(("Failed to send message to " + address + " on port " + port));
            return null;
        }
    }
 
    public void close() {
        socket.close();
    }
}