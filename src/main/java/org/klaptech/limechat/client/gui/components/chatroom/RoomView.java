package org.klaptech.limechat.client.gui.components.chatroom;

import javafx.scene.layout.*;
import org.klaptech.limechat.client.gui.components.chatinput.MessageView;
import org.klaptech.limechat.client.utils.GUIUtils;

/**
 * Room pane. Contains of input, room history
 * @author rlapin
 * @author MeDVen
 */
public class RoomView extends GridPane {
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

        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();

        vBox1.getChildren().add( historyView );
        vBox2.getChildren().add(membersView);
        vBox1.setVgrow(historyView, Priority.ALWAYS);
        vBox2.setVgrow(membersView, Priority.ALWAYS);

        for (int j = 0; j < 2; j++) {
            GUIUtils.autoSizeGridPaneColumns(this);
        }
        for (int j = 0; j < 2; j++) {
            GUIUtils.autoSizeGridPaneRows(this);
        }

        add( historyView, 0, 0 );
        add( membersView, 1, 0 );
        add( chatInputMessage, 0, 1, 2, 1 );
    }
}
