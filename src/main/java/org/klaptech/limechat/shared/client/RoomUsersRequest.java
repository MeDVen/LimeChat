package org.klaptech.limechat.shared.client;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.enums.MessageType;

/**
 * @author rlapin.
 */
public class RoomUsersRequest implements Message {
    private String roomName;

    public RoomUsersRequest(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }

    @Override
    public MessageType getType() {
        return MessageType.ROOM_USERS_REQUEST;
    }
}
