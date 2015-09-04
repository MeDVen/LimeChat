package org.klaptech.limechat.client.gui.components.chatinput;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

/**
 * Ex
 * @author rlapin
 */
public class ChatInputMessage extends HBox{
    private TextArea inputTextArea;
    /**
     * Text using for get height of text
     */
    public static final Text TEXT = new Text();

    public ChatInputMessage() {
        getStylesheets().add(getClass().getClassLoader().getResource("fxml/chatinputmessage.css").toExternalForm());
        initComponents();
        initListeners();
        updateSize();
    }

    private void updateSize() {
        updateSize("");
    }

    private void initListeners() {
        inputTextArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                updateSize(newValue);
            }
        });
    }

    private void initComponents() {


        inputTextArea = new TextArea();
        // Set priority for horizontal growing to text field
        HBox.setHgrow(inputTextArea, Priority.ALWAYS);
        inputTextArea.setId("chatinput");
        inputTextArea.setContextMenu(new ChatInputContextMenu());
        inputTextArea.setWrapText(true);
        inputTextArea.setVisible(true);

        getChildren().add(inputTextArea);



    }

    private void updateSize(String newValue) {
        TEXT.setText(newValue);
        inputTextArea.setPrefHeight(TEXT.getBoundsInLocal().getHeight());
    }

}
