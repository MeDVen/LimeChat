package org.klaptech.limechat.shared.utils;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

/**
 * Utils for converting bytes to object and object to bytes
 *
 * @author rlapin
 */
public class ByteObjectConverter {
    private static final Logger LOGGER = getLogger(ByteObjectConverter.class.getName());
    private ByteObjectConverter() {
    }

    /**
     * Convert bytes to object
     * @param bytes array of bytes
     * @param count num of bytes to convert
     * @return object which is result of byte conversion or null if operation is failed
     */
    public static Serializable bytesToObject(byte[] bytes, int count) {
        ByteInputStream byteInputStream = new ByteInputStream(bytes, count);
        try {
            ObjectInput objectInput = new ObjectInputStream(byteInputStream);
            return (Serializable) objectInput.readObject();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Convert object to bytes
     * @param object object to serialize, must implements serializable
     * @return array of bytes or empty array if operation is failed
     */
    public static byte[] objectToBytes(Serializable object){
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);
            objectOutputStream.writeObject(object);
            return byteOutputStream.getBytes();
        } catch (IOException e) {
            return new byte[0];
        }
    }
}
