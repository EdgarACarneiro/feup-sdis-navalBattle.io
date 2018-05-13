package Server;

import Communication.CommunicationAPI;
import Utils.ThreadPool;

import java.net.DatagramPacket;

public class PlayersListener implements Runnable {

    ServerLogic logic;

    ThreadPool threadPool;

    public PlayersListener(ServerLogic serverLogic) {
        logic = serverLogic;
        threadPool = new ThreadPool();
    }

    public void receiveReport(DatagramPacket message) {
        // TODO -- received the given packet, throw a new thread with it to parse it and do more stuff (bubble it up)
    }

    @Override
    public void run() {
        CommunicationAPI.channel(this);
    }
}
