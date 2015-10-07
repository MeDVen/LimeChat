package org.klaptech.limechat.client.gui.components.chatinput;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import org.klaptech.limechat.client.utils.GUIUtils;

/**
 * MessageView use HTMLEditor control. Editor toolbar hided by GUI-hack method.
 * By default editor control has focus. You may user another constant to off focus.
 *
 * @see GUIUtils - hideHTMLEditorToolbars(HTMLEditor editor)
 * @author MeDVen
 */
public class MessageView extends VBox {

    /**
     * Text using for get height of text
     */
    public static final Text TEXT = new Text();
    public static final String EDITOR_DEFAULT_STYLE_NOT_FOCUSED = "<body style=\"font-family: Helvetica, sans-serif; color: #96b946; "
            + "background-color: #828282; border: 1px solid black\"></body>";
    public static final String EDITOR_DEFAULT_STYLE_FOCUSED = "<body onLoad='document.body.focus();' contenteditable='true' "
            + "style='font-family: Helvetica, sans-serif; "
            + "color: #96b946; background-color: #828282; "
            + "border: 1px solid black;' />";
    public String typedText;
    private HTMLEditor inputHTMLTextArea;

    public MessageView() {
        GUIUtils.addCss(this,"fxml/messageview.css");
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
                inputHTMLTextArea.setHtmlText(EDITOR_DEFAULT_STYLE_FOCUSED);
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
        inputHTMLTextArea.setHtmlText(EDITOR_DEFAULT_STYLE_FOCUSED);
        inputHTMLTextArea.setVisible(true);

        getChildren().add(new MessageViewToolbar());
        getChildren().add(inputHTMLTextArea);
    }

    private void updateSize(String newValue) {
        TEXT.setText(newValue);
        inputHTMLTextArea.setPrefHeight(TEXT.getBoundsInLocal().getHeight());
    }
}
