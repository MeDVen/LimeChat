package org.klaptech.limechat.client.gui.components.chatroom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.klaptech.limechat.client.entities.User;
import org.klaptech.limechat.client.utils.GUIConstants;
import org.klaptech.limechat.client.utils.GUIUtils;
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
 * In tooltip of every cell shown user avatar and name.
 *
 * @see UserState
 * @see User
 *
 * @author MeDVen
 */
public class MembersView extends ListView {

    private ObservableList<User> testData = FXCollections.observableArrayList(
            new User("user1", UserState.ONLINE, GUIConstants.USER_IMAGE),
            new User("user2", UserState.AFK, GUIConstants.USER_IMAGE),
            new User("user3", UserState.BUSY, GUIConstants.USER_IMAGE),
            new User("user4", UserState.OFFLINE, GUIConstants.USER_IMAGE),
            new User("user5", UserState.TYPING, GUIConstants.USER_IMAGE));

    public MembersView() {
        GUIUtils.addCss(this, "fxml/membersview.css");
        initComponents();
    }

    private void initComponents() {
        setId("membersview");
        setItems(testData);
        setCellFactory(param -> new UserCell());
    }

    private class UserCell extends ListCell<User> {
        public void updateItem(User item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setGraphic(createImageView(item.getState().getStateImg(), 31));
                setText(item.getName());
                setTooltip(createTooltip(item));
            }
        }

        private Tooltip createTooltip(User item) {
            Tooltip tooltip = new Tooltip();
            tooltip.setText(item.getName());
            tooltip.setGraphic(createImageView(item.getAvatar(), 31));
            return tooltip;
        }

        private ImageView createImageView(Image img, int size) {
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(size);
            imageView.setFitHeight(size);
            return imageView;
        }
    }

}
