package org.klaptech.limechat.shared.server;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.MessageType;

/**
 * Message sends as answer from server to client with result of authorization
 *
 * @author rlapin
 */
public class LoginAnswer implements Message {
    /**
     * Defines result of login operation
     */
    private final TYPE answerType;

    protected LoginAnswer(LoginAnswer.TYPE answerType) {
        this.answerType = answerType;
    }

    @Override
    public MessageType getType() {
        return MessageType.ANSWER_LOGIN;
    }

    public TYPE getAnswerType() {
        return answerType;
    }

    /**
     * Answer type
     * SUCCESS - when user successfully login to the server
     * USER_ALREADY_CON - user has already connected to the server
     * INCORRECT_PASSWORD - user input incorrrect pwd
     * USER_NOT_EXISTS - first user should register
     */
    public enum TYPE {
        SUCCESS,
        USER_ALREADY_CON,
        INCORRECT_PASSWORD,
        USER_NOT_EXISTS
    }
}
