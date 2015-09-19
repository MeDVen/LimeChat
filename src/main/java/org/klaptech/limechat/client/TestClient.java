package org.klaptech.limechat.client;

import static java.util.logging.Logger.getLogger;






import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.logging.Logger;
import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.client.ClientMessageFactory;
import org.klaptech.limechat.shared.general.GeneralMessageFactory;
import org.klaptech.limechat.shared.utils.ByteObjectConverter;


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
            Message message = ClientMessageFactory.createLoginMessage("admin", "admin".getBytes());
            ByteBuffer byteBuffer = ByteBuffer.wrap(ByteObjectConverter.objectToBytes(message));
            socketChannel.write(byteBuffer);
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNextLine()){
                String str = scanner.nextLine();
                message = GeneralMessageFactory.createSendMessage(str);
                byteBuffer = ByteBuffer.wrap(ByteObjectConverter.objectToBytes(message));
                socketChannel.write(byteBuffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
