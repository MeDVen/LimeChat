package org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.views;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.EditorEntity;
import org.klaptech.limechat.client.net.ServerAddress;

/**
 * View consists of different view , but used for change one type<br>
 * For styling use eTextField in editors.css
 *
 * @author rlapin
 */
public class ServerAdressView implements EntityView {
    private HBox hBox;

    public ServerAdressView(EditorEntity entity) {
        hBox = new HBox();
        hBox.getStyleClass().add("eLane");
        Label serverLabel = createLabel("Server");
        Label portLabel = createLabel(":");
        portLabel.getStyleClass().add("eLabel");
        if (entity.getEditableValue().getValue() instanceof ServerAddress) {
        }
        TextField addrTextField = createTextField();
        TextField portTextField = createTextField();
        hBox.getChildren().addAll(serverLabel, addrTextField, portLabel, portTextField);
    }

    private Label createLabel(String caption) {
        Label resultLabel = new Label(caption);
        resultLabel.getStyleClass().add("eLabel");
        resultLabel.prefHeightProperty().bind(hBox.heightProperty());
        return resultLabel;
    }

    private TextField createTextField() {
        TextField resultTextField = new TextField();
        resultTextField.getStyleClass().add("eTextField");
        resultTextField.prefHeightProperty().bind(hBox.heightProperty());
        return resultTextField;
    }

    @Override
    public Node getGraphics() {
        return hBox;
    }
}
