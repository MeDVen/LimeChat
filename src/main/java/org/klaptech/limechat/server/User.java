package org.klaptech.limechat.server;

import org.klaptech.limechat.shared.entities.UserInfo;

import java.nio.channels.SocketChannel;

/**
 * UserInfo entity
 * @author rlapin
 */
public class User {
    private UserInfo userInfo;
    private SocketChannel socketChannel;

    public User(UserInfo userInfo, SocketChannel socketChannel) {
        this.userInfo = userInfo;
        this.socketChannel = socketChannel;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }
}
