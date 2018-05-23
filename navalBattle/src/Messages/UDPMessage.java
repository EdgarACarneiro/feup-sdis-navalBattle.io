package Messages;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class UDPMessage {

    // TODO : provavelmente posso ter uma Class comum entre ambas as mensagens, com metodos comuns como:
    // TODO (continuar): getContent, getSender, fields comuns relacionados, etc

    private InetAddress address;

    private int port;

    private String content;

    public UDPMessage(DatagramPacket packet) {
        address = packet.getAddress();
        port = packet.getPort();
        content= new String(packet.getData(), 0, packet.getLength()).trim();
    }
}
