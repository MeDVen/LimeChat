package org.klaptech.limechat.shared.server;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.entities.UserInfo;
import org.klaptech.limechat.shared.enums.MessageType;

/**
 * @author rlapin
 */
public class NewUserInRoomMessage implements Message {
    private final UserInfo userInfo;
    private final String channelName;

    NewUserInRoomMessage(UserInfo userInfo, String channelName) {
        this.userInfo = userInfo;
        this.channelName = channelName;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public String getChannelName() {
        return channelName;
    }

    @Override
    public MessageType getType() {
        return MessageType.NEW_USER;
    }
}
