package org.klaptech.limechat.client.gui.dialogs;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.klaptech.limechat.client.gui.components.sconnector.ConnectionType;
import org.klaptech.limechat.client.gui.components.sconnector.ServerConnectorView;

import java.util.ResourceBundle;

/**
 * Dialog using to setup servers list and connect to selected server
 *
 * @author rlapin
 */
public class ServerConnectorDialog implements Dialog {
    private final ServerConnectorView serverConnectorView;
    private Stage stage;

    public ServerConnectorDialog(Stage parent) {
        stage = new Stage();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(getClass().getCanonicalName());
        stage.setTitle(resourceBundle.getString("dialogTitle"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(parent);
        serverConnectorView = new ServerConnectorView();
        stage.setScene(new Scene(new Group(serverConnectorView), 500, 500));
    }

    @Override
    public void show() {
        stage.show();
    }

    @Override
    public void hide() {
        stage.hide();
    }

    @Override
    public void setTitle(String title) {
        throw new UnsupportedOperationException("set title not supported by serverconnectordialog");
    }

    /**
     * Invoked on connection success
     */
    public void connectionSucceed() {
        serverConnectorView.onConnect(ConnectionType.SUCCESS);
    }

    /**
     * Invoked on connection failed
     */
    public void connectionFailed() {
        serverConnectorView.onConnect(ConnectionType.FAIL);
    }
}
