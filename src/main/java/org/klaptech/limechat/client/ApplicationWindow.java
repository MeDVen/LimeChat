package org.klaptech.limechat.client;

import javafx.application.Application;
import javafx.stage.Stage;
import org.klaptech.limechat.client.gui.GUIManager;
import org.klaptech.limechat.client.net.ServerConnector;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Main window of LimeChat with application flow.
 * Created by MeDVen on 23.08.2015.
 */
public class ApplicationWindow extends Application {
    private static final Logger LOGGER = getLogger(ApplicationWindow.class.getName());






    @Override
    public void start(Stage initStage) throws Exception {
        Executors.newSingleThreadExecutor().execute(() -> {

            try {
                ServerConnector.INSTANCE.connect();
            } catch (IOException e) {
                LOGGER.severe("cannot connect to server");
            }
        });
        GUIManager.getInstance().setMainStage(initStage);
        GUIManager.getInstance().getLoginDialog().show();

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

}