package Communication;

import Messages.UDPMessage;
import Server.PlayersListener;
import Utils.ThreadPool;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer extends Thread {
 
    private DatagramSocket socket;

    /**
     * ThreadPool to quickly dispatch the received UDP received messages.
     * Dispatcher in this architecture level to minimize the waiting time of message in the UDP channel
     */
    private ThreadPool dispatcher;

    private byte[] buf = new byte[256];

    private PlayersListener superior;
 
    public UDPServer(PlayersListener higherLayer) {
        dispatcher = new ThreadPool();
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

            dispatcher.run(() -> reportToSuperior(packet));
        }
    }

    private void reportToSuperior(DatagramPacket packet) {
        superior.receiveReport(new UDPMessage(packet));
    }
}