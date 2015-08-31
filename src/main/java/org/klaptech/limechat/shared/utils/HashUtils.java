package org.klaptech.limechat.shared.utils;

import java.util.logging.Logger;

/**
 * @author rlapin
 */
public class HashUtils {
    private HashUtils(){

    }
    private static final Logger LOGGER = Logger.getLogger(HashUtils.class.getCanonicalName());
    public static String md5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            LOGGER.severe("md5 algorithm not found");
        }
        return null;
    }

}
