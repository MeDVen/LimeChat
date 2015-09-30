package org.klaptech.limechat.client.gui.components.chatinput;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import org.klaptech.limechat.client.utils.GUIUtils;

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
                    typedText = inputHTMLTextArea.getHtmlText().toString();
                    inputHTMLTextArea.setHtmlText("");
                    event.consume();
                    System.out.println( "Typed: " + typedText );
                    // TODO send messegeView content to server
                    break;
            }
        });
    }

    private void initComponents() {
        inputHTMLTextArea = new HTMLEditor();
        inputHTMLTextArea.setMinHeight( 200 );
        GUIUtils.hideHTMLEditorToolbars(inputHTMLTextArea);
        HBox.setHgrow(inputHTMLTextArea, Priority.ALWAYS);
        inputHTMLTextArea.setId("messageview");
        inputHTMLTextArea.setContextMenu(new MessageViewContextMenu());
        inputHTMLTextArea.setVisible(true);
        getChildren().add(inputHTMLTextArea);
    }

    private void updateSize(String newValue) {
        TEXT.setText(newValue);
        inputHTMLTextArea.setPrefHeight(TEXT.getBoundsInLocal().getHeight());
    }

}
