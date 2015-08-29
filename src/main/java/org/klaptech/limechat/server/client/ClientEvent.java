package org.klaptech.limechat.server.client;

import org.klaptech.limechat.shared.server.LoginAnswer;

/**
 * Client event contains information which transfer to clientlistener
 * @author rlapin
 */
public class ClientEvent {

    private final Client client;
    private String message;
    private LoginAnswer.TYPE type;
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

    /**
     * @return result of login
     */
    public LoginAnswer.TYPE getType() {
        return type;
    }

    /**
     *
     * @param type result of login
     */
    public void setType(LoginAnswer.TYPE type) {
        this.type = type;
    }
}
