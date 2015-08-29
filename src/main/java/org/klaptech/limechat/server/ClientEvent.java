package org.klaptech.limechat.server;

/**
 * Client event contains information which transfer to clientlistener
 * @author rlapin
 */
public class ClientEvent {

    private final Client client;
    private String message;

    public ClientEvent(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
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
