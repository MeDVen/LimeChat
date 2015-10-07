package org.klaptech.limechat.client.gui.components.sconnector;

import static java.util.logging.Logger.getLogger;






import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.klaptech.limechat.client.gui.dialogs.ServerChooserDialog;
import org.klaptech.limechat.client.net.ServerConnector;
import org.klaptech.limechat.client.net.ServerInfo;

/**
 * Panel with status of connection to server, refresh button and connection settings dialog
 * @author rlapin
 */
public class ServerConnectorView{
    private static final Logger LOGGER = getLogger(ServerConnectorView.class.getName());
    private ConnectionType type;
    private ComboBox<ServerInfo> serversComboBox;
    private ToggleButton connectToggleButton;
    private Button addServerBtn;
    /**
     * Label with connection state information
     */
    private Label infoLabel;
    /**
     * Button for refresh connection
     */
    private Button refreshButton;
    /**
     * Button for show connection settings dialog
     */
    private Button settingsButton;
    private HBox panel;

    public ServerConnectorView() {
        initComponents();
        initListeners();
    }

    private void initListeners() {
        refreshButton.setOnAction(event -> Executors.newSingleThreadExecutor().execute(this::tryConnect));
        settingsButton.setOnAction(event -> showServerChooserDialog());
    }

    private void showServerChooserDialog() {
        ServerChooserDialog serverChooserDialog = new ServerChooserDialog();
        serverChooserDialog.show();
    }

    /**
     * Try connect to server and set info label
     */
    private void tryConnect() {
        try {
            ServerConnector.INSTANCE.connect("127.0.0.132", 3123);
            setType(ConnectionType.SUCCESS);
        } catch (IOException e) {
            LOGGER.severe("cannot connect to server");
            setType(ConnectionType.FAIL);
        }
    }

    private void initComponents() {
        panel = new HBox(5);
        panel.setAlignment(Pos.CENTER_RIGHT);
        panel.getStyleClass().add("serverConnectorPanel");
        panel.getStylesheets().add(getClass().getClassLoader().getResource("fxml/serverconnectorview.css").toExternalForm());
        serversComboBox = createServersComboBox();
        panel.getChildren().addAll(refreshButton, settingsButton);


    }

    private ComboBox<ServerInfo> createServersComboBox() {
        //  List<ServerInfo> servers = PropertyManager.INSTANCE.getServerList();
        return null;
    }

    /**
     * Creates imageview using path
     * @param path
     * @return ImageView
     */
    private ImageView createImageView(String path) {
        ImageView refreshImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(path)));
        refreshImageView.setFitWidth(30);
        refreshImageView.setFitHeight(30);
        return refreshImageView;
    }

    /**
     * Set connection type and set connection info label id
     *
     * @param type
     */
    public void setType(ConnectionType type) {
        this.type = type;
        infoLabel.setId(type.getCssID());

    }

    /**
     *
     * @return component to add as panel
     */
    public HBox getPanel() {
        return panel;
    }
}
