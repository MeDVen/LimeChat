package org.klaptech.limechat.shared.general;

import org.klaptech.limechat.shared.Message;

/**
 * Factory of general messages
 *
 * @author rlapin
 */
public class GeneralMessageFactory {

    /**
     * Create send message to channel
     * @param channelName
     * @param text
     * @return message sending to client/server with text
     */
    public static Message createSendMessage(String channelName, String text) {
        return new SendMessage(channelName, text);
    }

}
