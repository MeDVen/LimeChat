package org.klaptech.limechat.client.gui.components.maskfield;

import javafx.scene.control.TextField;
import org.klaptech.limechat.client.gui.components.maskfield.validators.Validator;

/**
 * Text wrapper with validator functionality
 *
 * @author rlapin
 */
public class MaskInputView {

    private TextField textField;
    public static final String CORRECT = "correct";
    private static final String INCORRECT = "incorrect";
    private boolean isValid = true;

    public MaskInputView(TextField textField, Validator validator) {
        this.textField = textField;
        validate(textField, validator, textField.getText());
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            validate(textField, validator, newValue);
        });
    }

    private void validate(TextField textField, Validator validator, String newValue) {
        isValid = validator.isValid(newValue);
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
