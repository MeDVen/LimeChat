package org.klaptech.limechat.shared.enums;

import javafx.scene.image.Image;
import org.klaptech.limechat.client.utils.GUIConstants;

/**
 * @author MeDVen
 */
public enum UserState {
    AFK(GUIConstants.AFK_IMAGE),
    ONLINE(GUIConstants.ONLINE_IMAGE),
    OFFLINE(GUIConstants.OFFLINE_IMAGE),
    BUSY(GUIConstants.BUSY_IMAGE),
    TYPING(GUIConstants.TYPING_IMAGE);

    private final Image stateImg;

    UserState(Image stateImg) {
        this.stateImg = stateImg;
    }

    public Image getStateImg() {
        return stateImg;
    }
}
