package org.klaptech.limechat.client.entities;

import org.klaptech.limechat.shared.enums.UserState;

/**
 * @author MeDVen
 */
public class UserInfo {

    private String userName;

    private UserState userState;

    public UserInfo(String userName, UserState userState) {
        this.userName = userName;
        this.userState = userState;
    }

    public String getUserName() {
        return userName;
    }

    public UserState getUserState() {
        return userState;
    }
}
