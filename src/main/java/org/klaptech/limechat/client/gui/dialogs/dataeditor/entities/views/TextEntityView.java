package org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.views;

import javafx.scene.Node;
import javafx.scene.control.TextField;

/**
 * TextView for editor<br>
 * For styling use eTextfield in editors.css
 *
 * @author rlapin
 */
public class TextEntityView implements EntityView {
    private TextField textField;

    public TextEntityView() {
        textField = new TextField();
    }

    @Override
    public Node getGraphics() {
        return textField;
    }
}
