package org.klaptech.limechat.client.gui.dialogs.dataeditor;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.klaptech.limechat.client.gui.dialogs.OkCancelDialog;
import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.EditorEntity;
import org.klaptech.limechat.client.utils.GUIUtils;

import java.util.List;

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
public class DataEditorDialog<T extends EditorEntity> implements OkCancelDialog {
    private Stage stage;
    private int width = 300;
    private int height = 300;
    private List<T> fields;

    public DataEditorDialog(List<T> fields, Stage parent) {
        this.fields = fields;
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
        for (T field : fields) {
            HBox hBox = new HBox();
            hBox.getStyleClass().add("eLane");
            Label label = new Label(field.getLabelText());
            label.getStyleClass().add("eLabel");
            hBox.getChildren().addAll(label, field.getEntityView().getGraphics());
            vBox.getChildren().add(hBox);
        }
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
    public void onOK() {
        hide();
    }

    @Override
    public void onCancel() {
        hide();
    }
}
