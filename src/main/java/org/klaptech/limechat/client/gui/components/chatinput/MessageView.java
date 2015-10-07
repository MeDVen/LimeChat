package org.klaptech.limechat.client.gui.components.chatinput;

import java.lang.reflect.Field;

import com.sun.javafx.scene.web.skin.HTMLEditorSkin;

import org.klaptech.limechat.client.utils.GUIUtils;

import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;

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

    public static final String EDITOR_DEFAULT_STYLE_NOT_FOCUSED = "<body style=\"font-family: Helvetica, sans-serif; color: #96b946; "
            + "background-color: #828282; border: 1px solid black\"></body>";

    public static final String EDITOR_DEFAULT_STYLE_FOCUSED = "<body onLoad='document.body.focus();' contenteditable='true' "
            + "style='font-family: Helvetica, sans-serif; "
            + "color: #96b946; background-color: #828282; "
            + "border: 1px solid black;' />";

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
        inputHTMLTextArea.requestFocus();
        getChildren().add(inputHTMLTextArea);
    }

    private void updateSize(String newValue) {
        TEXT.setText(newValue);
        inputHTMLTextArea.setPrefHeight(TEXT.getBoundsInLocal().getHeight());
    }

    public void requestFocusArea() {
        HTMLEditorSkin skin = (HTMLEditorSkin) inputHTMLTextArea.getSkin();
        try {
            Field f = skin.getClass().getDeclaredField("webView");
            f.setAccessible(true);
            WebView wv = (WebView) f.get(skin);
            Platform.runLater(() -> {
                wv.requestFocus();
                wv.getEngine().executeScript("document.body.focus()");
                wv.getEngine().executeScript(
                        "var el = document.body;\n" +
                                "if (typeof window.getSelection != \"undefined\"\n" +
                                "            && typeof document.createRange != \"undefined\") {\n" +
                                "        var range = document.createRange();\n" +
                                "        range.selectNodeContents(el);\n" +
                                "        range.collapse(false);\n" +
                                "        var sel = window.getSelection();\n" +
                                "        sel.removeAllRanges();\n" +
                                "        sel.addRange(range);\n" +
                                "    } else if (typeof document.body.createTextRange != \"undefined\") {\n" +
                                "        var textRange = document.body.createTextRange();\n" +
                                "        textRange.moveToElementText(el);\n" +
                                "        textRange.collapse(false);\n" +
                                "        textRange.select();\n" +
                                "    }");
            });
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
