package org.klaptech.limechat.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.klaptech.limechat.client.gui.components.sconnector.ConnectionType;
import org.klaptech.limechat.client.gui.components.sconnector.ServerConnectorView;

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

        //  new ProgressDialog().show();
        Stage stage = new Stage();
        ServerConnectorView serverConnectorView = new ServerConnectorView();
        stage.setScene(new Scene(serverConnectorView.getPanel(),500,500));
        stage.show();
        serverConnectorView.setType(ConnectionType.SUCCESS);
      /*  GUIManager.getInstance().setMainStage(initStage);
        GUIManager.getInstance().getLoginDialog().show();*/
//        GUIManager.getInstance().showMainStage();

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