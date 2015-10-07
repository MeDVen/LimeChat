package org.klaptech.limechat.client;

import static java.util.logging.Logger.getLogger;






import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.klaptech.limechat.client.gui.components.sconnector.ServerConnectorView;

/**
 * Main window of LimeChat with application flow.
 * Created by MeDVen on 23.08.2015.
 */
public class ApplicationWindow extends Application {
    private static final Logger LOGGER = getLogger(ApplicationWindow.class.getName());

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage initStage) throws Exception {
//        new ProgressDialog().show();
//        Stage stage = new Stage();
//        ServerConnectorView serverConnectorView = new ServerConnectorView();
//        stage.setScene(new Scene(serverConnectorView.getPanel(),500,500));
//        stage.show();
//        GUIUtils.centerStage(stage);
//        serverConnectorView.setType(ConnectionType.SUCCESS);

        initStage.setScene(new Scene(new Group(new ServerConnectorView()), 300, 300));
        initStage.show();/*
        GUIManager.getInstance().setMainStage(initStage);
//        GUIManager.getInstance().getLoginDialog().show();
        GUIManager.getInstance().showMainStage();
        PropertyManager instance = PropertyManager.INSTANCE;*/
    }

}