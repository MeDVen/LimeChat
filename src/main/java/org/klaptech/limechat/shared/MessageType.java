package org.klaptech.limechat.shared;

/**
 * Type of message{@link Message}
 * @author rlapin
 */
public enum MessageType {
    /**
     * User join room
     */
    JOIN,
    /**
     * User write msg
     */
    MSG,
    /**
     * User connect to server
     */
    LOGIN,
    /**
     * User disconnect
     */
    DISCONNECT,
    /**
     * Incorrect command
     */
    ERROR,
    /**
     * Server answer on user login
     */
    ANSWER_LOGIN;
}
