package org.klaptech.limechat.client.net;

import org.klaptech.limechat.client.events.UserEvents;
import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.utils.ByteObjectConverter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
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
    private UserEvents events;

    /**
     * Connect to server
     *
     * @param addr address of server
     * @param port server port
     * @throws IOException cannot connect to server
     */
    public void connect(String addr, int port) throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        events = new UserEvents();
        socketChannel.connect(new InetSocketAddress(addr, port));
        LOGGER.info("connection succeed");
        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);
        while (!socketChannel.finishConnect()) {

        }
        Executors.newSingleThreadExecutor().execute(new ServerListener(this));

    }


    public void write(Message message) throws IOException {
        socketChannel.write(ByteBuffer.wrap(ByteObjectConverter.objectToBytes(message)));
    }


    public Selector getSelector() {
        return selector;
    }

    public UserEvents getEvents() {
        return events;
    }
}
