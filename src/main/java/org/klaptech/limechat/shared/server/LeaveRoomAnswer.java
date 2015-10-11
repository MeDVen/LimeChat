package org.klaptech.limechat.shared.server;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.enums.LeaveType;
import org.klaptech.limechat.shared.enums.MessageType;

/**
 * @author rlapin
 */
public class LeaveRoomAnswer implements Message {

    private LeaveType leaveType;
    /**
     * Room name
     */
    private final String channelName;

    protected LeaveRoomAnswer(LeaveType leaveType, String channelName) {

        this.leaveType = leaveType;
        this.channelName = channelName;
    }
    @Override
    public MessageType getType() {
        return MessageType.ANSWER_LEAVE;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public String getChannelName() {
        return channelName;
    }
}
