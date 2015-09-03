package org.klaptech.limechat.client.gui.components.chatroom;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.klaptech.limechat.client.gui.components.chatinput.ChatInput;

/**
 * Room pane. Contains of input, room history
 * @author rlapin
 */
public class RoomView extends VBox{
    ChatInput chatInput;
    public RoomView() {
        initComponents();
    }

    private void initComponents() {
        BorderPane borderPane = new BorderPane();
        chatInput = new ChatInput();
        borderPane.setBottom(chatInput);
    }
}
