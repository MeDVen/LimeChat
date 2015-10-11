package org.klaptech.limechat.client.events;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * @author rlapin
 */
public class ServerEvents {
    private static final Logger LOGGER = getLogger(ServerEvents.class.getName());

    public void connected() {
        LOGGER.info("Connected");
    }

    public void connectionFailed() {
        LOGGER.info("Connection failed");
    }

    public void disconnected() {
        LOGGER.info("Disconnected");
    }
}
