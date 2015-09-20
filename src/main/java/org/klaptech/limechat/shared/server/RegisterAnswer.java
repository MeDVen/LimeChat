package org.klaptech.limechat.shared.server;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.enums.MessageType;
import org.klaptech.limechat.shared.enums.RegisterAnswerType;

/**
 * Answer sending to client as a result of registration
 *
 * @author rlapin
 */
public class RegisterAnswer implements Message {
    private final RegisterAnswerType registerAnswerType;

    public RegisterAnswer(RegisterAnswerType registerAnswerType) {
        this.registerAnswerType = registerAnswerType;
    }

    @Override
    public MessageType getType() {
        return MessageType.ANSWER_REGISTER;
    }

    public RegisterAnswerType getRegisterAnswerType() {
        return registerAnswerType;
    }
}
