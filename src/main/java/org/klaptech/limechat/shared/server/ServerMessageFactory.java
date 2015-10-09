package org.klaptech.limechat.shared.server;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.enums.JoinResultType;
import org.klaptech.limechat.shared.enums.LeaveType;
import org.klaptech.limechat.shared.enums.LoginAnswerType;
import org.klaptech.limechat.shared.enums.RegisterAnswerType;

/**
 * Factory for server messages
 *
 * @author rlapin
 */
public class ServerMessageFactory {
    /**
     * Create login answer to login message
     *
     * @param answerType result of login operation {@link LoginAnswerType}
     * @return login answer
     */
    public static Message createLoginAnswer(LoginAnswerType answerType) {
        return new LoginAnswer(answerType);
    }

    /**
     * Joinanswer
     * @param joinAnswerType
     * @return joinchannel answer
     */
    public static Message createJoinChannelAnswer(JoinResultType joinAnswerType) {
        return new JoinChannelAnswer(joinAnswerType);
    }

    /**
     * Leave channel answer
     * @param leaveType
     * @param channelName
     * @return leavechannel answer
     */
    public static Message createLeaveChannelAnswer(LeaveType leaveType, String channelName) {
        return new LeaveChannelAnswer(leaveType, channelName);
    }

    /**
     * Register answer
     *
     * @param registerAnswerType {@link RegisterAnswerType}
     * @return message sending to user when he try to register
     */
    public static Message createRegisterAnswer(RegisterAnswerType registerAnswerType) {
        return new RegisterAnswer(registerAnswerType);
    }
}
