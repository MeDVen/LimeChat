package org.klaptech.limechat.server.conf;

/**
 * DefaultConfiguration of server
 * @author rlapin
 */
public class DefaultConfiguration implements Configuration{
    @Override
    public int getPort() {
        return 5678;
    }

    @Override
    public String getAddr() {
        return "192.168.1.28";
    }
}
