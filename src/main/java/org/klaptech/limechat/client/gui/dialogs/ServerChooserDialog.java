package org.klaptech.limechat.client.gui.dialogs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.klaptech.limechat.client.net.ServerInfo;
import org.klaptech.limechat.client.utils.GUIUtils;

/**
 * Dialog for selecting chat server
 *
 * @author rlapin
 */
public class ServerChooserDialog {
    private final Group root;
    ObservableList<ServerInfo> servers = FXCollections.observableArrayList();
    private final Stage stage;

    public ServerChooserDialog() {
        stage = new Stage(StageStyle.UTILITY);
        root = new Group();
        stage.setScene(new Scene(root, 300, 300));
        initComponents();
        generateTestData();
    }

    private void generateTestData() {
        servers.add(new ServerInfo("127.0.0.1", 6234, "Localhost server"));
    }
    private void initComponents() {
        ComboBox<ServerInfo> serversComboBox = new ComboBox<>(servers);
        serversComboBox.setCellFactory(new Callback<ListView<ServerInfo>, ListCell<ServerInfo>>() {
            @Override
            public ListCell<ServerInfo> call(ListView<ServerInfo> param) {
                ListCell<ServerInfo> cell = new ListCell<ServerInfo>() {
                    @Override
                    protected void updateItem(ServerInfo item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getName());
                        }
                    }
                };
                return cell;
            }
        });
        root.getChildren().addAll(serversComboBox);
    }

    /**
     * Show dialog
     */
    public void show() {
        stage.show();
        GUIUtils.centerStage(stage);
    }

    /**
     * Hide dialog
     */
    public void hide() {
        stage.hide();
    }
}
