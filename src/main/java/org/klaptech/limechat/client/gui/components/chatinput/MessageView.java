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

//    private TextArea inputTextArea;
    /**
     * Text using for get height of text
     */
    public static final Text TEXT = new Text();

    public MessageView() {
        getStylesheets().add(getClass().getClassLoader().getResource("fxml/chatinputmessage.css").toExternalForm());
        initComponents();
        initListeners();
        updateSize();
    }

    private void updateSize() {
        updateSize("");
    }

    private void initListeners() {
        inputHTMLTextArea.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()){
            case A:
                System.out.println(10);
                event.consume();
                inputHTMLTextArea.setHtmlText(inputHTMLTextArea.getHtmlText()+"<br>");
                break;
            default:
            }
        });
//        inputTextArea.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                updateSize(newValue);
//            }
//        });

//        inputHTMLTextArea.heightProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                updateSize(newValue.toString());
//            }
//        });
    }

    private void initComponents() {
//        inputTextArea = new TextArea();
        // Set priority for horizontal growing to text field
//        HBox.setHgrow(inputTextArea, Priority.ALWAYS);
//        inputTextArea.setId("chatinput");
//        inputTextArea.setContextMenu(new MessageViewContextMenu());
//        inputTextArea.setWrapText(true);
//        inputTextArea.setVisible(true);
//        getChildren().add(inputTextArea);

        inputHTMLTextArea = new HTMLEditor();
        inputHTMLTextArea.setHtmlText("<p>Test input message!</p>");
        inputHTMLTextArea.setMinHeight( 200 );
        GUIUtils.hideHTMLEditorToolbars(inputHTMLTextArea);
        HBox.setHgrow(inputHTMLTextArea, Priority.ALWAYS);
        inputHTMLTextArea.setId("chatinput");
        inputHTMLTextArea.setContextMenu(new MessageViewContextMenu());
        inputHTMLTextArea.setVisible(true);
        getChildren().add(inputHTMLTextArea);
    }

    private void updateSize(String newValue) {
        TEXT.setText(newValue);
//        inputTextArea.setPrefHeight(TEXT.getBoundsInLocal().getHeight());
        inputHTMLTextArea.setPrefHeight(TEXT.getBoundsInLocal().getHeight());
    }

}
