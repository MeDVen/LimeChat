package org.klaptech.limechat.shared;

/**
 * Factory of messages
 *
 * @author rlapin
 */
public class MessageFactory {
    /**
     * Create login message
     * @param username
     * @param password
     * @return message sending to server, when user is connecting
     */
    public static Message createLoginMessage(String username, String password) {
        return new MessageLogin(username, password);
    }
}
