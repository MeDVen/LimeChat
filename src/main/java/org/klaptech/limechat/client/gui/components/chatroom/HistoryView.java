package org.klaptech.limechat.client.gui.components.chatroom;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextArea;

/**
 * @author rlapin
 */
public class HistoryView extends TextArea {
//    private TextArea historyTextArea;

    public HistoryView() {
        getStylesheets().add(getClass().getClassLoader().getResource("fxml/historyview.css").toExternalForm());
        initComponents();
    }

    private void initComponents() {
        setId("historytextarea");
        setEditable(false);
        setText("(00:00) user1: Cool chat. :limechat: \n"
                + "(00:01) user2: Yeah. :thumbsup:");
        setContextMenu(new ContextMenu());
//        historyTextArea = new TextArea();
//        historyTextArea.setId("historytextarea");
//        historyTextArea.setEditable(false);
//        historyTextArea.setText("This place for messages history.");
//        getChildren().add(historyTextArea);
//        historyTextArea.setContextMenu(new ContextMenu());
    }
}
