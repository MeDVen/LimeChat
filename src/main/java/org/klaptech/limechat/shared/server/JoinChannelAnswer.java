package org.klaptech.limechat.shared.server;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.enums.MessageType;
import org.klaptech.limechat.shared.enums.JoinResultType;

/**
 * Message is send to client as a result of joining channel
 * @author rlapin
 */
public class JoinChannelAnswer implements Message {
    private JoinResultType joinAnswer;

    public JoinChannelAnswer(JoinResultType joinAnswer) {
        this.joinAnswer = joinAnswer;
    }

    public JoinResultType getJoinAnswer() {
        return joinAnswer;
    }

    @Override
    public MessageType getType() {
        return MessageType.ANSWER_JOIN;
    }
}
