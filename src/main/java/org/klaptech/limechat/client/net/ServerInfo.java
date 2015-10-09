package org.klaptech.limechat.client.net;

import java.io.Serializable;

/**
 * Contains server infomarmation
 * @author rlapin
 */
public class ServerInfo implements Serializable, Comparable {
    private ServerAddress addr;
    private String name;

    public ServerInfo(ServerAddress addr, String name) {
        this.addr = addr;
        this.name = name;
    }

    public ServerAddress getAddr() {
        return addr;
    }


    public String getName() {
        return name;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ServerInfo that = (ServerInfo) o;

        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override public String toString() {
        return name;
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        }
        if (o == null || getClass() != o.getClass()) {
            return 0;
        }

        ServerInfo that = (ServerInfo) o;
        return name.compareTo(that.name);
    }
}
