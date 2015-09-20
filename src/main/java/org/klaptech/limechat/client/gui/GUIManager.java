package org.klaptech.limechat.client.gui;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import org.klaptech.limechat.client.gui.components.ChatTabPane;
import org.klaptech.limechat.client.gui.components.chatroom.RoomTab;
import org.klaptech.limechat.client.gui.dialogs.LoginDialog;

/**
 * Contains all gui entities
 * Allow to show splash screen/different forms
 *
 * @author rlapin
 */
public class GUIManager {
    private static GUIManager instance = new GUIManager();
    private Stage mainStage;
    private static final String LIME_CHAT_ICON_64x64 = "images/limechat_64x64.png";
    private static final String LIME_CHAT_SPLASH = "images/limes_transperent_splash.png";
    private static final int SPLASH_DURATION = 5;
    public static final String APP_TITLE = "Lime Chat alpha v.0.0.1";
    private static final int SPLASH_WIDTH = 800;
    private static final int SPLASH_HEIGHT = 600;
    private LoginDialog loginDialog;
    private Pane splashLayout;
    private Label splashMessage;


    public static GUIManager getInstance() {
        return instance;
    }

    private GUIManager() {
        loginDialog = new LoginDialog();
        initSplashScreen();
    }

    private void initSplashScreen() {
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


    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    /**
     * Init and show main stage
     */
    private void showMainStage() {
        mainStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(LIME_CHAT_ICON_64x64)));
        mainStage.setTitle(APP_TITLE);
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600);
        ChatTabPane tabPane = new ChatTabPane();
        tabPane.addNewTab(new RoomTab("Room 1"));
        tabPane.addNewTab(new RoomTab("Room 2"));
        tabPane.addNewTab(new RoomTab("Room 3"));
        root.getChildren().add(tabPane);
        mainStage.setScene(scene);
        mainStage.show();
    }

    /**
     * show splash screen
     */
    public void showSplashScreen() {
        Scene splashScene = new Scene(splashLayout);
        splashScene.setFill(Color.TRANSPARENT);
        Stage splashStage = new Stage();
        splashStage.initStyle(StageStyle.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        splashStage.setScene(splashScene);
        splashStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        splashStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        splashStage.show();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(SPLASH_DURATION), e -> showMainStage()));
        timeline.play();
        FadeTransition fadeSplash = new FadeTransition(Duration.seconds(SPLASH_DURATION), splashLayout);
        fadeSplash.setFromValue(1.0);
        fadeSplash.setToValue(0.0);
        fadeSplash.setOnFinished(actionEvent -> splashStage.hide());
        fadeSplash.play();

    }

    public LoginDialog getLoginDialog() {
        return loginDialog;
    }
}
