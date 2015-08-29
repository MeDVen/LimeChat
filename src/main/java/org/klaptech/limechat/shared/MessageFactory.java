package org.klaptech.limechat.shared;

import org.klaptech.limechat.shared.client.Login;
import org.klaptech.limechat.shared.general.SendMessage;

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
        return new Login(username, password);
    }

    /**
     * Create send message
     * @param text
     * @return message sending to client/server with text
     */
    public static Message createSendMessage(String text) {
        return new SendMessage(text);
    }
}
