package org.klaptech.limechat.client.gui;

import javafx.scene.image.Image;

/**
 * @author rkushnerev on 07.10.15.
 */
public class GUIConstants {

    private GUIConstants() {}

    static {
    }

    /**
     * Status images
     */
    public static final Image AFK_IMAGE = new Image(GUIConstants.class.getClassLoader().getResourceAsStream("images/circle_blue.png"));
    public static final Image ONLINE_IMAGE = new Image(GUIConstants.class.getClassLoader().getResourceAsStream("images/circle_green.png"));
    public static final Image OFFLINE_IMAGE = new Image(GUIConstants.class.getClassLoader().getResourceAsStream("images/circle_grey.png"));
    public static final Image BUSY_IMAGE = new Image(GUIConstants.class.getClassLoader().getResourceAsStream("images/circle_red.png"));
    public static final Image TYPING_IMAGE = new Image(GUIConstants.class.getClassLoader().getResourceAsStream("images/smile.png")); // TODO get another picture

    /**
     * Toolbar images
     */
    public static final Image SMILE_IMAGE = new Image(GUIConstants.class.getClassLoader().getResourceAsStream("images/smile.png"));

    /**
     * Background
     */
//    public static final Image BACKGROUND_IMAGE = new Image(GUIConstants.class.getClassLoader().getResourceAsStream("images/background.png"));

    /**
     * User
     */
    public static final Image USER_IMAGE = new Image(GUIConstants.class.getClassLoader().getResourceAsStream("images/user.png"));
}
