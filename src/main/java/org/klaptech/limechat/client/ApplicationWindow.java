package org.klaptech.limechat.client;

import static java.util.logging.Logger.getLogger;

import java.util.logging.Logger;

import org.klaptech.limechat.client.gui.GUIManager;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main window of LimeChat with application flow.
 * Created by MeDVen on 23.08.2015.
 */
public class ApplicationWindow extends Application {
    private static final Logger LOGGER = getLogger(ApplicationWindow.class.getName());

    @Override
    public void start(Stage initStage) throws Exception {
//        new ProgressDialog().show();
//        Stage stage = new Stage();
//        ServerConnectorView serverConnectorView = new ServerConnectorView();
//        stage.setScene(new Scene(serverConnectorView.getPanel(),500,500));
//        stage.show();
//        GUIUtils.centerStage(stage);
//        serverConnectorView.setType(ConnectionType.SUCCESS);

        GUIManager.getInstance().setMainStage(initStage);
//        GUIManager.getInstance().getLoginDialog().show();
        GUIManager.getInstance().showMainStage();
    }

    public static void main(String[] args) {
        launch(args);
    }

}