package org.klaptech.limechat.client.gui.dialogs.dataeditor;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.klaptech.limechat.client.gui.dialogs.DialogListener;
import org.klaptech.limechat.client.gui.dialogs.Dialogs;
import org.klaptech.limechat.client.gui.dialogs.OkCancelDialog;
import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.ServerEditorEntity;
import org.klaptech.limechat.client.net.ServerAddress;
import org.klaptech.limechat.client.utils.GUIUtils;

/**
 * Utility dialog for editing or adding new data<br>
 * Styling info: <br>
 * <ul>
 * <li>use class eLabel for styling label elements</li>
 * <li>id okButton for styling ok button</li>
 * <li>id cancelButton for styling cancel button</li>
 * <li>class eLane for styling field lane</li>
 * <li>class eDialog for styling dialog</li>
 * </ul>
 *
 * @author rlapin
 */
public class ServerDataEditorDialog implements OkCancelDialog {
    private final ServerEditorEntity serverEntity;
    private Stage stage;
    private int width = 400;
    private int height = 300;
    private DialogListener listener;


    public ServerDataEditorDialog(ServerEditorEntity serverEntity, Stage parent) {
        this.serverEntity = serverEntity;
        listener = new DialogListener() {
        };
        initStage(parent);
        initComponents();

    }
    /**
     * init stage as a child of parent stage
     *
     * @param parent owner stage
     */
    private void initStage(Stage parent) {
        stage = new Stage(StageStyle.UTILITY);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(parent);
    }

    private void initComponents() {
        VBox vBox = new VBox();
        Scene scene = new Scene(vBox, width, height);
        GUIUtils.addCss(scene, "fxml/editors.css");
        stage.setScene(scene);
        HBox serverNameLane = createServerNameLane();
        HBox serverAddressLane = createServerAddressLane();
        HBox buttonLane = createButtonLane();
        vBox.getChildren().addAll(serverNameLane, serverAddressLane, buttonLane);
    }

    private HBox createServerAddressLane() {
        HBox hBox = new HBox();
        hBox.getStyleClass().add("eLane");
        Label serverLabel = createLabel("Server");
        serverLabel.prefHeightProperty().bind(hBox.heightProperty());
        Label portLabel = createLabel(":");
        portLabel.prefHeightProperty().bind(hBox.heightProperty());
        portLabel.getStyleClass().add("eLabel");
        TextField addrTextField = createTextField();
        addrTextField.prefHeightProperty().bind(hBox.heightProperty());
        TextField portTextField = createTextField();
        portTextField.prefHeightProperty().bind(hBox.heightProperty());
        portTextField.setId("eServerPortTextField");
        addrTextField.setText(serverEntity.getAddress().getAddr());
        portTextField.setText(String.valueOf(serverEntity.getAddress().getPort()));
        portTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            serverEntity.setAddress(new ServerAddress(serverEntity.getAddress().getAddr(), Integer.parseInt(newValue)));
        });
        addrTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            serverEntity.setAddress(new ServerAddress(newValue, serverEntity.getAddress().getPort()));
        });
        hBox.getChildren().addAll(serverLabel, addrTextField, portLabel, portTextField);
        return hBox;
    }

    private Label createLabel(String caption) {
        Label resultLabel = new Label(caption);
        resultLabel.getStyleClass().add("eLabel");
        return resultLabel;
    }

    private TextField createTextField() {
        TextField resultTextField = new TextField();
        resultTextField.getStyleClass().add("eTextField");
        return resultTextField;
    }

    private HBox createServerNameLane() {
        HBox hBox = new HBox();
        hBox.getStyleClass().add("eLane");
        Label label = new Label("Server name: ");
        label.getStyleClass().add("eLabel");
        label.prefHeightProperty().bind(hBox.heightProperty());
        TextField serverNameTextField = createTextField();
        serverNameTextField.prefHeightProperty().bind(hBox.heightProperty());
        serverNameTextField.setText(serverEntity.getName());
        serverNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            serverEntity.setName(newValue);
        });
        hBox.getChildren().addAll(label, serverNameTextField);
        return hBox;
    }

    private HBox createButtonLane() {
        HBox buttonLane = new HBox();
        buttonLane.setAlignment(Pos.CENTER_RIGHT);
        Button okButton = new Button("OK");
        okButton.setOnAction(event -> onOK());
        okButton.setId("okButton");
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> onCancel());
        cancelButton.setId("cancelButton");
        buttonLane.getChildren().addAll(okButton, cancelButton);
        return buttonLane;
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
    public void setDialogListener(DialogListener listener) {
        this.listener = listener;
    }

    @Override
    public void onOK() {
        Dialogs.showMessageBox("INFO", "Ok pressed", Dialogs.IconType.INFO);
        listener.onOK();
        hide();
    }

    @Override
    public void onCancel() {
        Dialogs.showMessageBox("INFO", "Cancel pressed", Dialogs.IconType.INFO);
        listener.onCancel();
        hide();
    }

    /**
     * @return server information after editing
     */
    public ServerEditorEntity getServerInfo() {
        return serverEntity;
    }
}
