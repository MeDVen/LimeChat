package org.klaptech.limechat.shared.general;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.MessageType;

/**
 * Use when client send message
 * @author rlapin
 */
public class SendMessage implements Message{
    private String message;

    protected SendMessage(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    @Override
    public MessageType getType() {
        return MessageType.MSG;
    }
}
