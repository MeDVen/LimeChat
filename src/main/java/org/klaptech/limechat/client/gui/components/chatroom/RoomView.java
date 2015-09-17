package org.klaptech.limechat.client.gui.components.chatroom;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.klaptech.limechat.client.gui.components.chatinput.ChatInputMessage;

/**
 * Room pane. Contains of input, room history
 * @author rlapin
 */
public class RoomView extends VBox{
    ChatInputMessage chatInputMessage;
    private HistoryView historyView;

    public RoomView() {
        initComponents();
    }

    private void initComponents() {
        BorderPane borderPane = new BorderPane();
        historyView = new HistoryView();
        chatInputMessage = new ChatInputMessage();
        borderPane.setCenter(historyView);
        borderPane.setBottom(chatInputMessage);
    }
}
