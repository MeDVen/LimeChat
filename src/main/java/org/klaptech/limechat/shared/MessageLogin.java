package org.klaptech.limechat.shared;

/**
 * Login message. Message is sending when user connect to server.
 * @author rlapin
 */
public class MessageLogin implements Message{

    private final String username;
    private final String password;

    protected MessageLogin(String username, String password) {
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
