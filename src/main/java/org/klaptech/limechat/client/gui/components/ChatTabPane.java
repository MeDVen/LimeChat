package org.klaptech.limechat.client.gui.components;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.klaptech.limechat.client.gui.components.chatroom.RoomTab;

/**
 * TabPane with tabs. One tab - one room.
 * Tab has textArea, where user enter his message,
 * and area with conversation history.
 * Every tab has instrument panel. Smiles, call history
 * and other.
 * @author MeDVen
 */
public class ChatTabPane extends TabPane {

    public ChatTabPane() {
        super();
    }

    public void addNewTab(Tab tab) {
        this.getTabs().add(tab);
    }

    public void removeTab(Tab tab) {
        getTabs().removeAll(tab);
    }

    /**
     * Get room tab by name
     *
     * @param roomName
     * @return
     */
    public RoomTab getRoomByName(String roomName) {
        for (Tab tab : getTabs()) {
            RoomTab roomTab = (RoomTab) tab;
            if (roomTab.getRoomName().equals(roomName)) {
                return roomTab;
            }
        }
        return null;
    }
}
