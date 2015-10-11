package org.klaptech.limechat.client.net;

import org.klaptech.limechat.client.events.ServerEvents;
import org.klaptech.limechat.client.events.UserEvents;
import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.utils.ByteObjectConverter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnresolvedAddressException;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Connects to server and create read selector
 *
 * @author rlapin
 */
public enum ServerConnector {
    INSTANCE;
    /**
     * Connection timeout
     */
    public static final long CONNECT_TIMEOUT = 1000;
    private SocketChannel socketChannel;
    private static final Logger LOGGER = getLogger(ServerConnector.class.getName());
    private Selector selector;
    private UserEvents userEvents;
    private ServerEvents serverEvents;

    /**
     * Connect to server
     *
     * @param addr address of server
     * @param port server port
     * @throws IOException cannot connect to server
     */
    public void connect(String addr, int port) {
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            userEvents = new UserEvents();
            serverEvents = new ServerEvents();
            socketChannel.connect(new InetSocketAddress(addr, port));
            LOGGER.info("connection succeed");
            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_READ);
            while (!socketChannel.finishConnect()) {

            }
            serverEvents.connected();
            Executors.newSingleThreadExecutor().execute(new ServerListener(this));
        } catch (IOException | UnresolvedAddressException exception) {
            serverEvents.connectionFailed();
        }
    }


    public void write(Message message) {
        try {
            socketChannel.write(ByteBuffer.wrap(ByteObjectConverter.objectToBytes(message)));
        } catch (IOException e) {
            //TODO mb not disconnected
            serverEvents.disconnected();
        }
    }


    public Selector getSelector() {
        return selector;
    }

    public UserEvents getUserEvents() {
        return userEvents;
    }
}
