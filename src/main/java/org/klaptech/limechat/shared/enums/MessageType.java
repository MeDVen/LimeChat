package org.klaptech.limechat.shared.enums;

import org.klaptech.limechat.shared.Message;

/**
 * Type of message{@link Message}
 * @author rlapin
 */
public enum MessageType {
    /**
     * UserInfo join room
     */
    JOIN,
    /**
     * UserInfo send msg
     */
    MSG,
    /**
     * UserInfo connect to server
     */
    LOGIN,
    /**
     * UserInfo disconnect
     */
    DISCONNECT,
    /**
     * Incorrect command
     */
    ERROR,
    /**
     * Server answer on user login
     */
    ANSWER_LOGIN, ANSWER_JOIN, LEAVE, ANSWER_LEAVE, REGISTER, ANSWER_REGISTER, ROOM_USERS, NEW_USER;
}
