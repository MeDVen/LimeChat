package org.klaptech.limechat.client.gui.dialogs;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.klaptech.limechat.client.utils.GUIUtils;

import java.util.ResourceBundle;

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

    public static void showMessageBox(Stage parent, String title, String message, IconType type) {
        Stage dialogStage = new Stage(StageStyle.DECORATED);
        dialogStage.setIconified(false);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(parent);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Dialogs.class.getCanonicalName());
        dialogStage.setResizable(false);
        dialogStage.getIcons().add(DIALOG_ICON);
        dialogStage.setTitle(title);
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 400, 100);
        GUIUtils.addCss(scene, "fxml/dialogs.css");
        dialogStage.setScene(scene);
        ImageView iconImage = new ImageView(type.getIcon());
        iconImage.setFitHeight(30);
        iconImage.setFitWidth(30);
        vbox.getChildren().add(iconImage);
        vbox.getChildren().add(new Label(message));
        Button okButton = new Button(resourceBundle.getString("okButton"));
        okButton.setOnAction(event -> dialogStage.hide());
        vbox.getChildren().add(okButton);
        dialogStage.show();
        GUIUtils.centerStage(dialogStage);
    }

    /**
     * Creates standard confirmation dialog with yes and no buttons.
     *
     * @param parent
     * @param title
     * @param message
     * @return true if user click yes button
     */
    public static void showConfirmBox(Stage parent, String title, String message, DialogListener listener) {
        Stage dialogStage = new Stage(StageStyle.DECORATED);
        dialogStage.setIconified(false);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(parent);
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Dialogs.class.getCanonicalName());
        dialogStage.setResizable(false);
        dialogStage.getIcons().add(DIALOG_ICON);
        dialogStage.setTitle(title);
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 400, 100);
        GUIUtils.addCss(scene, "fxml/dialogs.css");
        dialogStage.setScene(scene);
        ImageView iconImage = new ImageView(QUESTION_ICON);
        iconImage.setFitHeight(30);
        iconImage.setFitWidth(30);
        vbox.getChildren().add(iconImage);
        vbox.getChildren().add(new Label(message));

        Button yesButton = new Button(resourceBundle.getString("yesButton"));
        Button noButton = new Button(resourceBundle.getString("noButton"));
        yesButton.setOnAction(event -> {

            listener.onOK();
            dialogStage.hide();

        });
        dialogStage.setOnCloseRequest(event -> listener.onCancel());
        noButton.setOnAction(event -> {
            listener.onCancel();
            dialogStage.hide();
        });
        HBox buttonLane = new HBox(5, yesButton, noButton);
        buttonLane.setAlignment(Pos.CENTER_RIGHT);
        vbox.getChildren().add(buttonLane);
        dialogStage.show();
        GUIUtils.centerStage(dialogStage);
    }

    //TODO add input dialog

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
