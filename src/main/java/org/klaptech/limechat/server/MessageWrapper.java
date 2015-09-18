package org.klaptech.limechat.server;

import java.nio.channels.SocketChannel;
import org.klaptech.limechat.shared.Message;

/**
 * Class contains sender information and message object
 * Use by server while deserialize message object which is get from clients
 * @author rlapin
 */
public class MessageWrapper{
    private final SocketChannel socket;
    private final Message message;

    public MessageWrapper(SocketChannel socket, Message message) {
        this.socket = socket;
        this.message = message;
    }

    public SocketChannel getSocket() {
        return socket;
    }

    public Message getMessage() {
        return message;
    }
}
