package org.klaptech.limechat.shared.client;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.MessageType;

/**
 * Login message. Message is sending when user connect to server.
 * @author rlapin
 */
public class Login implements Message {

    private final String username;
    private final byte[] password;

    protected Login(String username, byte[] password) {
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

    public byte[] getPassword() {
        return password;
    }
}
