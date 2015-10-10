package org.klaptech.limechat.client.gui.components.maskfield.validators;

import java.util.List;

/**
 * Validate success if string is not contains in list
 *
 * @author rlapin
 */
public class ListNotContainsValidator implements Validator {
    private List<String> list;

    public ListNotContainsValidator(List<String> list) {
        this.list = list;
    }

    @Override
    public boolean isValid(String text) {
        return !list.contains(text);
    }
}
