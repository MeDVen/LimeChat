package org.klaptech.limechat.server.auth;

import org.klaptech.limechat.server.auth.db.Connector;
import org.klaptech.limechat.shared.enums.LoginAnswerType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    public static synchronized LoginAnswerType auth(String username, String password){
        Connection connection = Connector.INSTANCE.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from users WHERE username='"+username+"'");
            if(resultSet.next()){
                if(resultSet.getString("password").equals(password)){
                    return LoginAnswerType.SUCCESS; //TODO добавить проверка на то что юзер уже авторизован
                }else{
                    return LoginAnswerType.INCORRECT_PASSWORD;
                }

            }else{
                return LoginAnswerType.USER_NOT_EXISTS;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return LoginAnswerType.USER_ALREADY_CON;
    }
}
