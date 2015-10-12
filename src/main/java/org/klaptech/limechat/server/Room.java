package org.klaptech.limechat.server;

import org.klaptech.limechat.shared.Message;
import org.klaptech.limechat.shared.entities.UserInfo;
import org.klaptech.limechat.shared.enums.JoinResultType;
import org.klaptech.limechat.shared.general.GeneralMessageFactory;
import org.klaptech.limechat.shared.server.ServerMessageFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author rlapin
 */
public class Room {
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

    public Room(String name) {
        this.name = name;
        password = "";
        isOpened = true;
    }

    public Room(String name, String password) {
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
        }else if(!isOpened && !password.equals(this.password)){
            return JoinResultType.INCORRECT_PASSWORD;
        }else{
            users.add(user);
            onUserJoin(user);
            return JoinResultType.SUCCESS;
        }

    }

    public void onUserJoin(User joinedUser) {
        users.stream().filter(user -> user != joinedUser).forEach(user -> Server.getInstance().send(user.getSocketChannel(), ServerMessageFactory.createNewUserInRoomMessage(joinedUser.getUserInfo(), name)));
    }


    public List<UserInfo> createUsersInfoList() {
        return users.stream().map(User::getUserInfo).collect(Collectors.toList());
    }

    public void onUserLeave(){
        //TODO notify all room's clients
    }

    /**
     * Send text to all users on channel
     * @param text
     */
    public void sendMessage(String text) {
        Message message = GeneralMessageFactory.createSendMessage(getName(),text);
        for (User user : users) {
            Server.getInstance().send(user.getSocketChannel(), message);
        }

    }

    /**
     * Remove user from channel
     * @param user
     */
    public boolean dropUser(User user) {
        return users.remove(user);
    }
}
