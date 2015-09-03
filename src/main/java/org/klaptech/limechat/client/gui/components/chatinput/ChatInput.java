package org.klaptech.limechat.client.gui.components.chatinput;

import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Ex
 * @author rlapin
 */
public class ChatInput extends HBox{
    private TextArea inputTextArea;
    public ChatInput() {
        getStylesheets().add(getClass().getClassLoader().getResource("fxml/chatinput.css").toExternalForm());
        initComponents();
    }

    private void initComponents() {


        inputTextArea = new TextArea();
        // Set priority for horizontal growing to text field
        HBox.setHgrow(inputTextArea, Priority.ALWAYS);
        inputTextArea.setId("chatinput");
        inputTextArea.setContextMenu(new ChatInputContextMenu());
        inputTextArea.setWrapText(true);
        inputTextArea.setVisible(true);
        inputTextArea.setPrefRowCount(2);
        getChildren().add(inputTextArea);
    }
}
