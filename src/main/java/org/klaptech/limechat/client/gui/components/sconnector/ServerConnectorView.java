package org.klaptech.limechat.client.gui.components.sconnector;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.klaptech.limechat.client.gui.dialogs.Dialogs;
import org.klaptech.limechat.client.net.ServerAddress;
import org.klaptech.limechat.client.net.ServerConnector;
import org.klaptech.limechat.client.net.ServerInfo;
import org.klaptech.limechat.client.utils.GUIUtils;
import org.klaptech.limechat.client.utils.PropertyManager;

import java.io.IOException;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Panel with status of connection to server, refresh button and connection settings dialog
 * For styling node with css use:<br>
 * <ul>
 * <li>Root component id: serverConnectorPanel</li>
 * <li>Connect Button id: connectButton</li>
 * <li>Servers list combobox id: serversListCmb</li>
 * </ul>
 *
 * @author rlapin
 */
public class ServerConnectorView extends HBox {
    public static final int SPACING = 5;
    private static final Logger LOGGER = getLogger(ServerConnectorView.class.getName());
    private ConnectionType type;
    private ComboBox<ServerInfo> serversComboBox;
    /**
     * Connection button
     */
    private ToggleButton connectToggleButton;
    private Button addServerBtn;

    public ServerConnectorView() {
        super(SPACING);
        setAlignment(Pos.CENTER_RIGHT);
        setId("serverConnectorPanel");
        initComponents();
        initListeners();
    }

    private void initListeners() {
        addServerBtn.setOnAction(event -> addServerAction());
        connectToggleButton.setOnAction(event -> connectAction());
    }

    private void connectAction() {
        ServerInfo selServer = serversComboBox.getSelectionModel().getSelectedItem();
        if (selServer != null) {
            try {
                ServerConnector.INSTANCE.connect(selServer.getAddr().getAddr(), selServer.getAddr().getPort());
            } catch (IOException e) {
                LOGGER.severe("cannot connect to server " + selServer.getName() + " " + selServer.getAddr());
            }
        }
    }

    private void addServerAction() {
        // SHOW INPUT DIALOG
        ObservableList<ServerInfo> items = serversComboBox.getItems();
        ServerInfo mock = new ServerInfo(new ServerAddress("testaddr", 32), "TestServer");
        // Contains depends on server name
        if (!items.contains(mock)) {
            items.add(mock);
        } else {
            Dialogs.showMessageBox("Error while adding server", "Server is already existed", Dialogs.IconType.ERROR);
        }
    }

    /**
     * Try connect to server and set info label
     *//*
    private void tryConnect() {

    }*/

    /**
     * Create components and setup layout
     */
    private void initComponents() {

        GUIUtils.addCss(this, "fxml/serverconnectorview.css");
        serversComboBox = createServersComboBox();
        connectToggleButton = createConnectButton();
        addServerBtn = createAddServerButton();
        getChildren().addAll(serversComboBox, connectToggleButton, addServerBtn);
        initLayout();

    }

    private Button createAddServerButton() {
        Button btn = new Button();
        btn.setGraphic(createImageView("images/add.png"));
        return btn;
    }

    /**
     * Bind size and position properties of components
     */
    private void initLayout() {

    }

    private ToggleButton createConnectButton() {
        ToggleButton button = new ToggleButton();
        button.setGraphic(createImageView("images/connect.png"));
        button.setId("connectButton");
        return button;
    }

    /**
     * Create combobox with servers list<br>
     * Also fill serverslist with data from property file
     *
     * @return combobox
     */
    private ComboBox<ServerInfo> createServersComboBox() {

        ComboBox<ServerInfo> comboBox = new ComboBox<>(PropertyManager.INSTANCE.getProperties().getServerList());
        comboBox.setId("serversListCmb");
        return comboBox;
    }

    /**
     * Creates imageview using path
     *
     * @param path
     * @return ImageView
     */
    private ImageView createImageView(String path) {
        ImageView refreshImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(path)));
        refreshImageView.setFitWidth(30);
        refreshImageView.setFitHeight(30);
        return refreshImageView;
    }

}
