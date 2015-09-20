package org.klaptech.limechat.shared.enums;

/**
 * Leavetype is send to user when he leave channel or kicked or disconnected
 * @author rlapin
 */
public enum LeaveType {
    /**
     * Kicked
     */
    KICK,
    /**
     * Disconnected
     */
    DISCONNECT,
    /**
     * Leave channel
     */
    USER
}
