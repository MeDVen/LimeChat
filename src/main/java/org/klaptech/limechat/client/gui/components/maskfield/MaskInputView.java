package org.klaptech.limechat.client.gui.components.maskfield;

import javafx.scene.control.TextField;
import org.klaptech.limechat.client.gui.components.maskfield.validators.Validator;

/**
 * Text wrapper with validator functionality
 *
 * @author rlapin
 */
public class MaskInputView {
    //TODO add listener when text is valid which return text of hint for observer
    private TextField textField;
    public static final String CORRECT = "correct";
    private static final String INCORRECT = "incorrect";
    private boolean isValid = true;

    public MaskInputView(TextField textField, Validator... validators) {
        this.textField = textField;

        validate(textField, validators, textField.getText());
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            validate(textField, validators, newValue);
        });
    }

    /**
     * Apply every validator in validators
     *
     * @param textField
     * @param validators
     * @param value
     */
    private void validate(TextField textField, Validator[] validators, String value) {
        isValid = true;
        for (Validator validator : validators) {
            isValid = isValid & validator.isValid(value);
        }
        textField.setId(isValid ? CORRECT : INCORRECT);
    }

    /**
     * @return text or empty string if validate equal to false
     */
    public String getText() {
        if (isValid) {
            return textField.getText();
        }
        return "";
    }

    public TextField getTextField() {
        return textField;
    }
}
