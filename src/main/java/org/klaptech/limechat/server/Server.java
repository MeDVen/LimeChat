package org.klaptech.limechat.server;


import org.klaptech.limechat.server.conf.Configuration;
import org.klaptech.limechat.server.conf.DefaultConfiguration;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


/**
 * Server entity
 *
 * @author rlapin
 */
public class Server {
    private static final Logger LOGGER = Logger.getLogger(Server.class.getCanonicalName());
    public static final int N_THREADS = 4;
    private Selector selector;
    private Configuration config;
    private ByteBuffer readBuffer = ByteBuffer.allocate(8192);
    private ReadWorker readWorker = new ReadWorker();


    public Server(Configuration config) {
        this.config = config;


    }

    private void start() {
        try (
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ) {
            selector = Selector.open();
            serverSocketChannel.bind(new InetSocketAddress(config.getAddr(), config.getPort()));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                int num = selector.select();
                if (num == 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey key : selectionKeys) {
                    if (!key.isValid()) {
                        continue;
                    }
                    if (key.isConnectable()) {
                        LOGGER.info("User[ip:%s] disconnected");
                    }
                    if (key.isAcceptable()) {
                        accept(key);
                    } else if (key.isReadable()) {
                        read(key);
                    } else if (key.isWritable()) {
                        write(key);
                    }
                }
                selectionKeys.clear();
            }

        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * Write data to client
     *
     * @param key selectionKey with connection information
     */
    private void write(SelectionKey key) {

    }

    /**
     * Read data from client
     *
     * @param key selectionKey with connection information
     */
    private void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        readBuffer.clear();
        int numRead;
        try {
            numRead = socketChannel.read(readBuffer);
        } catch (IOException e) {
            LOGGER.severe("Problem with reading information from client");
            key.cancel();
            socketChannel.close();
            return;
        }
        if (numRead == -1) {
            key.channel().close();
            key.cancel();
            return;
        }
        readWorker.processData(socketChannel,readBuffer.array(),numRead);
    }

    /**
     * Accept connections and if success register socketchannel for reading
     *
     * @param key selectionkey with connection info
     * @throws IOException Problem with accepting connection
     */
    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        Socket socket = socketChannel.socket();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        LOGGER.info(String.format("User[ip:%s] connected successfully", socket.getLocalAddress()));
    }


    public static void main(String[] args) {
        Server server = new Server(new DefaultConfiguration());
        server.run();
    }

    /**
     * Run server
     */
    private void run() {

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                start();
            }
        });
        startReadWorker();
    }

    /**
     * Start worker for reading data from clients
     */
    private void startReadWorker() {

        Executors.newFixedThreadPool(N_THREADS).submit(readWorker);
    }


}
