package org.klaptech.limechat.client.entities;

import org.klaptech.limechat.shared.enums.UserState;

import javafx.scene.image.Image;

/**
 * @author MeDVen
 */
public class UserInfo {

    final private Image avatar;

    final private String name;

    final private UserState state;

    public UserInfo(String userName, UserState userState, Image avatar) {
        this.name = userName;
        this.state = userState;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public UserState getState() {
        return state;
    }

    public Image getAvatar() {
        return avatar;
    }
}
