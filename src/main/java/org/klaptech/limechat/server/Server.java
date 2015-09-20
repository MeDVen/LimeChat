package org.klaptech.limechat.server;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.klaptech.limechat.server.conf.Configuration;
import org.klaptech.limechat.server.conf.DefaultConfiguration;
import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.utils.ByteObjectConverter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Server entity
 * Singletone
 * @author rlapin
 */
public class Server {
    private static Server instance;
    private static final Logger LOGGER = Logger.getLogger(Server.class.getCanonicalName());
    private Selector selector;
    private Configuration config;
    private ByteBuffer readBuffer = ByteBuffer.allocate(10);
    private ReadWorker readWorker = new ReadWorker();
    private final List<ChangeRequest> changeRequests = new ArrayList<>();
    private final Map<SocketChannel, List<Message>> pendingData = new HashMap<>();
    private final Set<User> users = new HashSet<>();
    private Channels channels;

    private Server(Configuration config) {
        this.config = config;
        channels = new Channels();

    }
    public static Server getInstance(){
        if(instance == null){
            instance = new Server(new DefaultConfiguration());
        }
        return instance;
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

                synchronized (changeRequests) {
                    for (ChangeRequest changeRequest : changeRequests) {
                        switch (changeRequest.type) {
                            case ChangeRequest.CHANGEOPS:
                                changeRequest.socket.keyFor(selector).interestOps(changeRequest.ops);
                                break;
                            default:
                        }
                    }
                    changeRequests.clear();
                }
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
    private void write(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        synchronized (pendingData) {
            List<Message> sendData = pendingData.get(socketChannel);
            while (!sendData.isEmpty()) {
                socketChannel.write(ByteBuffer.wrap(ByteObjectConverter.objectToBytes(sendData.get(0))));
                sendData.remove(0);
            }
            if (sendData.isEmpty()) {
                key.interestOps(SelectionKey.OP_READ);
            }
        }
    }

    /**
     * Read data from client
     *
     * @param key selectionKey with connection information
     */
    private void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();

        ByteOutputStream byteOutputStream = new ByteOutputStream();
        int numRead = -1;
        while (numRead != 0) {
            readBuffer.clear();
            try {
                numRead = socketChannel.read(readBuffer);
                if (numRead == -1) {

                    //   key.cancel();
                    //   socketChannel.close();
                    // User disconnected or lost connection
                    key.cancel();
                    socketChannel.close();
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
        readWorker.processData(socketChannel, byteOutputStream);
        byteOutputStream.close();
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
        Server.getInstance().run();
    }

    /**
     * Run server
     */
    private void run() {

        Executors.newSingleThreadExecutor().execute(() -> start());
        startReadWorker();
    }

    /**
     * Start worker for reading data from clients
     */
    private void startReadWorker() {

        Executors.newSingleThreadExecutor().submit(readWorker);
    }

    public void send(SocketChannel socket, Message message) {
        synchronized (changeRequests) {
            // Indicate we want the interest ops set changed
            changeRequests.add(new ChangeRequest(socket, ChangeRequest.CHANGEOPS, SelectionKey.OP_WRITE));

            // And queue the data we want written
            synchronized (this.pendingData) {
                List<Message> queue = this.pendingData.get(socket);
                if (queue == null) {
                    queue = new ArrayList<>();
                    this.pendingData.put(socket, queue);
                }
                queue.add(message);
            }
        }

        //Wake up selector
        this.selector.wakeup();
    }

    public Channels getChannels() {
        return channels;
    }

    /**
     * Get user by socketchannel
     * @param socket
     * @return user if he's already connected, if there is no user with such socketchannel
     */
    public User getUser(SocketChannel socket) {
        for(User user : users){
            if(user.getSocketChannel().equals(socket)){
                return user;
            }
        }
        return null;
    }

    /**
     * Add user to server
     * @param user
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Check if server contains user
     *
     * @param username
     * @return true , if user has already connected
     */
    public boolean containsUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
