package org.klaptech.limechat.shared.server;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.enums.JoinResultType;
import org.klaptech.limechat.shared.enums.LoginAnswerType;

/**
 * Factory for server messages
 * @author rlapin
 */
public class ServerMessageFactory {
    /**
     * Create login answer to login message
     * @param answerType result of login operation {@link LoginAnswerType}
     * @return
     */
    public static Message createLoginAnswer(LoginAnswerType answerType) {
        return new LoginAnswer(answerType);
    }

    public static Message createJoinChannelAnswer(JoinResultType joinAnswer) {
        return new JoinChannelAnswer(joinAnswer);
    }
}
