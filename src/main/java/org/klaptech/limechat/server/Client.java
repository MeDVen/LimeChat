package org.klaptech.limechat.server;

import java.net.Socket;

/**
 * Client entity
 * @author rlapin
 */
public class Client {
    /**
     * Socket getting from client connection
     */
    private Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public String toString() {
        return String.format("Client [ ip: %s ]",socket.getInetAddress().getHostAddress());
    }
}
