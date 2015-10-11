package org.klaptech.limechat.client.gui.dialogs;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.klaptech.limechat.client.utils.GUIUtils;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Dialog with progress indicator
 *
 * @author rlapin
 */
public class ProgressDialog implements Dialog {
    private final static Logger LOGGER = getLogger(ProgressDialog.class.getName());
    private Stage stage;

    public ProgressDialog(Stage parent) {
        stage = new Stage(StageStyle.TRANSPARENT);
        ProgressIndicator progressIndicator = new ProgressIndicator();
        Group group = new Group(progressIndicator);
        stage.initOwner(parent);
        Scene scene = new Scene(group, Color.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        GUIUtils.addCss(scene, "fxml/progressdialog.css");
    }

    /**
     * Show progress dialog
     */
    @Override
    public void show() {
        stage.show();
        GUIUtils.centerStage(stage);
    }

    /**
     * Hide progress dialog
     */
    @Override
    public void hide() {
        stage.hide();
    }

    @Override
    public void setTitle(String title) {
        throw new UnsupportedOperationException("Set title not supported by progress dialog as it undecorated");
    }

}
