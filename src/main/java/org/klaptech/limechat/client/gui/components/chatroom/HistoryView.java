package org.klaptech.limechat.client.gui.components.chatroom;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextArea;
import org.klaptech.limechat.client.utils.GUIUtils;

/**
 * @author rlapin
 * @author MeDVen
 */
public class HistoryView extends TextArea {

    public HistoryView() {
        GUIUtils.addCss(this, "fxml/historyview.css");
        initComponents();
    }

    private void initComponents() {
        setId("historyview");
        setEditable(false);
        setText("");
        setContextMenu(new ContextMenu());
    }
}
