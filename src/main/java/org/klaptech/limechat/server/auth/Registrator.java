package org.klaptech.limechat.server.auth;

import org.klaptech.limechat.server.auth.db.Connector;
import org.klaptech.limechat.shared.enums.RegisterAnswerType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Register users on server
 *
 * @author rlapin
 */
public class Registrator {
    /**
     * @param username
     * @param password
     * @param email
     * @return {@link RegisterAnswerType}
     */
    public static synchronized RegisterAnswerType register(String username, String password, String email) {
        Connection connection = Connector.INSTANCE.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from users WHERE username='" + username + "'");
            if (resultSet.next()) {
                return RegisterAnswerType.USER_ALREADY_REGISTER;
            } else {
                statement.execute("INSERT INTO users VALUES('" + username + "','" + password + "','" + email + "')");
                return RegisterAnswerType.SUCCESS;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connector.INSTANCE.close();
        }
        return RegisterAnswerType.ERROR;
    }
}
