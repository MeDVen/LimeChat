package org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.views;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.EditorEntity;

/**
 * TextView for editor<br>
 * For styling use eTextField in editors.css
 *
 * @author rlapin
 */
public class TextEntityView implements EntityView {
    private HBox hBox;

    public TextEntityView(EditorEntity entity) {
        hBox = new HBox();
        hBox.getStyleClass().add("eLane");
        Label label = new Label(entity.getLabelText());
        label.getStyleClass().add("eLabel");
        label.prefHeightProperty().bind(hBox.heightProperty());
        if (entity.getEditableValue().getValue() instanceof String) {
        }

        TextField textField = new TextField();
        textField.getStyleClass().add("eTextField");
        textField.prefHeightProperty().bind(hBox.heightProperty());
        hBox.getChildren().addAll(label, textField);
    }

    @Override
    public Node getGraphics() {
        return hBox;
    }
}
