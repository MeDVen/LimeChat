package org.klaptech.limechat.client.gui.components;

import javafx.scene.layout.GridPane;
import org.klaptech.limechat.client.entities.ChatRoom;
import org.klaptech.limechat.client.entities.User;
import org.klaptech.limechat.client.gui.GUIManager;
import org.klaptech.limechat.client.gui.components.chatroom.RoomTab;
import org.klaptech.limechat.client.utils.GUIUtils;

/**
 * Main chat content
 *
 * @author rlapin
 */
public class MainView extends GridPane {
    ChatTabPane tabPane;

    public MainView() {

        initComponents();

        GUIUtils.autoSizeGridPaneColumns(this);
        GUIUtils.autoSizeGridPaneRows(this);
    }

    private void initComponents() {
        tabPane = new ChatTabPane();
        User user = GUIManager.getInstance().getUser();
        for (ChatRoom room : user.getDefaultRooms()) {
            tabPane.addNewTab(new RoomTab(room.getName()));
        }
        add(tabPane, 0, 0);
    }

}
