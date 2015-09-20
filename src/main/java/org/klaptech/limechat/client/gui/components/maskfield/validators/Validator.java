package org.klaptech.limechat.client.gui.components.maskfield.validators;

/**
 * Validator for MaskInputView
 *
 * @author rlapin
 */
public interface Validator {
    /**
     * Check maskinputview content if is valid
     *
     * @param text content of maskinputiview
     * @return true if content is correct
     */
    public boolean isValid(String text);
}
