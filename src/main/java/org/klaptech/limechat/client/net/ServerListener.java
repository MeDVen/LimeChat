package org.klaptech.limechat.client.net;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.klaptech.limechat.client.events.UserEvents;
import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.server.LoginAnswer;
import org.klaptech.limechat.shared.server.RegisterAnswer;
import org.klaptech.limechat.shared.utils.ByteObjectConverter;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Listen server messages
 *
 * @author rlapin
 */
public class ServerListener implements Runnable {
    private static final Logger LOGGER = getLogger(ServerListener.class.getName());
    /**
     * Read buffer
     */
    private static ByteBuffer readBuffer = ByteBuffer.allocate(8196);
    /**
     * Used as bridge to gui
     */
    private final UserEvents events;
    /**
     * Read selector
     */
    private Selector selector;

    public ServerListener(ServerConnector serverConnector) {

        this.selector = serverConnector.getSelector();
        this.events = serverConnector.getUserEvents();

    }

    @Override
    public void run() {
        try {
            while (true) {
                if (selector.select() == 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey selectionKey : selectionKeys) {
                    if (selectionKey.isReadable()) {
                        read(selectionKey);
                    }
                }
                selectionKeys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();

        ByteOutputStream byteOutputStream = new ByteOutputStream();
        int numRead = -1;
        while (numRead != 0) {
            readBuffer.clear();
            try {
                numRead = socketChannel.read(readBuffer);
                if (numRead == -1) {
                    key.channel().close();
                    key.cancel();
                    // User disconnected or lost connection
                    LOGGER.info("User disconnected");
                    return;
                }

                byteOutputStream.write(readBuffer.array());
            } catch (IOException e) {
                // User disconnected or lost connection
                LOGGER.severe("Problem with reading information from client");
                key.cancel();
                socketChannel.close();
                return;
            }

        }
        Object[] messages = ByteObjectConverter.bytesToObjects(byteOutputStream.getBytes(), byteOutputStream.getCount());
        for (Object message : messages) {
            if (message instanceof Message) {
                Message msg = (Message) message;
                switch (msg.getType()) {
                    case ANSWER_LOGIN:
                        events.userLogged(((LoginAnswer) msg).getAnswerType());
                        break;
                    case ANSWER_JOIN:
                        break;
                    case ANSWER_REGISTER:
                        events.userRegistered(((RegisterAnswer) msg).getRegisterAnswerType());
                        break;
                }
            }
        }
        byteOutputStream.close();
    }
}
