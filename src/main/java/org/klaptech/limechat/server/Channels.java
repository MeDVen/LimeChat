package org.klaptech.limechat.server;

import java.util.HashSet;
import java.util.Set;

/**
 * Channels on server
 * @author rlapin
 */
public class Channels {
    private final Set<Channel> channels= new HashSet<>();
    public final static Channel DUMMY_CHANNEL = new Channel("NULL");

    public Channels() {
        generateChannels();
    }

    private void generateChannels() {
        channels.add(new Channel("default"));
        channels.add(new Channel("testopenchannel"));
        channels.add(new Channel("closedchannel", "12345"));
    }

    /**
     * Get channelName by name
     * @param channelName (NotNull)
     * @return Channel with name given in param or Channel dummy
     */
    public Channel getChannelByName(String channelName) {
        for (Channel channel : channels) {
            if(channel.getName().equals(channelName)){
                return channel;
            }
        }
        return DUMMY_CHANNEL;
    }
}
