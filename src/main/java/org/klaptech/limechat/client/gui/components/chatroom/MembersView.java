package org.klaptech.limechat.client.gui.components.chatroom;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.klaptech.limechat.client.utils.GUIUtils;
import org.klaptech.limechat.shared.entities.UserInfo;
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
 * @see UserInfo
 *
 * @author MeDVen
 */
public class MembersView extends ListView {

    private ObservableList<UserInfo> users = FXCollections.observableArrayList();

    public MembersView() {
        GUIUtils.addCss(this, "fxml/membersview.css");
        initComponents();
    }

    private void initComponents() {
        setId("membersview");
        setItems(users);
        setCellFactory(param -> new UserCell());
    }

    private class UserCell extends ListCell<UserInfo> {
        public void updateItem(UserInfo item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setGraphic(createImageView(item.getState().getStateImg(), 31));
                setText(item.getName());
                setTooltip(createTooltip(item));
            }
        }

        private Tooltip createTooltip(UserInfo item) {
            Tooltip tooltip = new Tooltip();
            tooltip.setText(item.getName());
            tooltip.setGraphic(createImageView(item.getImage(), 31));
            return tooltip;
        }

        private ImageView createImageView(Image img, int size) {
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(size);
            imageView.setFitHeight(size);
            return imageView;
        }
    }

    public ObservableList<UserInfo> getUsers() {
        return users;
    }
}
