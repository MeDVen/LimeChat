package org.klaptech.limechat.shared.server;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.entities.UserInfo;
import org.klaptech.limechat.shared.enums.JoinResultType;
import org.klaptech.limechat.shared.enums.LeaveType;
import org.klaptech.limechat.shared.enums.LoginAnswerType;
import org.klaptech.limechat.shared.enums.RegisterAnswerType;

import java.util.List;

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
     * @param channelName
     * @return joinchannel answer
     */
    public static Message createJoinChannelAnswer(JoinResultType joinAnswerType, String channelName) {
        return new JoinRoomAnswer(joinAnswerType, channelName);
    }

    /**
     * Leave channel answer
     * @param leaveType
     * @param channelName
     * @return leavechannel answer
     */
    public static Message createLeaveChannelAnswer(LeaveType leaveType, String channelName) {
        return new LeaveRoomAnswer(leaveType, channelName);
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

    /**
     * Send when new user join to room for every user in room except new user
     *
     * @param userInfo
     * @param channelName
     * @return
     */
    public static Message createNewUserInRoomMessage(UserInfo userInfo, String channelName) {
        return new NewUserInRoomMessage(userInfo, channelName);
    }

    /**
     * Send all list of user in room for new user
     *
     * @param usersInfoList
     * @param name
     * @return
     */
    public static Message createRoomUsersMessage(List<UserInfo> usersInfoList, String name) {
        return new RoomUsersMessage(usersInfoList, name);
    }
}
