package org.klaptech.limechat.server.client;

import org.klaptech.limechat.server.auth.Authorizer;
import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.MessageType;
import org.klaptech.limechat.shared.client.Login;

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
     * Start listening client
     */
    public void listen() {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    ObjectInputStream objInputStream = new ObjectInputStream(socket.getInputStream());
                    Message msg = (Message) objInputStream.readObject();
                    if (msg.getType() == MessageType.LOGIN) {
                        Login loginMsg = (Login) msg;
                        ClientEvent clientEvent = new ClientEvent(Client.this);
                        clientEvent.setType(Authorizer.auth(loginMsg.getUsername(),loginMsg.getPassword()));
                        clientListener.clientLogin(clientEvent);
                    }
                    while (true) {
                        msg = (Message) objInputStream.readObject();
                        if (msg != null) {
                            System.out.println(msg);
                        }
                    }
                } catch (IOException | ClassNotFoundException e) { // Reading error. Client disconnect
                    clientListener.clientDisconnected(new ClientEvent(Client.this));
                }
            }
        });
    }
}