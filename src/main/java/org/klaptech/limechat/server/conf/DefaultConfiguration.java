package org.klaptech.limechat.server.conf;

/**
 * DefaultConfiguration of server
 * @author rlapin
 */
public class DefaultConfiguration implements Configuration{
    @Override
    public int getPort() {
        return 3306;
    }

    @Override
    public String getAddr() {
        return "localhost";
    }
}
