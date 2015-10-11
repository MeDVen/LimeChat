package org.klaptech.limechat.client.gui.dialogs;

/**
 * Describes standard dialog methods
 *
 * @author rlapin
 */
public interface Dialog {
    /**
     * Show dialog
     */
    void show();

    /**
     * Hide dialog
     */
    void hide();

    /**
     * Set title of dialog
     *
     * @param title
     */
    void setTitle(String title);
}
