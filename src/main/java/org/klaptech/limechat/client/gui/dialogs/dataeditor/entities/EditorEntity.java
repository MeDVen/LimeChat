package org.klaptech.limechat.client.gui.dialogs.dataeditor.entities;

import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.types.EditorType;
import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.views.EntityView;

/**
 * Entity for using in editor dialog, show have type and name
 *
 * @author rlapin
 */
public interface EditorEntity {
    /**
     * Display as label string
     *
     * @return string value of editor entity
     */
    String getLabelText();

    EntityView getEntityView();

    EditorType getEditableValue();


}
