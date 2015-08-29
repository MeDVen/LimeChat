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
    /**
     * Fires when client is disconnected to the server
     * @param e event object {@link ClientEvent}
     */
    void clientDisconnected(ClientEvent e);

    /**
     * Fires when client is sending message
     * @param e event object {@link ClientEvent}
     */
    void clientSendMessage(ClientEvent e);

    /**
     * Fires when client is login to server
     * @param e event object {@link ClientEvent}
     */
    void clientLogin(ClientEvent e);
}
