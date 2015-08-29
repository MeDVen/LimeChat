package org.klaptech.limechat.server.io;

import org.klaptech.limechat.server.Client;
import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.general.GeneralMessageFactory;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

/**
 * Send message to clients, should be thread-safe
 * @author rlapin
 */
public class Writer {
    private static final Logger LOGGER = Logger.getLogger(Writer.class.getCanonicalName());

    /**
     * Send message to client
     * @param client
     * @param message {@link Message} - create using {@link GeneralMessageFactory}
     */
    public synchronized static void write(Client client, Message message){
        try {
            OutputStream outputStream = client.getSocket().getOutputStream();
            new ObjectOutputStream(outputStream).writeObject(message);
        } catch (IOException e) {
            LOGGER.severe("Ошибка");
        }
    }
}
