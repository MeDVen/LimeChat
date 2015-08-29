package org.klaptech.limechat.server.auth;

/**
 * Authorize users on server
 * @author rlapin
 */
public class Authorizer {
    /**
     * @param username
     * @param password
     * @return {@code true}, if auth is succeed
     */
    public static synchronized boolean auth(String username, String password){
        return true;
    }
}
