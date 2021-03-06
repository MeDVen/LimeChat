package org.klaptech.limechat.shared.client;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.enums.MessageType;

/**
 * Message is send by user to server , when he want to join channel
 * @author rlapin
 */
public class JoinRoomMessage implements Message {
    /**
     * Room name
     */
    private final String channelName;
    /**
     * Optional field, used when channel is closed (not null , if there is no password use "")
     */
    private final String password;

    protected JoinRoomMessage(String channelName, String password) {
        this.channelName = channelName;
        this.password = password;
    }

    @Override
    public MessageType getType() {
        return MessageType.JOIN;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getPassword() {
        return password;
    }
}
