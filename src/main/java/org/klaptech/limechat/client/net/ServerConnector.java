package org.klaptech.limechat.client.net;

import static java.util.logging.Logger.getLogger;






import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import org.klaptech.limechat.client.events.UserEvents;
import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.utils.ByteObjectConverter;

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

    public void connect() throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        events = new UserEvents();
        socketChannel.connect(new InetSocketAddress("192.168.1.28", 5678));
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
