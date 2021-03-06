package org.klaptech.limechat.client;

import javafx.application.Application;
import javafx.stage.Stage;
import org.klaptech.limechat.client.gui.GUIManager;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

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
        GUIManager.getInstance().setMainStage(initStage);
//        new ProgressDialog().show();
//        Stage stage = new Stage();
//        ServerConnectorView serverConnectorView = new ServerConnectorView();
//        stage.setScene(new Scene(serverConnectorView.getPanel(),500,500));
//        stage.show();
//        GUIUtils.centerStage(stage);
//        serverConnectorView.setType(ConnectionType.SUCCESS);
//        LoginDialog loginDialog = new LoginDialog();
//        loginDialog.show();
        GUIManager.getInstance().getServerConnectorDialog().show();
       /* UserInfo user = new UserInfo("TEST USER", UserState.ONLINE, null);
        user.getDefaultRooms().add(new ChatRoom("dasd"));
        GUIManager.getInstance().setUserInfo(user);
        GUIManager.getInstance().showChatWindow();*/

//       GUIManager.getInstance().setMainStage(initStage);
//        GUIManager.getInstance().getLoginDialog().show();
//        GUIManager.getInstance().showChatWindow();
//        PropertyManager instance = PropertyManager.INSTANCE;
    }

}