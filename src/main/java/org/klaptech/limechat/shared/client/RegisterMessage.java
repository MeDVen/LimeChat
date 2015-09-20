package org.klaptech.limechat.shared.client;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.enums.MessageType;

/**
 * Sends when user want to register on server
 *
 * @author rlapin
 */
public class RegisterMessage implements Message {
    private final String username;
    private final String password;
    private final String email;

    protected RegisterMessage(String username, String password, String email) {
        this.username = username;
        this.password = password;

        this.email = email;
    }

    @Override
    public MessageType getType() {
        return MessageType.REGISTER;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
