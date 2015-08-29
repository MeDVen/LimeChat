package org.klaptech.limechat.client;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.MessageType;
import org.klaptech.limechat.shared.client.ClientMessageFactory;
import org.klaptech.limechat.shared.general.GeneralMessageFactory;
import org.klaptech.limechat.shared.server.LoginAnswer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author rlapin
 */
public class TestClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost",3306);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ObjectInputStream ois = null;
                    try {
                        ois = new ObjectInputStream(socket.getInputStream());
                        while(true) {
                            Message msg = (Message) ois.readObject();
                            if (msg.getType() == MessageType.ANSWER_LOGIN) {
                                System.out.println(((LoginAnswer) msg).getAnswerType());
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (ois != null) {
                                ois.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();
            oos.writeObject(ClientMessageFactory.createLoginMessage("admin", "a1dmin"));
            oos.writeObject(GeneralMessageFactory.createSendMessage("hello world"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
