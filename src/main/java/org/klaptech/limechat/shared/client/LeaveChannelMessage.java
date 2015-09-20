package org.klaptech.limechat.shared.client;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.enums.MessageType;

/**
 * Message is send by user to server , when he want to leave channel
 * @author rlapin
 */
public class LeaveChannelMessage implements Message{
    /**
     * Channel name
     */
    private final String channelName;

    protected LeaveChannelMessage(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public MessageType getType() {
        return MessageType.LEAVE;
    }

    public String getChannelName() {
        return channelName;
    }

}
