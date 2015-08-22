package org.klaptech.limechat.server;

import java.net.Socket;

/**
 * Client event contains information which transfer to clientlister
 * @author rlapin
 */
public class ClientEvent {

    private Socket socket;

    public ClientEvent(Socket socket) {
        this.socket = socket;
    }

    /**
     *
     * @return client socket
     */
    public Socket getSocket() {
        return socket;
    }

}
