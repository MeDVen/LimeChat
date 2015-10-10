package org.klaptech.limechat.client.gui.dialogs.dataeditor;

import javafx.application.Application;
import javafx.stage.Stage;
import org.klaptech.limechat.client.gui.dialogs.DialogListener;
import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.ServerEditorEntity;

/**
 * @author rlapin
 */
public class Sample extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        ServerDataEditorDialog dialog = new ServerDataEditorDialog(new ServerEditorEntity(), primaryStage);
        dialog.show();
        dialog.setDialogListener(new DialogListener() {
            @Override
            public void onOK() {
                System.out.println(dialog.getServerInfo());
            }

            @Override
            public void onCancel() {

            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }


}
