package org.klaptech.limechat.shared.general;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.enums.MessageType;

/**
 * Use when client send message
 * @author rlapin
 */
public class SendMessage implements Message{
    private String message;
    private String channelName;
    protected SendMessage(String channelName, String message) {
        this.channelName = channelName;
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public String getChannelName() {
        return channelName;
    }

    @Override
    public MessageType getType() {
        return MessageType.MSG;
    }
}
