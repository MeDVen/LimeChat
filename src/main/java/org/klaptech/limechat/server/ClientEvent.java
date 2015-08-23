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

    /**
     *
     * @return text message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setup user message
     * @param message user message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
