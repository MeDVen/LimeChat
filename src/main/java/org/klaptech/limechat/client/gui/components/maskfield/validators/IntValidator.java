package org.klaptech.limechat.client.gui.components.maskfield.validators;

/**
 * Validate true if text is number
 *
 * @author rlapin
 */
public class IntValidator implements Validator {
    private static final String UINT_REGEX = "^[^0]?([1-9])+$";

    @Override
    public boolean isValid(String text) {
        return text.matches(UINT_REGEX);
    }
}
