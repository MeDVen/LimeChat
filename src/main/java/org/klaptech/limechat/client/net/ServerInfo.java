package org.klaptech.limechat.client.net;

import java.io.Serializable;

/**
 * Contains server infomarmation
 * @author rlapin
 */
public class ServerInfo implements Serializable {
    private String addr;
    private int port;
    private String name;

    public ServerInfo(String addr, int port, String name) {
        this.addr = addr;
        this.port = port;
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public int getPort() {
        return port;
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
}
