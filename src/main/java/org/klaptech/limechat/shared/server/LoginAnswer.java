package org.klaptech.limechat.shared.server;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.enums.MessageType;
import org.klaptech.limechat.shared.enums.LoginAnswerType;

/**
 * Message sends as answer from server to client with result of authorization
 *
 * @author rlapin
 */
public class LoginAnswer implements Message {
    /**
     * Defines result of login operation
     */
    private final LoginAnswerType answerType;

    protected LoginAnswer(LoginAnswerType answerType) {
        this.answerType = answerType;
    }

    @Override
    public MessageType getType() {
        return MessageType.ANSWER_LOGIN;
    }

    public LoginAnswerType getAnswerType() {
        return answerType;
    }

}
