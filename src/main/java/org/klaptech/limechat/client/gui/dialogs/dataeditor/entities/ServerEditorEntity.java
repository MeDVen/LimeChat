package org.klaptech.limechat.client.gui.dialogs.dataeditor.entities;

import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.types.EditorType;
import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.types.StringType;
import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.views.EntityView;
import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.views.TextEntityView;

import java.util.ArrayList;
import java.util.List;

/**
 * Server entity for editor
 *
 * @author rlapin
 */
public class ServerEditorEntity implements EditorEntity {
    private List<EditorEntity> entities;

    private ServerEditorEntity() {
        entities = new ArrayList<>();
        entities.add(createServerNameEntity());
    }

    private EditorEntity createServerNameEntity() {
        return new EditorEntity() {
            @Override
            public String getLabelText() {
                return "Server name: "; //TODO add info from localization properties
            }

            @Override
            public EntityView getEntityView() {
                return new TextEntityView(); // TODO masked text with comparison depends on existing servers
            }

            @Override
            public EditorType getEditableValue() {
                return new StringType("");
            }
        };
    }

    public static List<EditorEntity> getEntities() {
        return new ServerEditorEntity().entities;
    }

    @Override
    public String getLabelText() {
        return null;
    }

    @Override
    public EntityView getEntityView() {
        return null;
    }

    @Override
    public EditorType getEditableValue() {
        return null;
    }
}
