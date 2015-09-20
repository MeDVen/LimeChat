package org.klaptech.limechat.client.gui.components;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * TabPane with tabs. One tab - one room.
 * Tab has textArea, where user enter his message,
 * and area with conversation history.
 * Every tab has instrument panel. Smiles, call history
 * and other.
 * @author MeDVen
 */
public class ChatTabPane extends TabPane {

    public ChatTabPane() {
        super();
    }

    public void addNewTab(Tab tab) {
        pinToolbar(tab); // empty
        this.getTabs().add(tab);
    }

    public void removeTab(Tab tab) {
        getTabs().removeAll(tab);
    }

    /**
     * Need do this.
     */
    public void pinToolbar(Tab tab) {
        // TODO make toolbar
    }
}
