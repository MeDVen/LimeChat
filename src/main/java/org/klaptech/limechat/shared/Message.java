package org.klaptech.limechat.shared;

import org.klaptech.limechat.shared.enums.MessageType;

import java.io.Serializable;

/**
 * Entity for exchange between server and clients
 * @author rlapin
 */
public interface Message extends Serializable{
    /**
     * @return type of message
     */
    MessageType getType();
}
