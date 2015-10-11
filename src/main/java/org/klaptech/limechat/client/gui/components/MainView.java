package org.klaptech.limechat.client.gui.components;

import javafx.scene.layout.GridPane;
import org.klaptech.limechat.client.utils.GUIUtils;

/**
 * Main chat content
 *
 * @author rlapin
 */
public class MainView extends GridPane {
    ChatTabPane tabPane;

    public MainView() {

        initComponents();

        GUIUtils.autoSizeGridPaneColumns(this);
        GUIUtils.autoSizeGridPaneRows(this);
    }

    private void initComponents() {
        tabPane = new ChatTabPane();

        add(tabPane, 0, 0);
    }
}
