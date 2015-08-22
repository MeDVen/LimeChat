package org.klaptech.limechat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Manager for accept input connections
 * @author rlapin
 */
public class ClientAcceptService {
    private static final Logger LOGGER = getLogger(ClientAcceptService.class.getCanonicalName());
    private final ServerSocket serverSocket;
    private ClientListener listener;

    public ClientAcceptService(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Listen for input connections
     */
    public void listen(){
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        listener.clientConnected(new ClientEvent(serverSocket.accept()));
                    } catch (IOException e) {
                        LOGGER.severe(e.getMessage());
                    }
                }
            }
        });
    }

    public void setClientListener(ClientListener clientListener) {
        this.listener = clientListener;
    }
}
