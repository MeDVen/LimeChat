package org.klaptech.limechat.server;

import static java.util.logging.Logger.getLogger;






import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.klaptech.limechat.server.auth.Authorizer;
import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.client.LoginMessage;
import org.klaptech.limechat.shared.general.SendMessage;
import org.klaptech.limechat.shared.server.LoginAnswer;
import org.klaptech.limechat.shared.server.ServerMessageFactory;
import org.klaptech.limechat.shared.utils.ByteObjectConverter;

/**
 * Worker with input information
 * Convert bytes to message and work with that
 * @author rlapin
 */
public class ReadWorker implements Runnable{
    private static final Logger LOGGER = getLogger(ReadWorker.class.getName());

    private final List<MessageWrapper> queue = new ArrayList<>();
    private Server server;

    public ReadWorker(final Server server) {
        this.server = server;
    }

    public void processData(SocketChannel socket, ByteOutputStream byteOutputStream) {
        synchronized(queue) {
            Object[] messages = ByteObjectConverter.bytesToObjects(byteOutputStream.getBytes(), byteOutputStream.getCount());
            for (Object  message : messages) {
                if(message instanceof Message) {
                    queue.add(new MessageWrapper(socket, (Message) message));
                }
            }
            queue.notify();

        }
    }

    public void run() {
        MessageWrapper messageWrapper;

        while(true) {
            // Wait for data to become available
            synchronized(queue) {
                while(queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                    }
                }
                messageWrapper = queue.remove(0);
            }
            Message message = messageWrapper.getMessage();
            // Return to sender
            switch(message.getType()){
                case LOGIN:
                    LoginMessage loginMessage = (LoginMessage) message;
                    LoginAnswer.TYPE auth = Authorizer.auth(loginMessage.getUsername(), loginMessage.getPassword());
                    LOGGER.info("LOGIN "+auth);
                    server.send(messageWrapper.getSocket(), ServerMessageFactory.createLoginAnswer(auth));
                    break;
                case MSG:
                    try {
                        System.out.println(String.format("%s sends: %s",messageWrapper.getSocket().getRemoteAddress().toString(),((SendMessage) message).getMessage()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
            }

        }
    }
}
