package org.klaptech.limechat.shared.utils;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.web.HTMLEditor;

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
}
