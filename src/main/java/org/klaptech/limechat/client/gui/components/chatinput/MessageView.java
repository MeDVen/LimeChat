package org.klaptech.limechat.client.gui.components.chatinput;

import org.klaptech.limechat.client.utils.GUIUtils;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;

/**
 * Ex
 * @author rlapin
 */
public class MessageView extends HBox {

    private HTMLEditor inputHTMLTextArea;

    /**
     * Text using for get height of text
     */
    public static final Text TEXT = new Text();

    public static final String EDITOR_DEFAULT_STYLE = "<body style=\"font-family: Helvetica, sans-serif; color: #96b946; "
            + "background-color: #828282; border: 1px solid black\"></body>";

    public String typedText;

    public MessageView() {
        getStylesheets().add(getClass().getClassLoader().getResource("fxml/messageview.css").toExternalForm());
        initComponents();
        initListeners();
        updateSize();
    }

    private void updateSize() {
        updateSize("");
    }

    private void initListeners() {
        inputHTMLTextArea.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case ENTER:
                    typedText = inputHTMLTextArea.getHtmlText();
                    inputHTMLTextArea.setHtmlText(EDITOR_DEFAULT_STYLE);
                    event.consume();
                    // TODO send messegeView content to server
                    break;
            }
        });
    }

    private void initComponents() {
        inputHTMLTextArea = new HTMLEditor();
        inputHTMLTextArea.setMinHeight(200);
        GUIUtils.hideHTMLEditorToolbars(inputHTMLTextArea);
        HBox.setHgrow(inputHTMLTextArea, Priority.ALWAYS);
        inputHTMLTextArea.setId("messageview");
        inputHTMLTextArea.setContextMenu(new MessageViewContextMenu());
        inputHTMLTextArea.setHtmlText(EDITOR_DEFAULT_STYLE);
        inputHTMLTextArea.setVisible(true);
        inputHTMLTextArea.requestFocus();
        getChildren().add(inputHTMLTextArea);
    }

    private void updateSize(String newValue) {
        TEXT.setText(newValue);
        inputHTMLTextArea.setPrefHeight(TEXT.getBoundsInLocal().getHeight());
    }

}
