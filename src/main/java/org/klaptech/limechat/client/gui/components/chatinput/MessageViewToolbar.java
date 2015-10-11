package org.klaptech.limechat.client.gui.components.chatinput;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.klaptech.limechat.client.gui.GUIManager;
import org.klaptech.limechat.client.gui.dialogs.Dialogs;
import org.klaptech.limechat.client.utils.GUIConstants;
import org.klaptech.limechat.client.utils.GUIUtils;

/**
 * @author MeDVen
 */
public class MessageViewToolbar extends HBox {
    private ToolBar toolbar;

    public MessageViewToolbar() {
        GUIUtils.addCss(this, "fxml/messageviewtoolbar.css");
        initComponents();
    }

    private void initComponents() {
        ImageView smileImg = new ImageView(GUIConstants.SMILE_IMAGE);
        ImageView statusImg = new ImageView(GUIConstants.ONLINE_IMAGE);

        smileImg.setFitHeight(31);
        smileImg.setFitWidth(31);
        statusImg.setFitHeight(31);
        statusImg.setFitWidth(31);

        Button smilesBtn = new Button("", smileImg);
        smilesBtn.setMinSize(32, 32);
        Button statusBtn = new Button("", statusImg);
        statusBtn.setMinSize(32, 32);

        smileButtonAction(smilesBtn);
        statusBtnAction(statusBtn);

        toolbar = new ToolBar(smilesBtn, statusBtn);
        toolbar.setId("mvtoolbar");
        toolbar.setOrientation(Orientation.HORIZONTAL);

        getChildren().add(toolbar);
    }

    private void smileButtonAction(Button smileBtn) {
        smileBtn.setOnAction(event -> Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), "Smiles", "Work in progress", Dialogs.IconType.INFO));
    }

    private void statusBtnAction(Button statusBtn) {
        statusBtn.setOnAction(event -> Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), "Statuses", "Work in progress", Dialogs.IconType.INFO));
    }
}
