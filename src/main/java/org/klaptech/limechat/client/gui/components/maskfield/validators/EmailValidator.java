package org.klaptech.limechat.client.gui.components.maskfield.validators;

/**
 * @author rlapin
 */
public class EmailValidator implements Validator {
    private final static String EMAIL_REGEX = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";

    @Override
    public boolean isValid(String text) {
        return text.matches(EMAIL_REGEX);
    }
}
