package org.klaptech.limechat.client.events;

import org.klaptech.limechat.client.gui.GUIManager;
import org.klaptech.limechat.shared.enums.LoginAnswerType;

/**
 * User events such as userlogin , userjoin channel e.t.c
 *
 * @author rlapin
 */
public class UserEvents {
    public UserEvents() {
    }

    public void userLogged(LoginAnswerType loginType) {
        GUIManager.getInstance().getLoginDialog().userLogged(loginType);
    }
}
