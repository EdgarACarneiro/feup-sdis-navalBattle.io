package Communication.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;
 
    private byte[] buf;
 
    public UDPClient(String address, int port) throws Exception {
        socket = new DatagramSocket();
        this.address = InetAddress.getByName(address);
        this.port = port;
    }

    // TODO to be called inside a thread
    public String sendUDP(String msg) throws Exception {
        buf = msg.getBytes();

        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        socket.send(packet);

        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        return new String(packet.getData(), 0, packet.getLength());
    }
 
    public void close() {
        socket.close();
    }
}