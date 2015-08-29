package org.klaptech.limechat.client;

import org.klaptech.limechat.shared.general.GeneralMessageFactory;
import org.klaptech.limechat.shared.client.ClientMessageFactory;

import java.io.IOException;
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
            oos.writeObject(ClientMessageFactory.createLoginMessage("admin", "admin"));
            oos.writeObject(GeneralMessageFactory.createSendMessage("hello world"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
