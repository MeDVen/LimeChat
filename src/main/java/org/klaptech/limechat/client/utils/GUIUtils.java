package org.klaptech.limechat.client.utils;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Gui Utils with hacks methods.
 * @author  MeDVen
 */
public class GUIUtils {

    private GUIUtils() {
    }

    /**
     * Delete all tollbar buttons from {@link HTMLEditor}, just only
     * input area, what can display html-code.
     * @param editor
     */
    public static void hideHTMLEditorToolbars(final HTMLEditor editor) {
        editor.setVisible(false);
        Platform.runLater(() -> {
            Node[] nodes = editor.lookupAll(".tool-bar").toArray(new Node[0]);
            for (Node node : nodes) {
                node.setVisible(false);
                node.setManaged(false);
            }
            editor.setVisible(true);
        });
    }

    /**
     * Move stage to center
     * @param stage
     */
    public static void centerStage(Stage stage) {
        double width = stage.getWidth();
        double height = stage.getHeight();
        stage.setX((Screen.getPrimary().getBounds().getWidth() - width) / 2);
        stage.setY((Screen.getPrimary().getBounds().getHeight() - height) / 2);
    }

    /**
     * Set grow to column. If GridPane have more than 1 column,
     * <b>need use for each</b>.
     * @param grid
     */
    public static void autoSizeGridPaneColumns(GridPane grid) {
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().add(cc);
    }

    /**
     * Set grow to row. If GridPane have more than and 1 row,
     * <b>need use for each</b>.
     * @param grid
     */
    public static void autoSizeGridPaneRows(GridPane grid) {
        RowConstraints rc = new RowConstraints();
        rc.setVgrow(Priority.ALWAYS);
        grid.getRowConstraints().add(rc);
    }
}
