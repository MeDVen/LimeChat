package org.klaptech.limechat.shared.enums;

import org.klaptech.limechat.shared.Message;

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
     * User send msg
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
    ANSWER_LOGIN, ANSWER_JOIN;
}
