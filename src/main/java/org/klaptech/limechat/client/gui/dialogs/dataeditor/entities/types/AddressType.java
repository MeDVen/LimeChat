package org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.types;

import org.klaptech.limechat.client.net.ServerAddress;

/**
 * Describes ip(dns):port type
 *
 * @author rlapin
 */
public class AddressType implements EditorType {
    public static final int DEFAULT_PORT = 2134;
    private ServerAddress serverAddress;

    @Override
    public ServerAddress getValue() {
        return serverAddress;
    }

    public AddressType(ServerAddress serverAddress) {
        this.serverAddress = serverAddress;
    }

}
