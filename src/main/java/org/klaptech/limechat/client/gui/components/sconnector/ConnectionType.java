package org.klaptech.limechat.client.gui.components.sconnector;

/**
 * Defines if connection to server is succeed or failed
 *
 * @author rlapin
 */
public enum ConnectionType {
    SUCCESS("successLabel"), FAIL("errorLabel"), NOT_CONNECTED("notconLabel");

    private String cssId;

    ConnectionType(String cssID) {
        this.cssId = cssID;
    }

    public String getCssID() {
        return cssId;
    }
}
