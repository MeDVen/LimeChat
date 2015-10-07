package org.klaptech.limechat.client.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.klaptech.limechat.client.net.ServerInfo;

/**
 * Entity contains properties
 *
 * @author rlapin
 */
public class Properties implements Serializable {
    private List<ServerInfo> servers;

    public Properties() {
        servers = new ArrayList<>();
        fillSampleData();
    }

    private void fillSampleData() {
        servers.add(new ServerInfo("127.0.0.1", 1234, "TestServer"));
    }

    public List<ServerInfo> getServerList() {
        return servers;
    }
}
