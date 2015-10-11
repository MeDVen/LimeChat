package org.klaptech.limechat.shared.server;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.entities.UserInfo;
import org.klaptech.limechat.shared.enums.MessageType;

import java.util.List;

/**
 * @author rlapin
 */
public class RoomUsersMessage implements Message {
    private final List<UserInfo> usersInfoList;
    private final String roomName;

    RoomUsersMessage(List<UserInfo> usersInfoList, String roomName) {
        this.usersInfoList = usersInfoList;
        this.roomName = roomName;
    }

    public List<UserInfo> getUsersInfoList() {
        return usersInfoList;
    }

    public String getRoomName() {
        return roomName;
    }

    @Override
    public MessageType getType() {
        return MessageType.ROOM_USERS;
    }
}
