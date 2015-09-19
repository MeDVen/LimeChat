package org.klaptech.limechat.shared.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/**
 * Utils for hashing data
 * @author rlapin
 */
public class HashUtils {
    private HashUtils() {

    }

    private static final Logger LOGGER = Logger.getLogger(HashUtils.class.getCanonicalName());

    /**
     * Generate md5 hash
     * @param bytes array of bytes
     * @return hash which generated from array of bytes
     */
    public static String md5(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < bytes.length; ++i) {
                sb.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.severe("md5 algorithm not found");
        }
        return null;
    }

}
