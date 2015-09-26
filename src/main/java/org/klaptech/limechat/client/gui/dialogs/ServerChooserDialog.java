package org.klaptech.limechat.client.gui.dialogs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.klaptech.limechat.client.net.ServerInfo;
import org.klaptech.limechat.client.utils.GUIUtils;

/**
 * Dialog for selecting chat server
 *
 * @author rlapin
 */
public class ServerChooserDialog {
    ObservableList<ServerInfo> servers = FXCollections.observableArrayList();
    private final Stage stage;

    public ServerChooserDialog() {
        stage = new Stage();
        initComponents();
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
    }

    /**
     * Show dialog
     */
    public void show() {
        stage.show();
        System.out.println(stage.getX());
        GUIUtils.centerStage(stage);
    }

    /**
     * Hide dialog
     */
    public void hide() {
        stage.hide();
    }
}
