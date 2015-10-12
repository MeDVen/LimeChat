package org.klaptech.limechat.shared.client;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.enums.MessageType;

/**
 * Login message. Message is sending when user connect to server.
 * @author rlapin
 */
public class LoginMessage implements Message {
    private final String username;
    private final String password;

    protected LoginMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public MessageType getType() {
        return MessageType.LOGIN;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
