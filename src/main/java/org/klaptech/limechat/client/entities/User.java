package org.klaptech.limechat.client.entities;

import javafx.scene.image.Image;
import org.klaptech.limechat.shared.enums.UserState;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MeDVen
 */
public class User {

    private Image avatar;

    private String name;

    private UserState state;
    private List<ChatRoom> defaultRooms;

    public User(String userName, UserState userState, Image avatar) {
        this.name = userName;
        this.state = userState;
        this.avatar = avatar;
        defaultRooms = new ArrayList<>();
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

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public List<ChatRoom> getDefaultRooms() {
        return defaultRooms;
    }
}
