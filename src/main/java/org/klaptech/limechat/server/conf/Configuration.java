package org.klaptech.limechat.server.conf;

/**
 * Configuration for server
 * @author rlapin
 */
public interface Configuration {
    /**
     * get listen port
     * @return listen port , where clients should connect
     */
    int getPort();
}
