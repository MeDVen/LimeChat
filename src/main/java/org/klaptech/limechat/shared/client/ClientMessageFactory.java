package org.klaptech.limechat.shared.client;

import org.klaptech.limechat.shared.Message;

/**
 * Client message factory
 * @author rlapin
 */
public class ClientMessageFactory {
    /**
     * Create login message
     * @param username
     * @param password
     * @return message sending to server, when user is connecting
     */
    public static Message createLoginMessage(String username, String password) {
        return new Login(username, password);
    }
}
