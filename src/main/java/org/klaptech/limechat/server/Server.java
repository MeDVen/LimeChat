package org.klaptech.limechat.server;

import org.klaptech.limechat.server.conf.Configuration;
import org.klaptech.limechat.server.conf.DefaultConfiguration;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 * Describes server entity
 * @author rlapin
 */
public class Server{
    private static final Logger logger = Logger.getLogger(Server.class.getCanonicalName());
    private final Configuration config;
    private ServerSocket serverSocket;
    private ClientAcceptService acceptManager;
    private List<Client> clients;
    private ClientListener clientListener;

    public Server(Configuration config) {
        this.config = config;
        clients = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(config.getPort());

        } catch (IOException e) {
            logger.severe(e.getMessage());
        }
        clientListener = new ClientListenerImpl(this);
    }

    /**
     * Add client to client list
     * @param client
     */
    public void addClient(Client client){
        client.setClientListener(clientListener);
        client.listen();
        clients.add(client);
    }
    /**
     * Start clientlisten service
     */
    public void startListen(){
        acceptManager = new ClientAcceptService(serverSocket);
        acceptManager.setClientListener(clientListener);
        acceptManager.listen();
    }

    public static void main(String[] args) {
        Server server = new Server(new DefaultConfiguration());
        server.startListen();
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }
}
