package org.klaptech.limechat.client.gui.components.sconnector;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.klaptech.limechat.client.gui.dialogs.ServerChooserDialog;
import org.klaptech.limechat.client.net.ServerConnector;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;


/**
 * Panel with status of connection to server, refresh button and connection settings dialog
 * @author rlapin
 */
public class ServerConnectorView{
    private static final Logger LOGGER = getLogger(ServerConnectorView.class.getName());
    private ConnectionType type;
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
        infoLabel = new Label("Connection succeed");
        setType(ConnectionType.NOT_CONNECTED);
        panel.getChildren().add(infoLabel);
        ImageView refreshImageView = createImageView("images/refresh.png");
        refreshButton = new Button();
        refreshButton.setGraphic(refreshImageView);
        refreshButton.setId("refresh-button");
        ImageView settingsImageView = createImageView("images/settings.png");
        settingsButton = new Button();
        settingsButton.setGraphic(settingsImageView);
        settingsButton.setId("settings-button");
        panel.getChildren().addAll(refreshButton, settingsButton);


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
