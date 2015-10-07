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
        setId("historytextarea");
        setEditable(false);
        setText("(00:00) user1: Cool chat. :limechat: \n"
                + "(00:01) user2: Yeah. :thumbsup:");
        setContextMenu(new ContextMenu());
    }
}
