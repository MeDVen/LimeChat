package org.klaptech.limechat.client.gui.components.sconnector;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


/**
 * Panel with status of connection to server, refresh button and connection settings dialog
 * @author rlapin
 */
public class ServerConnectorView{
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
    private Button showConSettingsButton;
    private HBox panel;

    public ServerConnectorView() {
        initComponents();
    }

    private void initComponents() {
        panel = new HBox(5);
        infoLabel = new Label("Connection succeed");
        panel.getChildren().add(infoLabel);
        refreshButton = new Button();
        ImageView refreshImageView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/refresh.png")));
        refreshButton.setGraphic(refreshImageView);

        panel.getChildren().add(refreshButton);
    }

    /**
     *
     * @return component to add as panel
     */
    public HBox getPanel() {
        return panel;
    }
}
