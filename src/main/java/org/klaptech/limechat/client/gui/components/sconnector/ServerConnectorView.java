package org.klaptech.limechat.client.gui.components.sconnector;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.klaptech.limechat.client.gui.GUIManager;
import org.klaptech.limechat.client.gui.dialogs.DialogListener;
import org.klaptech.limechat.client.gui.dialogs.Dialogs;
import org.klaptech.limechat.client.gui.dialogs.dataeditor.ServerDataEditorDialog;
import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.ServerEditorEntity;
import org.klaptech.limechat.client.net.ServerConnector;
import org.klaptech.limechat.client.net.ServerInfo;
import org.klaptech.limechat.client.utils.GUIUtils;
import org.klaptech.limechat.client.utils.PropertyManager;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
    private final ResourceBundle resourceBundle;
    private ConnectionType type;
    private ComboBox<ServerInfo> serversComboBox;
    /**
     * Connection button
     */
    private ToggleButton connectToggleButton;
    private Button addServerBtn;
    private Button editServerBtn;
    private Button removeServerBtn;

    public ServerConnectorView() {
        super(SPACING);
        resourceBundle = ResourceBundle.getBundle(this.getClass().getCanonicalName());
        setAlignment(Pos.CENTER_RIGHT);
        setId("serverConnectorPanel");
        initComponents();
        initListeners();
        updateComboBox();
        serversComboBox.getSelectionModel().selectFirst();
    }

    private void initListeners() {
        addServerBtn.setOnAction(event -> addServerAction());
        editServerBtn.setOnAction(event -> editServerAction());
        removeServerBtn.setOnAction(event -> removeServerAction());
        connectToggleButton.setOnAction(event -> connectAction());
        ObservableList<ServerInfo> items = serversComboBox.getItems();
        items.addListener((ListChangeListener<ServerInfo>) c -> {
                    updateComboBox();
                }
        );
        serversComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {

        });
    }

    /**
     * invoke when need to disable/enable edit controls and select value in cmb
     */
    private void updateComboBox() {
        ObservableList<ServerInfo> items = serversComboBox.getItems();
        editServerBtn.setDisable(items.isEmpty());
        removeServerBtn.setDisable(items.isEmpty());
    }

    private void removeServerAction() {
        Dialogs.showConfirmBox(GUIManager.getInstance().getMainStage(), resourceBundle.getString("deleteTitle"), resourceBundle.getString("deleteText"), new DialogListener() {
            @Override
            public void onOK() {
                serversComboBox.getItems().remove(serversComboBox.getValue());
                serversComboBox.getSelectionModel().selectNext();
            }
        });
    }

    private void editServerAction() {
        ServerInfo selectedItem = serversComboBox.getValue();
        ServerEditorEntity serverEntity = new ServerEditorEntity();
        serverEntity.setName(selectedItem.getName());
        serverEntity.setAddress(selectedItem.getAddr());
        ServerDataEditorDialog dataDialog = new ServerDataEditorDialog(serverEntity, GUIManager.getInstance().getMainStage());
        dataDialog.initServersList(getServersNameList());
        dataDialog.setTitle(resourceBundle.getString("editServerDlgTitle"));
        dataDialog.show();
        dataDialog.setDialogListener(new DialogListener() {
            @Override
            public void onOK() {
                selectedItem.setName(dataDialog.getServerInfo().getName());
                selectedItem.setAddr(dataDialog.getServerInfo().getAddress());
                serversComboBox.getItems().set(serversComboBox.getItems().indexOf(selectedItem), selectedItem);

                LOGGER.info("Server " + selectedItem + " successfully edit");

            }

            @Override
            public void onCancel() {
                LOGGER.info("No server will be added as result of cancel event");
            }
        });
    }

    private void connectAction() {
        ServerInfo selServer = serversComboBox.getValue();
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
        ServerDataEditorDialog dataDialog = new ServerDataEditorDialog(new ServerEditorEntity(), GUIManager.getInstance().getMainStage());
        ObservableList<ServerInfo> items = serversComboBox.getItems();
        dataDialog.initServersList(getServersNameList());
        dataDialog.setTitle(resourceBundle.getString("addServerDlgTitle"));
        dataDialog.show();
        dataDialog.setDialogListener(new DialogListener() {
            @Override
            public void onOK() {
                ServerInfo serverInfo = new ServerInfo(dataDialog.getServerInfo().getAddress(), dataDialog.getServerInfo().getName());
                items.add(serverInfo);
                serversComboBox.getSelectionModel().selectLast();
                LOGGER.info("Server " + serverInfo + " successfully added");
            }

            @Override
            public void onCancel() {
                LOGGER.info("No server will be added as result of cancel event");
            }
        });

    }

    private List<String> getServersNameList() {
        return serversComboBox.getItems().stream().map(ServerInfo::getName).collect(Collectors.toList());
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
        addServerBtn = createButton("images/add.png");
        editServerBtn = createButton("images/edit.png");
        removeServerBtn = createButton("images/remove.png");
        getChildren().addAll(serversComboBox, connectToggleButton, addServerBtn, editServerBtn, removeServerBtn);
        initLayout();

    }

    private Button createButton(String path) {
        Button btn = new Button();
        btn.setGraphic(createImageView(path));
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
