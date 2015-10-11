package org.klaptech.limechat.client.entities;

/**
 * Describes chat room
 *
 * @author rlapin
 */
public class ChatRoom {
    private String name;

    public ChatRoom(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
