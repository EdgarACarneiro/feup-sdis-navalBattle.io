package Communication.UDP;

import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * The Class UDPClient.
 */
public class UDPClient implements Serializable {
    
    /** The socket. */
    private transient DatagramSocket socket;
    
    /** The address. */
    private InetAddress address;
    
    /** The port. */
    private int port;
 
    /** The buffer. */
    private byte[] buf;
 
    /**
     * Instantiates a new UDP client.
     *
     * @param address the address
     * @param port the port
     */
    public UDPClient(InetAddress address, int port) {
        try {
            socket = new DatagramSocket();
        } catch(java.net.SocketException e) {
            System.err.println("Failed to create UDP Client with IP " + address + " on port " + port);
        }

        this.address = address;
        this.port = port;
    }

    /**
     * Send UDP packet.
     *
     * @param msg the msg
     * @return the string
     */
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
 
    /**
     * Close.
     */
    public void close() {
        socket.close();
    }
}