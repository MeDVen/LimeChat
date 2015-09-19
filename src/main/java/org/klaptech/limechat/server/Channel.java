package org.klaptech.limechat.server;

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
}
