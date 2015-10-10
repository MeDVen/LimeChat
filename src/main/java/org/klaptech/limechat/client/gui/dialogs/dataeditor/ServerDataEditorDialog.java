package org.klaptech.limechat.client.gui.dialogs.dataeditor;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.klaptech.limechat.client.gui.GUIManager;
import org.klaptech.limechat.client.gui.components.maskfield.MaskInputView;
import org.klaptech.limechat.client.gui.components.maskfield.validators.IntValidator;
import org.klaptech.limechat.client.gui.components.maskfield.validators.LengthValidator;
import org.klaptech.limechat.client.gui.components.maskfield.validators.ListNotContainsValidator;
import org.klaptech.limechat.client.gui.components.maskfield.validators.MaxLengthValidator;
import org.klaptech.limechat.client.gui.dialogs.DialogListener;
import org.klaptech.limechat.client.gui.dialogs.Dialogs;
import org.klaptech.limechat.client.gui.dialogs.OkCancelDialog;
import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.ServerEditorEntity;
import org.klaptech.limechat.client.net.ServerAddress;
import org.klaptech.limechat.client.utils.GUIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility dialog for editing or adding new data<br>
 * Styling info: <br>
 * <ul>
 * <li>use class root for styling dialog contentpane</li>
 * <li>use class eLabel for styling label elements</li>
 * <li>id okButton for styling ok button</li>
 * <li>class eButton for styling ok button</li>
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
    private List<String> existedServers;
    private MaskInputView serverNameMaskView;
    private MaskInputView portMaskView;
    private MaskInputView addrMaskView;


    public ServerDataEditorDialog(ServerEditorEntity serverEntity, Stage parent) {
        this.serverEntity = serverEntity;
        existedServers = new ArrayList<>();
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

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(parent);
    }

    private void initComponents() {
        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox(5);
        Scene scene = new Scene(borderPane, width, height);
        borderPane.setId("editor");
        GUIUtils.addCss(scene, "fxml/editors.css");
        stage.setScene(scene);
        HBox serverNameLane = createServerNameLane();
        HBox serverAddressLane = createServerAddressLane();
        HBox buttonLane = createButtonLane();
        vBox.getChildren().addAll(serverNameLane, serverAddressLane);
        borderPane.setCenter(vBox);
        borderPane.setBottom(buttonLane);
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
        addrMaskView = new MaskInputView(addrTextField, new LengthValidator(5));
        addrTextField.prefHeightProperty().bind(hBox.heightProperty());
        TextField portTextField = createTextField();
        portMaskView = new MaskInputView(portTextField, new MaxLengthValidator(5), new IntValidator());
        portTextField.prefHeightProperty().bind(hBox.heightProperty());
        portTextField.setId("eServerPortTextField");
        addrTextField.setText(serverEntity.getAddress().getAddr());
        portTextField.setText(String.valueOf(serverEntity.getAddress().getPort()));
        portTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            String text = portMaskView.getText();
            int port = text.isEmpty() ? 0 : Integer.parseInt(text);
            serverEntity.setAddress(new ServerAddress(serverEntity.getAddress().getAddr(), port));
        });
        addrTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            serverEntity.setAddress(new ServerAddress(addrMaskView.getText(), serverEntity.getAddress().getPort()));
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
        serverNameMaskView = new MaskInputView(serverNameTextField, new ListNotContainsValidator(existedServers), new LengthValidator(5));
        serverNameTextField.prefHeightProperty().bind(hBox.heightProperty());
        serverNameTextField.setText(serverEntity.getName());
        serverNameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            serverEntity.setName(serverNameMaskView.getText());
        });
        hBox.getChildren().addAll(label, serverNameTextField);
        return hBox;
    }

    private HBox createButtonLane() {
        HBox buttonLane = new HBox(5);
        buttonLane.setAlignment(Pos.CENTER_RIGHT);
        Button okButton = new Button("OK");
        okButton.setOnAction(event -> onOK());
        okButton.setId("okButton");
        okButton.getStyleClass().add("eButton");

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> onCancel());
        cancelButton.setId("cancelButton");
        cancelButton.getStyleClass().add("eButton");
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

        if (isCorrect()) {
            listener.onOK();
            hide();
        } else {
            Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), "Error", "Check fields", Dialogs.IconType.ERROR);
        }
    }

    /**
     * Check if is serverentity is correct
     *
     * @return true if is valid serverentity
     */
    private boolean isCorrect() {
        return !serverNameMaskView.getText().isEmpty() && !addrMaskView.getText().isEmpty() && !portMaskView.getText().isEmpty();
    }

    @Override
    public void onCancel() {
        Dialogs.showMessageBox(GUIManager.getInstance().getMainStage(), "INFO", "Cancel pressed", Dialogs.IconType.INFO);
        listener.onCancel();
        hide();
    }

    /**
     * @return server information after editing
     */
    public ServerEditorEntity getServerInfo() {
        return serverEntity;
    }

    public void initServersList(List<String> serversNameList) {
        existedServers.clear();
        existedServers.addAll(serversNameList);
    }
}
