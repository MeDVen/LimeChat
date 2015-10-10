package org.klaptech.limechat.client.gui.dialogs;

/**
 * Listener using for define close dialog behaviour
 *
 * @author rlapin
 */
public interface DialogListener {
    default void onOK() {
    }

    default void onCancel() {
    }
}
