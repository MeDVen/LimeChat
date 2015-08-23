package org.klaptech.limechat.server;

import java.net.Socket;
import java.util.logging.Logger;

/**
 * Client entity
 *
 * @author rlapin
 */
public class Client {
    private static final Logger LOGGER = Logger.getLogger(Client.class.getCanonicalName());
    /**
     * Socket getting from client connection
     */
    private Socket socket;
    private ClientListener clientListener;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public String toString() {
        return String.format("Client [ ip: %s ]", socket.getInetAddress().getHostAddress());
    }

    public void setClientListener(ClientListener clientListener) {
        this.clientListener = clientListener;
    }


}
