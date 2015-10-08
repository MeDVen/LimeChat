package org.klaptech.limechat.client.utils;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.klaptech.limechat.client.net.ServerInfo;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Entity contains properties
 *
 * @author rlapin
 */
public class Properties implements Serializable {
    private Set<ServerInfo> servers;
    private static final Logger LOGGER = getLogger(Properties.class.getName());

    public Properties() {
        servers = new TreeSet<>();


    }

    private void serversListUpdated() {
        PropertyManager.INSTANCE.writeProperties();
        LOGGER.info("Servers list updated want to update properties file");
    }

    /**
     * wrap list with observable and add valuechange listener then return it
     *
     * @return observable list making from arraylist which is read from properyfile
     */
    public ObservableList<ServerInfo> getServerList() {
        ObservableList<ServerInfo> observableList = FXCollections.observableArrayList(servers);
        observableList.addListener((ListChangeListener<ServerInfo>) c -> {
                    servers.clear();
                    servers.addAll(c.getList());
                    serversListUpdated();
                }
        );
        return observableList;
    }
}
