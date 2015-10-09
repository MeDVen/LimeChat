package org.klaptech.limechat.client.gui.dialogs.dataeditor.entities;

import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.types.AddressType;
import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.types.EditorType;
import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.types.StringType;
import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.views.EntityView;
import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.views.ServerAdressView;
import org.klaptech.limechat.client.gui.dialogs.dataeditor.entities.views.TextEntityView;
import org.klaptech.limechat.client.net.ServerAddress;

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
        entities.add(createServerAddrEntity());
    }

    private EditorEntity createServerAddrEntity() {
        return new EditorEntity() {
            @Override
            public String getLabelText() {
                return ""; //TODO add info from localization properties
            }

            @Override
            public EntityView getEntityView() {
                return new ServerAdressView(this); // TODO masked text with comparison depends on existing servers
            }

            @Override
            public EditorType getEditableValue() {
                return new AddressType(new ServerAddress("", AddressType.DEFAULT_PORT));
            }
        };
    }

    private EditorEntity createServerNameEntity() {
        return new EditorEntity() {
            @Override
            public String getLabelText() {
                return "Server name: "; //TODO add info from localization properties
            }

            @Override
            public EntityView getEntityView() {
                return new TextEntityView(this); // TODO masked text with comparison depends on existing servers
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
