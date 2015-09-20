package org.klaptech.limechat.client.entities;

import org.klaptech.limechat.shared.enums.UserState;

/**
 * @author MeDVen
 */
public class UserInfo {

    final private String name;

    final private UserState state;

    public UserInfo(String userName, UserState userState) {
        this.name = userName;
        this.state = userState;
    }

    public String getName() {
        return name;
    }

    public UserState getState() {
        return state;
    }
}
