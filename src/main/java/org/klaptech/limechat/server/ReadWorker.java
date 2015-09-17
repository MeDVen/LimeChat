package org.klaptech.limechat.server;

import org.klaptech.limechat.shared.Message;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Worker with input information
 * Convert bytes to message and work with that
 * @author rlapin
 */
public class ReadWorker implements Runnable{
    private static final Logger LOGGER = getLogger(ReadWorker.class.getName());

    private List<Message> queue = new ArrayList<>();

    public void processData(SocketChannel socket, byte[] data, int count) {
        byte[] dataCopy = new byte[count];
        System.arraycopy(data, 0, dataCopy, 0, count);
        synchronized(queue) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));
                queue.add((Message) objectInputStream.readObject());
                queue.notify();
            } catch (IOException e) {
                try {
                    LOGGER.severe("Error while reading data from:"+socket.getLocalAddress());
                } catch (IOException e1) {
                    LOGGER.severe("Error while getting socket address");
                }
            } catch (ClassNotFoundException e) {
                try {
                    LOGGER.severe("Reading incorrect object from client:" + socket.getLocalAddress());
                } catch (IOException e1) {
                    LOGGER.severe("Error while getting socket address");
                }
            }

        }
    }

    public void run() {
        Message message;

        while(true) {
            // Wait for data to become available
            synchronized(queue) {
                while(queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                    }
                }
                message = queue.remove(0);
            }

            // Return to sender
            System.out.println(message);
        }
    }
}
