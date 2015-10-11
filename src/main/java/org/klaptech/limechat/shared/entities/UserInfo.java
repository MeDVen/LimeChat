package org.klaptech.limechat.shared.entities;

import javafx.scene.image.Image;
import org.klaptech.limechat.client.entities.ChatRoom;
import org.klaptech.limechat.shared.enums.UserState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity for describing user
 * @author MeDVen
 * @author rlapin
 */
public class UserInfo implements Serializable {

    private Image image;

    private String name;

    private UserState state;
    private List<ChatRoom> defaultRooms;

    public UserInfo(String userName, UserState userState, Image image) {
        this.name = userName;
        this.state = userState;
        this.image = image;
        defaultRooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public UserState getState() {
        return state;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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

    @Override
    public String toString() {
        return String.format("Username: %s , Default rooms ", name) + defaultRooms;
    }
}
