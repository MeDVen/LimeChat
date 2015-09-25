package org.klaptech.limechat.client.gui.dialogs;

import static java.util.logging.Logger.getLogger;






import java.util.logging.Logger;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.klaptech.limechat.client.utils.GUIUtils;

/**
 * Dialog with progress indicator
 * @author rlapin
 */
public class ProgressDialog {
    private final static Logger LOGGER = getLogger(ProgressDialog.class.getName());
    private Stage stage;

    public ProgressDialog() {
        stage = new Stage(StageStyle.TRANSPARENT);


        ProgressIndicator progressIndicator = new ProgressIndicator();
        Group group = new Group(progressIndicator);
        Scene scene = new Scene(group, Color.TRANSPARENT);

        stage.setScene(scene);
        try {
            scene.getStylesheets().add(getClass().getClassLoader().getResource("fxml/progressdialog.css").toExternalForm());
        } catch (NullPointerException e) {
            LOGGER.severe("Error while loading file: progressdialog.css");
        }

    }
    public void show(){
        stage.show();
        GUIUtils.centerStage(stage);
    }



}
