package Communication.UDP;

import Messages.UDPMessage;
import Utils.HigherLayer;
import Utils.ThreadPool;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


/**
 * The Class UDPServer.
 */
public class UDPServer extends Thread {

    /** The Constant MESSAGE_SIZE. */
    private static final int MESSAGE_SIZE = 2048;

    /** The socket. */
    private DatagramSocket socket;

    /**
     * ThreadPool to quickly dispatch the received UDP messages.
     * Dispatcher in this architecture level to minimize the waiting time of message in the UDP channel
     */
    private ThreadPool dispatcher;

    /** The buf. */
    private byte[] buf = new byte[MESSAGE_SIZE];

    /** The superior. */
    private HigherLayer superior;
 
    /**
     * Instantiates a new UDP server.
     *
     * @param higherLayer the higher layer
     * @param port the port
     */
    public UDPServer(HigherLayer higherLayer, int port) {
        superior = higherLayer;
        dispatcher = new ThreadPool();

        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
 
    /* 
     * @see java.lang.Thread#run()
     */
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

    /**
     * Report to superior.
     *
     * @param packet the packet
     */
    private void reportToSuperior(DatagramPacket packet) {
        superior.receiveReport(new UDPMessage(packet));
    }
}