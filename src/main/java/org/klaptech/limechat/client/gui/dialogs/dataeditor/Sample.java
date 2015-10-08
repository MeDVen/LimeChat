package org.klaptech.limechat.client.gui.dialogs.dataeditor;

import javafx.application.Application;
import javafx.stage.Stage;
import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.EditorEntity;
import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.ServerEditorEntity;

/**
 * @author rlapin
 */
public class Sample extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        DataEditorDialog<EditorEntity> dialog = new DataEditorDialog<>(ServerEditorEntity.getEntities(), primaryStage);
        dialog.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
