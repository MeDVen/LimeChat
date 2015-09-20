package org.klaptech.limechat.client.gui.components.chatroom;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.klaptech.limechat.client.gui.components.chatinput.MessageView;

/**
 * Room pane. Contains of input, room history
 * @author rlapin
 * @author MeDVen
 */
public class RoomView extends VBox {
    private MembersView membersView;
    private MessageView chatInputMessage;
    private HistoryView historyView;

    public RoomView() {
        initComponents();
    }

    private void initComponents() {
        historyView = new HistoryView();
        membersView = new MembersView();
        chatInputMessage = new MessageView();

        HBox hbox = new HBox();
        hbox.getChildren().addAll(historyView, membersView);
        getChildren().addAll(hbox, chatInputMessage);
    }
}
