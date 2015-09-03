package org.klaptech.limechat.client.gui.components;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Glow;

/**
 * TabPane with tabs. One tab - one room.
 * Tab has textArea, where user enter his message,
 * and area with conversation history.
 * Every tab has instrument panel. Smiles, call history
 * and other.
 * Created by MeDVen on 02.09.2015.
 */
public class ChatTabPane extends TabPane {

    public ChatTabPane() {
        super();
    }

    /**
     * Add new tab to TabPane
     * @param roomTab tab with room
     */
    public void addNewTab(Tab roomTab) {

        TextArea textArea = new TextArea();
        textArea.setScrollLeft(300);

        Glow glow = new Glow();
        glow.setLevel(5);

        textArea.setEffect(glow);


        pinInstrumentPanel(roomTab); // empty

        this.getTabs().add(roomTab);
    }

    /**
     * Need do this.
     */
    public void pinInstrumentPanel(Tab tab) {
        /* TO-DO */
    }
}
