package org.klaptech.limechat.client.gui.components;

import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import org.klaptech.limechat.client.entities.ChatRoom;
import org.klaptech.limechat.client.gui.GUIManager;
import org.klaptech.limechat.client.gui.components.chatroom.RoomTab;
import org.klaptech.limechat.client.gui.dialogs.Dialogs;
import org.klaptech.limechat.client.net.ServerConnector;
import org.klaptech.limechat.client.utils.GUIUtils;
import org.klaptech.limechat.shared.client.ClientMessageFactory;
import org.klaptech.limechat.shared.entities.UserInfo;
import org.klaptech.limechat.shared.enums.JoinResultType;

import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Main chat content
 *
 * @author rlapin
 */
public class MainView extends GridPane {
    private static final Logger LOGGER = getLogger(MainView.class.getName());
    private ChatTabPane tabPane;
    private ResourceBundle resourceBundle;
    public MainView() {

        initComponents();
        resourceBundle = ResourceBundle.getBundle(getClass().getCanonicalName());
        GUIUtils.autoSizeGridPaneColumns(this);
        GUIUtils.autoSizeGridPaneRows(this);
    }

    private void initComponents() {
        tabPane = new ChatTabPane();
        UserInfo userInfo = GUIManager.getInstance().getUserInfo();
        Executors.newSingleThreadExecutor().execute(() -> {
            for (ChatRoom room : userInfo.getDefaultRooms()) {
                addNewRoom(room.getName());

            }
            Platform.runLater(() -> add(tabPane, 0, 0));
        });
    }

    /**
     * Try to join room and add new tab if join in succeed
     *
     * @param name
     */
    private void addNewRoom(String name) {

        ServerConnector.INSTANCE.write(ClientMessageFactory.createJoinChannelMessage(name));

    }

    public void userJoinedRoom(JoinResultType joinResultType, String roomName) {
        switch (joinResultType) {
            case SUCCESS:
                Platform.runLater(() -> tabPane.addNewTab(new RoomTab(roomName)));
                LOGGER.info("You successfully join room" + roomName);
                break;
            case ACCESS_FORBIDDEN:
                Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), resourceBundle.getString("errorJoinTitle"), resourceBundle.getString("noAccess"), Dialogs.IconType.ERROR);
                break;
            case ALREADY_ON_CHANNEL:
                Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), resourceBundle.getString("errorJoinTitle"), resourceBundle.getString("alreadyIn"), Dialogs.IconType.ERROR);
                break;
            case CHANNEL_NOT_FOUND:
                Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), resourceBundle.getString("errorJoinTitle"), resourceBundle.getString("roomNotFound"), Dialogs.IconType.ERROR);
                break;
            case INCORRECT_PASSWORD:
                Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), resourceBundle.getString("errorJoinTitle"), resourceBundle.getString("incorrectPwd"), Dialogs.IconType.ERROR); //TODO add input dialog for new password
                break;
        }
    }

    public void newUserInRoom(UserInfo userInfo, String roomName) {
        LOGGER.info("new user in room" + roomName);
        Platform.runLater(() -> tabPane.getRoomByName(roomName).getRoomView().getMembersView().getUsers().add(userInfo));

    }

    public void updateRoomUsers(List<UserInfo> usersInfoList, String roomName) { //TODO should invoke only when we send request from client
        LOGGER.info("update room users" + roomName);
        Platform.runLater(() -> tabPane.getRoomByName(roomName).getRoomView().getMembersView().getUsers().addAll(usersInfoList));
    }
}
