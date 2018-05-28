package Messages;

import java.net.DatagramPacket;
import java.net.InetAddress;


/**
 * The Class UDPMessage.
 */
public class UDPMessage implements Message {

    // TODO : provavelmente posso ter uma Class comum entre ambas as mensagens, com metodos comuns como:
    // TODO (continuar): getContent, getSender, fields comuns relacionados, etc

    /** The address. */
    private InetAddress address;

    /** The port. */
    private int port;

    /** The content. */
    private String content;

    /**
     * Instantiates a new UDP message.
     *
     * @param packet the packet
     */
    public UDPMessage(DatagramPacket packet) {
        address = packet.getAddress();
        port = packet.getPort();
        content= new String(packet.getData(), 0, packet.getLength()).trim();
    }

    /**
     * Gets the address.
     *
     * @return the address
     */
    public InetAddress getAddress() {
        return address;
    }

    /**
     * Gets the port.
     *
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * Gets the content.
     *
     * @return the content
     */
    public String getContent() {
        return content;
    }
}
