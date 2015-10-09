package org.klaptech.limechat.server.auth.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * db connector, for connection to sqlite db
 * @author rlapin
 */
public enum Connector {
    INSTANCE;
    private static final Logger LOGGER = Logger.getLogger(Connector.class.getCanonicalName());
    private Connection connection;

    /**
     * Establish jdbc connection
     */
    public void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+getClass().getResource("users.db").getPath());
        } catch (ClassNotFoundException e) {
            LOGGER.severe("driver not found");
        } catch (SQLException e) {
            LOGGER.severe("db file not found");
        }
    }

    /**
     * @return connection
     */
    public synchronized Connection getConnection() {
        if(connection == null){
            connect();
        }
        return connection;
    }



    /**
     * Close jdbc connection
     */
    public void close(){
        try {
            if(connection!=null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            LOGGER.severe(e.getMessage());
        }
    }


}
