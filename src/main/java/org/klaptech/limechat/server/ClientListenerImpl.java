package org.klaptech.limechat.server;

import org.klaptech.limechat.server.io.Writer;
import org.klaptech.limechat.shared.server.LoginAnswer;
import org.klaptech.limechat.shared.server.ServerMessageFactory;

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

        Client client = e.getClient();
        server.addClient(client);
        LOGGER.info(String.format("Client %s connected successfully",client));
        client.listen();
    }

    @Override
    public void clientDisconnected(ClientEvent e) {
        server.removeClient(e.getClient());
        LOGGER.info(String.format("Client %s disconnected successfully",e.getClient().getSocket()));
    }

    @Override
    public void clientSendMessage(ClientEvent e) {
        System.out.println(e.getClient().getSocket()+":"+e.getMessage());
    }

    @Override
    public void clientLogin(ClientEvent e) {
        Writer.write(e.getClient(), ServerMessageFactory.createLoginAnswer(LoginAnswer.TYPE.SUCCESS));
    }
}
