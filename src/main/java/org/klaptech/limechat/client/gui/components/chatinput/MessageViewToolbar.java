package org.klaptech.limechat.client.gui.components.chatinput;

import org.klaptech.limechat.client.gui.dialogs.Dialogs;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * @author MeDVen
 */
public class MessageViewToolbar extends HBox {
    private static final Image SMILE_IMAGE = new Image(MessageViewToolbar.class.getClassLoader().getResourceAsStream("images/smile.png"));
    private static final Image STATUS_IMAGE = new Image(MessageViewToolbar.class.getClassLoader().getResourceAsStream("images/circle_green.png"));

    private ToolBar toolbar;

    public MessageViewToolbar() {
        getStylesheets().add(getClass().getClassLoader().getResource("fxml/messageviewtoolbar.css").toExternalForm());
        initComponents();
    }

    private void initComponents() {
        // TODO imageButtons, change styles
        ImageView smileImg = new ImageView(SMILE_IMAGE);
        ImageView statusImg = new ImageView(STATUS_IMAGE);

        smileImg.setFitHeight(31);
        smileImg.setFitWidth(31);
        statusImg.setFitHeight(31);
        statusImg.setFitWidth(31);

        Button smilesBtn = new Button("", smileImg);
//        smilesBtn.setMaxSize(24, 24);
        smilesBtn.setMinSize(32, 32);
        Button statusBtn = new Button("", statusImg);
//        statusBtn.setMaxSize(24, 24);
        statusBtn.setMinSize(32, 32);

        smileButtonAction(smilesBtn);
        statusBtnAction(statusBtn);

        toolbar = new ToolBar(smilesBtn, statusBtn);
        toolbar.setId("mvtoolbar"); // messageView toolbar id
        toolbar.setOrientation(Orientation.HORIZONTAL);

        getChildren().add(toolbar);
    }

    private void smileButtonAction(Button smileBtn) {
        smileBtn.setOnAction(event -> Dialogs.showMessageBox("Smiles", "Work in progress", Dialogs.IconType.INFO));
    }

    private void statusBtnAction(Button statusBtn) {
        statusBtn.setOnAction(event -> Dialogs.showMessageBox("Statuses", "Work in progress", Dialogs.IconType.INFO));
    }
}
