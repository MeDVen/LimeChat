package org.klaptech.limechat.client.gui.dialogs.dataeditor.entities;

import org.klaptech.limechat.client.net.ServerAddress;

/**
 * Server entity for editor
 *
 * @author rlapin
 */
public class ServerEditorEntity {
    private ServerAddress address = new ServerAddress("", 0);
    private String name = "";

    public ServerAddress getAddress() {
        return address;
    }

    public void setAddress(ServerAddress address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
