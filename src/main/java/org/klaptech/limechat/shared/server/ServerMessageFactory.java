package org.klaptech.limechat.shared.server;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.server.LoginAnswer.TYPE;

/**
 * Factory for server messages
 * @author rlapin
 */
public class ServerMessageFactory {
    /**
     * Create login answer to login message
     * @param answerType result of login operation {@link TYPE}
     * @return
     */
    public static Message createLoginAnswer(TYPE answerType) {
        return new LoginAnswer(answerType);
    }
}
