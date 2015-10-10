package org.klaptech.limechat.client.net;

import java.io.Serializable;

/**
 * Serveraddress consist of host address and port
 *
 * @author rlapin
 */
public class ServerAddress implements Serializable {
    private String addr;
    private int port;

    public ServerAddress(String addr, int port) {
        this.port = port;
        this.addr = addr;
    }

    public String getAddr() {
        return addr;
    }


    public int getPort() {
        return port;
    }


    @Override
    public String toString() {
        return addr + ":" + port;
    }
}
