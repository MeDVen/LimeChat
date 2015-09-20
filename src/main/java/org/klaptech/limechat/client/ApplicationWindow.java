package org.klaptech.limechat.client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.klaptech.limechat.client.gui.components.ChatTabPane;
import org.klaptech.limechat.client.gui.components.chatroom.RoomTab;
import org.klaptech.limechat.client.gui.dialogs.LoginDialog;

/**
 * Main window of LimeChat with application flow.
 * Created by MeDVen on 23.08.2015.
 */
public class ApplicationWindow extends Application {

    private static final int SPLASH_DURATION = 5;
    public static final String APP_TITLE = "Lime Chat alpha v.0.0.1";
    @FXML
    private Label testLabel;

    private static final String LIME_CHAT_ICON_64x64 = "images/limechat_64x64.png";
    private static final String LIME_CHAT_SPLASH = "images/limes_transperent_splash.png";

    private static final int SPLASH_WIDTH = 800;
    private static final int SPLASH_HEIGHT = 600;

    private Pane splashLayout;
    private Label splashMessage;

    @Override
    public void start(Stage initStage) throws Exception {
        //initStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(LIME_CHAT_ICON_64x64)));
        //showMainStage(initStage);


        LoginDialog loginDialog = new LoginDialog();
        loginDialog.show();//showMainStage();
        //  showHTMLEditor(initStage);
    /* showSplashScreen(initStage);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(SPLASH_DURATION), e -> showMainStage()));
        timeline.play();
        FadeTransition fadeSplash = new FadeTransition(Duration.seconds(SPLASH_DURATION), splashLayout);
        fadeSplash.setFromValue(1.0);
        fadeSplash.setToValue(0.0);
        fadeSplash.setOnFinished(actionEvent -> initStage.hide());
        fadeSplash.play();*/
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        ImageView splashImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(LIME_CHAT_SPLASH)));
        splashMessage = new Label("Welcome to best chat ever - LimeChat!");
        splashMessage.setAlignment(Pos.CENTER);

        InnerShadow is = new InnerShadow();
        is.setOffsetX(3);
        is.setOffsetY(3);
        is.setColor(Color.GREENYELLOW);
        splashMessage.setEffect(is);
        splashMessage.setStyle("-fx-text-fill: black; -fx-font-size: 25pt");
        splashMessage.setFont(Font.font("Times New Roman", FontWeight.BOLD, 32));

        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splashImage, splashMessage);
//        splashLayout.setStyle("-fx-padding: 5; -fx-background-color: transparent; -fx-border-width:2; -fx-border-color: linear-gradient(to bottom, green, black);" +
//                "-fx-border-radius: 30 30 30 30;");
        splashLayout.setStyle("-fx-padding: 5; -fx-background-color: transparent;");
        splashLayout.setEffect(new DropShadow());
    }

    /**
     * Init and show main stage
     */
    private void showMainStage(Stage stage){
        stage.setTitle(APP_TITLE);
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600);
        ChatTabPane tabPane = new ChatTabPane();
        tabPane.addNewTab(new RoomTab("Room 1"));
        tabPane.addNewTab(new RoomTab("Room 2"));
        tabPane.addNewTab(new RoomTab("Room 3"));
        root.getChildren().add(tabPane);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * show splash screen
     * @param stage main stage
     */
    private void showSplashScreen(Stage stage) {
        Scene splashScene = new Scene(splashLayout);
        splashScene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        stage.setScene(splashScene);
        stage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        stage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        stage.show();
    }

    public void showText(ActionEvent actionEvent) {
        testLabel.setText("Test green color theme");
    }
}