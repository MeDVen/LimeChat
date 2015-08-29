package org.klaptech.limechat.shared.client;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.MessageType;

/**
 * Login message. Message is sending when user connect to server.
 * @author rlapin
 */
public class Login implements Message {

    private final String username;
    private final String password;

    public Login(String username, String password) {
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
