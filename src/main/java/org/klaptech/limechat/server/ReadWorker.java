package org.klaptech.limechat.server;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.klaptech.limechat.server.auth.Authorizer;
import org.klaptech.limechat.server.auth.Registrator;
import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.client.JoinRoomMessage;
import org.klaptech.limechat.shared.client.LeaveRoomMessage;
import org.klaptech.limechat.shared.client.LoginMessage;
import org.klaptech.limechat.shared.client.RegisterMessage;
import org.klaptech.limechat.shared.entities.UserInfo;
import org.klaptech.limechat.shared.enums.JoinResultType;
import org.klaptech.limechat.shared.enums.LeaveType;
import org.klaptech.limechat.shared.enums.LoginAnswerType;
import org.klaptech.limechat.shared.enums.RegisterAnswerType;
import org.klaptech.limechat.shared.enums.UserState;
import org.klaptech.limechat.shared.general.SendMessage;
import org.klaptech.limechat.shared.server.ServerMessageFactory;
import org.klaptech.limechat.shared.utils.ByteObjectConverter;

import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Worker with input information
 * Convert bytes to message and work with that
 *
 * @author rlapin
 */
public class ReadWorker implements Runnable {
    private static final Logger LOGGER = getLogger(ReadWorker.class.getName());

    private final List<MessageWrapper> queue = new ArrayList<>();


    public void processData(SocketChannel socket, ByteOutputStream byteOutputStream) {
        synchronized (queue) {
            Object[] messages = ByteObjectConverter.bytesToObjects(byteOutputStream.getBytes(), byteOutputStream.getCount());
            for (Object message : messages) {
                if (message instanceof Message) {
                    queue.add(new MessageWrapper(socket, (Message) message));
                }
            }
            queue.notify();

        }
    }

    public void run() {
        final Server server = Server.getInstance();
        MessageWrapper messageWrapper;
        while (true) {
            // Wait for data to become available
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                    }
                }
                messageWrapper = queue.remove(0);
            }

            Message message = messageWrapper.getMessage();
            // Return to sender
            SocketChannel socket = messageWrapper.getSocket();
            switch (message.getType()) {
                case LOGIN:
                    LoginMessage loginMessage = (LoginMessage) message;
                    LoginAnswerType authAnswer;
                    if (server.containsUser(loginMessage.getUsername())) {
                        authAnswer = LoginAnswerType.USER_ALREADY_CON;

                    } else {
                        authAnswer = Authorizer.auth(loginMessage.getUsername(), loginMessage.getPassword());
                        if (authAnswer == LoginAnswerType.SUCCESS) {//TODO image must be not null
                            server.addUser(new User(new UserInfo(loginMessage.getUsername(), UserState.ONLINE, null), messageWrapper.getSocket()));
                        }
                    }
                    LOGGER.info("LOGIN " + authAnswer);
                    server.send(socket, ServerMessageFactory.createLoginAnswer(authAnswer));
                    break;
                case JOIN:
                    JoinRoomMessage joinRoomMessage = (JoinRoomMessage) message;
                    Room room = server.getRooms().getChannelByName(joinRoomMessage.getChannelName());
                    JoinResultType joinAnswer;
                    if (room == Rooms.DUMMY_ROOM) {
                        joinAnswer = JoinResultType.CHANNEL_NOT_FOUND;
                    } else {
                        joinAnswer = room.join(server.getUser(socket));
                    }
                    server.send(socket, ServerMessageFactory.createJoinChannelAnswer(joinAnswer, joinRoomMessage.getChannelName()));
                    if(joinAnswer == JoinResultType.SUCCESS){
                        //server.send(socket, ServerMessageFactory.createUserListMessage());
                    }
                    break;
                case MSG:
                    SendMessage sendMessage = (SendMessage) message;
                    room = server.getRooms().getChannelByName(sendMessage.getChannelName());
                    room.sendMessage(sendMessage.getMessage());
                    break;
                case LEAVE:
                    LeaveRoomMessage leaveRoomMessage = (LeaveRoomMessage) message;
                    room = server.getRooms().getChannelByName(leaveRoomMessage.getChannelName());
                    if (room.dropUser(Server.getInstance().getUser(messageWrapper.getSocket()))) {
                        server.send(messageWrapper.getSocket(), ServerMessageFactory.createLeaveChannelAnswer(LeaveType.USER, leaveRoomMessage.getChannelName()));
                    }
                    break;
                case REGISTER:
                    RegisterMessage registerMessage = (RegisterMessage) message;
                    RegisterAnswerType registerAnswerType = Registrator.register(registerMessage.getUsername(), registerMessage.getPassword(), registerMessage.getEmail());


                    LOGGER.info("Register " + registerAnswerType);
                    server.send(socket, ServerMessageFactory.createRegisterAnswer(registerAnswerType));
                    break;
                default:
            }

        }
    }
}
