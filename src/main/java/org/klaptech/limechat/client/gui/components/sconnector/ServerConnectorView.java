package org.klaptech.limechat.client.gui.components.sconnector;

import static java.util.logging.Logger.getLogger;






import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.klaptech.limechat.client.net.ServerConnector;
import org.klaptech.limechat.client.net.ServerInfo;
import org.klaptech.limechat.client.utils.GUIUtils;
import org.klaptech.limechat.client.utils.PropertyManager;

/**
 * Panel with status of connection to server, refresh button and connection settings dialog
 *
 * @author rlapin
 */
public class ServerConnectorView extends HBox {
    public static final int SPACING = 5;
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

    public ServerConnectorView() {
        super(SPACING);
        setAlignment(Pos.CENTER_RIGHT);
        setId("serverConnectorPanel");
        initComponents();
        initListeners();
    }

    private void initListeners() {
       /* refreshButton.setOnAction(event -> Executors.newSingleThreadExecutor().execute(this::tryConnect));*/

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

        GUIUtils.addCss(this, "fxml/serverconnectorview.css");
        serversComboBox = createServersComboBox();
        getChildren().addAll(serversComboBox);

    }

    private ComboBox<ServerInfo> createServersComboBox() {
        List<ServerInfo> servers = PropertyManager.INSTANCE.getProperties().getServerList();

        ComboBox<ServerInfo> comboBox = new ComboBox<>(FXCollections.observableArrayList(servers));
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

    /**
     * Set connection type and set connection info label id
     *
     * @param type
     */
    public void setType(ConnectionType type) {
        this.type = type;
        infoLabel.setId(type.getCssID());

    }

}
