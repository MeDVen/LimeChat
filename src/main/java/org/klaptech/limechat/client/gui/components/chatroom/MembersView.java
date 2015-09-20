package org.klaptech.limechat.client.gui.components.chatroom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import org.klaptech.limechat.client.entities.UserInfo;
import org.klaptech.limechat.shared.enums.UserState;

/**
 * View of members in room. Display members name and status circle.
 *
 * <ul>Circle colors:
 * <li>Green - online</li>
 * <li>White - offline</li>
 * <li>Yellow - afk</li>
 * <li>Red - busy</li>
 * </ul>
 *
 * @see UserState
 *
 * @author MeDVen
 */
public class MembersView extends Group {

    private ListView<UserInfo> listView;

    private ObservableList<UserInfo> testData = FXCollections.observableArrayList(new UserInfo("user1", UserState.ONLINE),
            new UserInfo("user2", UserState.AFK),
            new UserInfo("user3", UserState.BUSY),
            new UserInfo("user4", UserState.OFFLINE),
            new UserInfo("user5", UserState.TYPING));

    public MembersView() {
        getStylesheets().add(getClass().getClassLoader().getResource("fxml/membersview.css").toExternalForm());
        initComponents();
    }

    private void initComponents() {
        listView = new ListView<>();
        listView.setId("membersview");
        listView.setItems(testData);
        listView.setCellFactory(param -> new UserCell());
        getChildren().add(listView);
    }

    // TODO add colored circle-statuses
    private class UserCell extends ListCell<UserInfo> {
        public void updateItem(UserInfo item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setText(item.getName() + " " + item.getState());
            }
        }
    }

}
