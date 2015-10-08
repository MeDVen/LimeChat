package org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.types;

/**
 * @author rlapin
 */
public class StringType implements EditorType {

    private String value;

    public StringType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }


}
