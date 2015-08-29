package org.klaptech.limechat.client;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
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

import java.io.IOException;

/**
 * Main window of LimeChat with application flow.
 * Created by MeDVen on 23.08.2015.
 */
public class ApplicationWindow extends Application {

    private static final int SPLASH_DURATION = 5;
    @FXML
    private Label testLabel;

    private static final String LIME_CHAT_ICON_64x64 = "could_put_icon_here_64x64";
    private static final String LIME_CHAT_SPLASH = "limes_transperent_splash.png";

    private static final int SPLASH_WIDTH = 800;
    private static final int SPLASH_HEIGHT = 600;

    private Pane splashLayout;
    private Label splashMessage;

    private Stage mainStage;

    @Override
    public void start(Stage initStage) throws Exception {
        showSplashScreen(initStage);
        initStage.toFront();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(SPLASH_DURATION), e -> showMainStage()));
        timeline.play();
        FadeTransition fadeSplash = new FadeTransition(Duration.seconds(SPLASH_DURATION), splashLayout);
        fadeSplash.setFromValue(1.0);
        fadeSplash.setToValue(0.0);
        fadeSplash.setOnFinished(actionEvent -> initStage.hide());
        fadeSplash.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        ImageView splashImage = new ImageView(new Image(getClass().getResourceAsStream(LIME_CHAT_SPLASH)));
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
    private void showMainStage(){
        mainStage = new Stage(StageStyle.DECORATED);
        mainStage.setTitle("Lime Chat beta v.0.0.1");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("limechat.fxml"));
            Scene scene = new Scene(root, 800, 582);
            mainStage.setScene(scene);
            mainStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * show splash screen
     * @param initStage main stage
     */
    private void showSplashScreen(Stage initStage) {
        Scene splashScene = new Scene(splashLayout);
        splashScene.setFill(Color.TRANSPARENT);
        initStage.initStyle(StageStyle.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        initStage.show();
    }

    public void showText(ActionEvent actionEvent) {
        testLabel.setText("Test green color theme");
    }
}
