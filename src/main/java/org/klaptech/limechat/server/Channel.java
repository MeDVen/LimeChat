package org.klaptech.limechat.server;

import org.klaptech.limechat.shared.enums.JoinResultType;

import java.util.HashSet;
import java.util.Set;

/**
 * @author rlapin
 */
public class Channel {
    private String name;
    /**
     * if channel is opened , you don't need to check password
     */
    private boolean isOpened;
    private String password;
    /**
     * Max users on channel
     */
    private int maxUserCount = 10;
    private Set<User> users = new HashSet<>();

    public Channel(String name) {
        this.name = name;
        password = "";
        isOpened = true;
    }

    public Channel(String name, String password) {
        this.name = name;
        this.isOpened = false;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return name+":"+password;
    }

    public JoinResultType join(User user) {
        return join(user,"");
    }

    public JoinResultType join(User user, String password){
        if(users.contains(user)){
            return JoinResultType.ALREADY_ON_CHANNEL;
        }else if(!isOpened && password.equals(this.password)){
            return JoinResultType.INCORRECT_PASSWORD;
        }else{
            users.add(user);
            return JoinResultType.SUCCESS;
        }

    }

}
