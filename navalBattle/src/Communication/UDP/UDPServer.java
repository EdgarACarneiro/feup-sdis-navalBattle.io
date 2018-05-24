package Communication.UDP;

import Messages.UDPMessage;
import Utils.HigherLayer;
import Utils.ThreadPool;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer extends Thread {

    private static final int MESSAGE_SIZE = 1024;

    private DatagramSocket socket;

    /**
     * ThreadPool to quickly dispatch the received UDP messages.
     * Dispatcher in this architecture level to minimize the waiting time of message in the UDP channel
     */
    private ThreadPool dispatcher;

    private byte[] buf = new byte[MESSAGE_SIZE];

    private HigherLayer superior;
 
    public UDPServer(HigherLayer higherLayer, int port) {
        superior = higherLayer;
        dispatcher = new ThreadPool();

        try {
            socket = new DatagramSocket(port);
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