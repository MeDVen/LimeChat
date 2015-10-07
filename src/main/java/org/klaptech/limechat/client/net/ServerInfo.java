package org.klaptech.limechat.client.net;

import java.io.Serializable;

/**
 * Contains server infomarmation
 *
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
}
