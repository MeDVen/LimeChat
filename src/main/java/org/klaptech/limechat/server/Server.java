package org.klaptech.limechat.server;

import org.klaptech.limechat.server.conf.Configuration;
import org.klaptech.limechat.server.conf.DefaultConfiguration;
import org.klaptech.limechat.shared.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


/**
 * Describes server entity
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
        listenClients();
    }

    /**
     * Listen for current clients
     */
    private void listenClients() {
        int nThreads = 1; //TODO choose right number
        Executors.newFixedThreadPool(nThreads).execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (Client client : clients) {
                        listenClient(client.getSocket());
                    }
                }


            }
        });
    }

    /**
     * Listen client message
     *
     * @param socket client socket
     */
    private synchronized void listenClient(Socket socket) {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            Message message;
            if ((message = (Message) objectInputStream.readObject()) != null) {
                ClientEvent clientEvent = new ClientEvent(socket);
                clientEvent.setMessage(message.toString());
                clientListener.clientSendMessage(clientEvent);
            }

        } catch (IOException | ClassNotFoundException e) {
            LOGGER.severe(e.getMessage());
            clientListener.clientDisconnected(new ClientEvent(socket));

            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if (!socket.isClosed()) {

                    socket.close();
                }
            } catch (IOException ex) {
                LOGGER.severe(ex.getMessage());
            }
        }
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
     * Start clientlisten service
     */
    public void startListen() {
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
