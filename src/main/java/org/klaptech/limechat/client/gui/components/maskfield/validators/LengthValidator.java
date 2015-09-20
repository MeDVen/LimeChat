package org.klaptech.limechat.client.gui.components.maskfield.validators;

/**
 * Check text for length
 * Valid if length of text is more than textLength variable
 *
 * @author rlapin
 */
public class LengthValidator implements Validator {

    private int textLength;

    public LengthValidator(int textLength) {
        this.textLength = textLength;
    }

    @Override
    public boolean isValid(String text) {
        return text.length() >= textLength;
    }
}
