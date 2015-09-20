package org.klaptech.limechat.server;

import java.nio.channels.SocketChannel;

/**
 * User entity
 * @author rlapin
 */
public class User {
    private String username;
    private SocketChannel socketChannel;
    public User(String username, SocketChannel socketChannel) {
        this.username = username;
        this.socketChannel = socketChannel;
    }

    public String getUsername() {
        return username;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }
}
