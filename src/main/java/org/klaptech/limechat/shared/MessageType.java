package org.klaptech.limechat.shared;

/**
 * Type of message{@link Message}
 * @author rlapin
 */
public enum MessageType {
    /**
     * User join room
     */
    JOIN("JOIN"),
    /**
     * User write msg
     */
    MSG("MSG"),
    /**
     * User connect to server
     */
    CONNECT("CON"),
    /**
     * User disconnect
     */
    DISCONNECT("EXIT"),
    /**
     * Incorrect command
     */
    ERROR("");

    /**
     * Pattern of message type(check before serialize)
     */
    private final String inputPattern;

    MessageType(String inputPattern) {

        this.inputPattern = inputPattern;
    }

    public String getInputPattern() {
        return inputPattern;
    }
}
