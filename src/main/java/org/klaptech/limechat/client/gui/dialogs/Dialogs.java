package org.klaptech.limechat.client.gui.dialogs;

import java.util.ResourceBundle;

import org.klaptech.limechat.client.utils.GUIUtils;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * MessageBox , alertbox, inputbox etc
 *
 * @author rlapin
 */
public class Dialogs {
    private static final Image DIALOG_ICON = new Image(Dialogs.class.getClassLoader().getResourceAsStream("images/limechat_64x64.png"));
    private static final Image INFO_ICON = new Image(Dialogs.class.getClassLoader().getResourceAsStream("images/info.png"));
    private static final Image ERROR_ICON = new Image(Dialogs.class.getClassLoader().getResourceAsStream("images/error.png"));
    private static final Image QUESTION_ICON = new Image(Dialogs.class.getClassLoader().getResourceAsStream("images/question.png"));

    public static void showMessageBox(String title, String message, IconType type) {
        Stage dialogStage = new Stage(StageStyle.DECORATED);
        dialogStage.setIconified(false);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("org.klaptech.limechat.client.gui.dialogs.Dialogs");
        dialogStage.setResizable(false);
        dialogStage.getIcons().add(DIALOG_ICON);
        dialogStage.setTitle(title);
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        dialogStage.setScene(new Scene(vbox, 400, 100));
        ImageView iconImage = new ImageView(type.getIcon());
        iconImage.setFitHeight(30);
        iconImage.setFitWidth(30);
        vbox.getChildren().add(iconImage);
        vbox.getChildren().add(new Label(message));
        Button okButton = new Button(resourceBundle.getString("okbutton"));
        okButton.setOnAction(event -> dialogStage.hide());
        vbox.getChildren().add(okButton);
        dialogStage.show();
        GUIUtils.centerStage(dialogStage);
    }

    /**
     * Icon for different dialog types , can return imageicon
     *
     * @author rlapin
     */
    public enum IconType {
        INFO(INFO_ICON), ERROR(ERROR_ICON), QUESTION(QUESTION_ICON);


        private final Image questionIcon;

        IconType(Image questionIcon) {

            this.questionIcon = questionIcon;
        }

        public Image getIcon() {
            return questionIcon;
        }
    }
}
