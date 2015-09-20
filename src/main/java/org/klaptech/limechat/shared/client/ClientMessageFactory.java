package org.klaptech.limechat.shared.client;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.utils.HashUtils;

/**
 * Client message factory
 * @author rlapin
 */
public class ClientMessageFactory {
    /**
     * Create login message
     * @param username
     * @param password
     * @return message sending to server, when user is connecting
     */
    public static Message createLoginMessage(String username, byte[] password) {
        return new LoginMessage(username, HashUtils.md5(password));
    }

    /**
     * User join channel
     * @param channelName
     * @param password
     * @return joinmessage
     */
    public static Message createJoinChannelMessage(String channelName, byte[] password){
        return new JoinChannelMessage(channelName, HashUtils.md5(password));
    }
    /**
     * User join channel
     * @param channelName
     * @return joinmessage
     */
    public static Message createJoinChannelMessage(String channelName){
        return new JoinChannelMessage(channelName, "");
    }

    /**
     * User leave channe
     * @param channelName
     * @return leavechannelmessage
     */
    public static Message createLeaveChannelMessage(String channelName){
        return new LeaveChannelMessage(channelName);
    }
}
