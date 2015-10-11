package org.klaptech.limechat.client.gui;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.klaptech.limechat.client.gui.components.MainView;
import org.klaptech.limechat.client.gui.dialogs.LoginDialog;
import org.klaptech.limechat.client.gui.dialogs.ServerConnectorDialog;
import org.klaptech.limechat.shared.entities.UserInfo;

/**
 * Contains all gui entities
 * Allow to show splash screen/different forms
 *
 * @author rlapin
 */
public class GUIManager {
    private static GUIManager instance = new GUIManager();
    private final ServerConnectorDialog serverConnectorDialog;
    private Stage mainStage;
    private static final String LIME_CHAT_ICON_64x64 = "images/limechat_64x64.png";
    private static final String LIME_CHAT_SPLASH = "images/limes_transperent_splash.png";
    private static final int SPLASH_DURATION = 7;
    public static final String APP_TITLE = "Lime Chat alpha v.0.0.1";
    private static final int SPLASH_WIDTH = 800;
    private static final int SPLASH_HEIGHT = 600;
    private final LoginDialog loginDialog;
    private Pane splashLayout;
    private Label splashMessage;
    private AnchorPane splashImagePane;
    private UserInfo userInfo;

    private MainView mainView;


    public static GUIManager getInstance() {
        return instance;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private GUIManager() {
        loginDialog = new LoginDialog();
        serverConnectorDialog = new ServerConnectorDialog(mainStage);
        initSplashScreen();
    }

    private void initSplashScreen() {
        splashMessage = new Label("Welcome to LimeChat!");
        splashMessage.setAlignment(Pos.CENTER);

        ImageView splashImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(LIME_CHAT_SPLASH)));
        splashImagePane = new AnchorPane(splashImage);
        InnerShadow is = new InnerShadow();
        is.setOffsetX(3);
        is.setOffsetY(3);
        is.setColor(Color.GREENYELLOW);
        splashMessage.setEffect(is);
        splashMessage.setStyle("-fx-text-fill: black; -fx-font-size: 25pt");
        splashMessage.setFont(Font.font("Times New Roman", FontWeight.BOLD, 32));
        splashImagePane.getChildren().addAll(progressEllipses);
        for (Ellipse ellipse : progressEllipses) {
            ellipse.setFill(Color.valueOf("#96b946"));
            ellipse.setVisible(false);
        }
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splashImagePane, splashMessage);
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
    // TODO changed to private
    public void showChatWindow() {
        mainStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream(LIME_CHAT_ICON_64x64)));
        mainStage.setTitle(APP_TITLE);

        mainView = new MainView();
        Scene scene = new Scene(mainView, 800, 600);

        mainStage.setScene(scene);
        mainStage.show();
    }

    /**
     * show splash screen with progress effect and fade out
     */
    public void showSplashScreen() {
        Scene splashScene = new Scene(splashLayout);
        splashScene.setFill(Color.TRANSPARENT);
        Stage splashStage = new Stage();
        splashStage.initStyle(StageStyle.TRANSPARENT);
        splashStage.initModality(Modality.WINDOW_MODAL);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        splashStage.setScene(splashScene);
        splashStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        splashStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        splashStage.show();
        final int[] count = {0};
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), e -> progress(count[0]++)));
        timeline.setCycleCount(SPLASH_DURATION);
        timeline.play();

        FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1), splashLayout);
        fadeSplash.setFromValue(1.0);
        fadeSplash.setToValue(0.0);
        timeline.setOnFinished(actionEvent -> {
            fadeSplash.play();

        });
        fadeSplash.setOnFinished(event -> {
            splashStage.hide();
            showChatWindow();
        });
    }

    /**
     * Draw progress on splash
     *
     * @param value count of progress
     */
    private void progress(int value) {
        progressEllipses[value].setVisible(true);
    }

    Ellipse[] progressEllipses = new Ellipse[]{new Ellipse(135, 411, 12, 12), new Ellipse(187, 409, 14, 15), new Ellipse(248, 413, 16, 16),
            new Ellipse(301, 424, 16, 14), new Ellipse(354, 437, 14, 12), new Ellipse(408, 441, 16, 12), new Ellipse(466, 440, 16, 12)};

    public LoginDialog getLoginDialog() {
        return loginDialog;
    }

    public ServerConnectorDialog getServerConnectorDialog() {
        return serverConnectorDialog;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public MainView getMainView() {
        return mainView;
    }
}
