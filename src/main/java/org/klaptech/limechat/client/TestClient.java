package org.klaptech.limechat.client;

import static java.util.logging.Logger.getLogger;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.klaptech.limechat.server.MessageWrapper;
import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.client.ClientMessageFactory;
import org.klaptech.limechat.shared.client.JoinChannelMessage;
import org.klaptech.limechat.shared.enums.LoginAnswerType;
import org.klaptech.limechat.shared.enums.MessageType;
import org.klaptech.limechat.shared.general.GeneralMessageFactory;
import org.klaptech.limechat.shared.server.JoinChannelAnswer;
import org.klaptech.limechat.shared.server.LoginAnswer;
import org.klaptech.limechat.shared.utils.ByteObjectConverter;


/**
 * @author rlapin
 */
public class TestClient {
    private static final Logger LOGGER = getLogger(TestClient.class.getName());
    private static ByteBuffer readBuffer = ByteBuffer.allocate(8196);
    private static SocketChannel socketChannel;

    public static void main(String[] args) {
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost", 3306));
            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_READ);
            while (!socketChannel.finishConnect()) {

            }
            Message message = ClientMessageFactory.createLoginMessage("admin", "admin".getBytes());
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            if (selector.select() == 0) {
                                continue;
                            }
                            Set<SelectionKey> selectionKeys = selector.selectedKeys();
                            for (SelectionKey selectionKey : selectionKeys) {
                                if(selectionKey.isReadable()){
                                    read(selectionKey);
                                }
                            }
                            selectionKeys.clear();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
            thread.setDaemon(true);
            thread.start();
            ByteBuffer byteBuffer = ByteBuffer.wrap(ByteObjectConverter.objectToBytes(message));
            socketChannel.write(byteBuffer);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                message = GeneralMessageFactory.createSendMessage(str);
                byteBuffer = ByteBuffer.wrap(ByteObjectConverter.objectToBytes(message));
                socketChannel.write(byteBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void read(SelectionKey key) throws IOException {
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
        for (Object  message : messages) {
            if(message instanceof Message) {
                Message msg = (Message) message;
                if(msg.getType() == MessageType.ANSWER_LOGIN){
                    if(((LoginAnswer)msg).getAnswerType() == LoginAnswerType.SUCCESS){
                        ByteBuffer byteBuffer = ByteBuffer.wrap(ByteObjectConverter.objectToBytes(ClientMessageFactory.createJoinChannelMessage("default" +
                                "")));
                        socketChannel.write(byteBuffer);
                    }
                }else if(msg.getType() == MessageType.ANSWER_JOIN){
                    System.out.println(((JoinChannelAnswer)msg).getJoinAnswer());
                }else {
                    System.out.println(message);
                }
            }
        }
        byteOutputStream.close();
    }
}
