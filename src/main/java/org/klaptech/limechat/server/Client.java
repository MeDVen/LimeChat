package org.klaptech.limechat.server;

import org.klaptech.limechat.shared.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Client entity
 *
 * @author rlapin
 */
public class Client {
    private static final Logger LOGGER = Logger.getLogger(Client.class.getCanonicalName());
    /**
     * Socket getting from client connection
     */
    private Socket socket;
    private ClientListener clientListener;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public String toString() {
        return String.format("Client [ ip: %s ]", socket.getInetAddress().getHostAddress());
    }

    public void setClientListener(ClientListener clientListener) {
        this.clientListener = clientListener;
    }

    /**
     * Listen for client messages
     */
    public void listen() {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                ObjectInputStream objectInputStream = null;
                try {
                    objectInputStream = new ObjectInputStream(socket.getInputStream());
                    while (!Thread.currentThread().isInterrupted()) {
                        Message message;
                        if((message = (Message)objectInputStream.readObject())!=null){
                            ClientEvent clientEvent = new ClientEvent(socket);
                            clientEvent.setMessage(message.toString());
                            clientListener.clientSendMessage(clientEvent);
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    LOGGER.severe(e.getMessage());
                } finally {

                    clientListener.clientDisconnected(new ClientEvent(socket));

                    try {
                        assert objectInputStream != null;
                        objectInputStream.close();
                        if (!socket.isClosed()) {

                            socket.close();
                        }
                    } catch (IOException e) {
                        LOGGER.severe(e.getMessage());
                    }

                }

            }
        });
    }
}
