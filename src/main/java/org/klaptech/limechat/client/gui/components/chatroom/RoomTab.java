package org.klaptech.limechat.client.gui.components.chatroom;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.Tab;

/**
 * @author rlapin
 */
public class RoomTab extends Tab {

    private RoomView roomView;

    public RoomTab(String roomName) {
        super(roomName);
        roomView = new RoomView();
        setClosable(false);
        setContextMenu(new ContextMenu());
        setContent(roomView);
    }
}
