package org.klaptech.limechat.client.gui.components.chatroom;

import javafx.scene.Group;
import javafx.scene.control.TextArea;

/**
 * @author rlapin
 */
public class HistoryView extends Group {
    private TextArea historyTextArea;

    public HistoryView() {
        getStylesheets().add(getClass().getClassLoader().getResource("fxml/historyview.css").toExternalForm());
        initComponents();
    }

    private void initComponents() {
        historyTextArea = new TextArea();
        historyTextArea.setId("historytextarea");
        getChildren().add(historyTextArea);
    }
}
