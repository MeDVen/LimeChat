package org.klaptech.limechat.client.events;

import org.klaptech.limechat.client.gui.GUIManager;
import org.klaptech.limechat.shared.entities.UserInfo;
import org.klaptech.limechat.shared.enums.JoinResultType;
import org.klaptech.limechat.shared.enums.LoginAnswerType;
import org.klaptech.limechat.shared.enums.RegisterAnswerType;

import java.util.List;

/**
 * UserInfo events such as userlogin , userjoin channel e.t.c
 *
 * @author rlapin
 */
public class UserEvents {

    /**
     * user try to logging on server
     *
     * @param loginType {@link LoginAnswerType}
     */
    public void userLogged(LoginAnswerType loginType) {
        GUIManager.getInstance().getLoginDialog().userLogged(loginType);
    }

    /**
     * user try to register
     *
     * @param registerAnswerType {@link RegisterAnswerType}
     */
    public void userRegistered(RegisterAnswerType registerAnswerType) {
        GUIManager.getInstance().getLoginDialog().userRegistered(registerAnswerType);
    }

    /**
     * when user try to join room
     *
     * @param joinResultType
     */
    public void userJoinedRoom(JoinResultType joinResultType, String roomName) {
        GUIManager.getInstance().getMainView().userJoinedRoom(joinResultType, roomName);
    }

    /**
     * when new user join room
     *
     * @param userInfo new user information
     * @param roomName room which user join in
     */
    public void newUserInRoom(UserInfo userInfo, String roomName) {
        GUIManager.getInstance().getMainView().newUserInRoom(userInfo, roomName);
    }

    /**
     * Get list of users in room
     *
     * @param usersInfoList
     * @param roomName
     */
    public void updateRoomUsers(List<UserInfo> usersInfoList, String roomName) {
        GUIManager.getInstance().getMainView().updateRoomUsers(usersInfoList, roomName);
    }
}
