package org.klaptech.limechat.server;

import java.net.Socket;

/**
 * Client event contains information which transfer to clientlistener
 * @author rlapin
 */
public class ClientEvent {

    private Socket socket;
    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
