package org.klaptech.limechat.client.gui.components.maskfield.validators;

/**
 * Validate as true if text length less or equal to size
 *
 * @author rlapin
 */
public class MaxLengthValidator implements Validator {

    private int size;

    public MaxLengthValidator(int size) {
        this.size = size;
    }

    @Override
    public boolean isValid(String text) {
        return text.length() <= size;
    }
}
