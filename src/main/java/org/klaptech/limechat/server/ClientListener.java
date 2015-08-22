package org.klaptech.limechat.server;

/**
 * Client listener
 * @author rlapin
 */
public interface ClientListener {
    /**
     * Fires when client is connected to the server
     * @param e event object {@link ClientEvent}
     */
    void clientConnected(ClientEvent e);
}
