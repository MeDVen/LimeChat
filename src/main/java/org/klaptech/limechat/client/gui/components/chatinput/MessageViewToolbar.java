package org.klaptech.limechat.client.gui.components.chatinput;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;

/**
 * @author rkushnerev on 07.10.15.
 */
public class MessageViewToolbar extends HBox {

    private ToolBar toolbar;

    public MessageViewToolbar() {
        getStylesheets().add(getClass().getClassLoader().getResource("fxml/messageviewtoolbar.css").toExternalForm());
        initComponents();
    }

    private void initComponents() {
        // TODO imageButtons, change styles
        Button smilesBtn = new Button("Smiles");
        Button statusBtn = new Button("Change Status");

        toolbar = new ToolBar(smilesBtn, statusBtn);
        toolbar.setId("mvtoolbar"); // messageView toolbar id
        toolbar.setOrientation(Orientation.HORIZONTAL);

        getChildren().add(toolbar);
    }
}
