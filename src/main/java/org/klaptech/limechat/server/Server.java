package org.klaptech.limechat.server;

import org.klaptech.limechat.server.conf.Configuration;
import org.klaptech.limechat.server.conf.DefaultConfiguration;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;


/**
 * Server entity
 *
 * @author rlapin
 */
public class Server {
    private static final Logger LOGGER = Logger.getLogger(Server.class.getCanonicalName());
    private final Configuration config;
    private ServerSocket serverSocket;
    private ClientAcceptService acceptManager;
    private List<Client> clients;
    private ClientListener clientListener;

    public Server(Configuration config) {
        this.config = config;
        clients = Collections.synchronizedList(new ArrayList<>());
        try {
            serverSocket = new ServerSocket(config.getPort());

        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        clientListener = new ClientListenerImpl(this);

    }




    /**
     * Add client to client list
     *
     * @param client
     */
    public void addClient(Client client) {
        client.setClientListener(clientListener);
        // NOTIFY THAT client is add
        clients.add(client);
    }

    /**
     * Start clientlisten for accept service
     */
    public void startListenForAccept() {
        acceptManager = new ClientAcceptService(serverSocket);
        acceptManager.setClientListener(clientListener);
        acceptManager.listen();
    }

    public static void main(String[] args) {
        Server server = new Server(new DefaultConfiguration());
        server.startListenForAccept();
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    /**
     * Remove client by it socket
     * @param socket clientsocket
     */
    public void removeClient(Socket socket) {
        for (Iterator<Client> iterator = clients.iterator(); iterator.hasNext(); ) {
            Client client = iterator.next();
            if(client.getSocket() == socket){
                iterator.remove();
            }
        }
    }

}
