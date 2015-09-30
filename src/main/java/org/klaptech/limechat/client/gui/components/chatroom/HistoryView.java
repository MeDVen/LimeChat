package org.klaptech.limechat.client.gui.components.chatroom;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 * @author rlapin
 */
public class HistoryView extends VBox {
    private TextArea historyTextArea;

    public HistoryView() {
        getStylesheets().add(getClass().getClassLoader().getResource("fxml/historyview.css").toExternalForm());
        initComponents();
    }

    private void initComponents() {
        historyTextArea = new TextArea();
        historyTextArea.setId("historytextarea");
        historyTextArea.setEditable(false);
        historyTextArea.setText("This place for messages history.");
        getChildren().add(historyTextArea);
        historyTextArea.setContextMenu(new ContextMenu());
    }
}
