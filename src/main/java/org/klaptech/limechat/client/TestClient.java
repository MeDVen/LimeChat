package org.klaptech.limechat.client;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.client.ClientMessageFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;


/**
 * @author rlapin
 */
public class TestClient {
    private static final Logger LOGGER = getLogger(TestClient.class.getName());
    public static void main(String[] args) {
        try(SocketChannel socketChannel = SocketChannel.open()){
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost",3306));
            while(!socketChannel.finishConnect()){

            }
                Message message = ClientMessageFactory.createLoginMessage("admin", "admin");
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutput out = new ObjectOutputStream(bos);
                out.writeObject(message);
                ByteBuffer byteBuffer = ByteBuffer.wrap(bos.toByteArray());
                socketChannel.write(byteBuffer);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
