package org.klaptech.limechat.server;

import java.util.logging.Logger;

/**
 * Implementation of client listener for server
 * @author rlapin
 */
public class ClientListenerImpl implements ClientListener {
    private static final Logger LOGGER = Logger.getLogger(ClientListenerImpl.class.getCanonicalName());
    private final Server server;

    public ClientListenerImpl(Server server) {
        this.server = server;
    }

    @Override
    public void clientConnected(ClientEvent e) {
        Client client = new Client(e.getSocket());
        server.addClient(client);
        LOGGER.info(String.format("Client %s connected successfully",client));
    }
}
