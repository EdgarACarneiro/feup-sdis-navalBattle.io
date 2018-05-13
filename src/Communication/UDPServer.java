package Communication;

import Server.PlayersListener;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer extends Thread {
 
    private DatagramSocket socket;

    private byte[] buf = new byte[256];

    private PlayersListener superior;
 
    public UDPServer(PlayersListener higherLayer) {
        superior = higherLayer;

        try {
            socket = new DatagramSocket(4445);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
 
    public void run() {

        while (true) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            reportToSuperior(packet);
        }
    }

    public void reportToSuperior(DatagramPacket message) {
        superior.receiveReport(message);
    }
}