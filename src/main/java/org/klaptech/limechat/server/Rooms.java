package org.klaptech.limechat.server;

import java.util.HashSet;
import java.util.Set;

/**
 * Rooms on server
 *
 * @author rlapin
 */
public class Rooms {
    private final Set<Room> rooms = new HashSet<>();
    public final static Room DUMMY_ROOM = new Room("NULL");

    public Rooms() {
        generateChannels();
    }

    private void generateChannels() {
        rooms.add(new Room("default"));
        rooms.add(new Room("testopenchannel"));
        rooms.add(new Room("closedchannel", "12345"));
    }

    /**
     * Get channelName by name
     *
     * @param channelName (NotNull)
     * @return Room with name given in param or Room dummy
     */
    public Room getChannelByName(String channelName) {
        for (Room room : rooms) {
            if (room.getName().equals(channelName)) {
                return room;
            }
        }
        return DUMMY_ROOM;
    }
}
