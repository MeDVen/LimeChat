package org.klaptech.limechat.shared.server;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.enums.JoinResultType;
import org.klaptech.limechat.shared.enums.MessageType;

/**
 * Message is send to client as a result of joining channel
 * @author rlapin
 */
public class JoinRoomAnswer implements Message {
    private JoinResultType joinAnswer;
    /**
     * Room name
     */
    private final String channelName;

    public JoinRoomAnswer(JoinResultType joinAnswer, String channelName) {
        this.joinAnswer = joinAnswer;
        this.channelName = channelName;
    }

    public JoinResultType getJoinAnswer() {
        return joinAnswer;
    }

    public String getChannelName() {
        return channelName;
    }

    @Override
    public MessageType getType() {
        return MessageType.ANSWER_JOIN;
    }
}
