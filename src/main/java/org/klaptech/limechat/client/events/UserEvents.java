package org.klaptech.limechat.client.events;

import org.klaptech.limechat.client.gui.GUIManager;
import org.klaptech.limechat.shared.enums.LoginAnswerType;
import org.klaptech.limechat.shared.enums.RegisterAnswerType;

/**
 * User events such as userlogin , userjoin channel e.t.c
 *
 * @author rlapin
 */
public class UserEvents {

    /**
     * user try to logging on server
     *
     * @param loginType {@link LoginAnswerType}
     */
    public void userLogged(LoginAnswerType loginType) {
        GUIManager.getInstance().getLoginDialog().userLogged(loginType);
    }

    /**
     * user try to register
     *
     * @param registerAnswerType {@link RegisterAnswerType}
     */
    public void userRegistered(RegisterAnswerType registerAnswerType) {
        GUIManager.getInstance().getLoginDialog().userRegistered(registerAnswerType);
    }
}
