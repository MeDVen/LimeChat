package org.klaptech.limechat.client.events;

import org.klaptech.limechat.client.gui.GUIManager;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * @author rlapin
 */
public class ServerEvents {
    private static final Logger LOGGER = getLogger(ServerEvents.class.getName());

    public void connected() {
        GUIManager.getInstance().getServerConnectorDialog().connectionSucceed();
    }


    public void connectionFailed() {
        GUIManager.getInstance().getServerConnectorDialog().connectionFailed();
    }

    public void disconnected() {
        LOGGER.info("Disconnected");
    }
}
