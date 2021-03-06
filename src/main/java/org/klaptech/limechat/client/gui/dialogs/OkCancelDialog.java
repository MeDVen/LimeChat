package org.klaptech.limechat.client.gui.dialogs;


/**
 * Describes behaviour for ok and cancel
 *
 * @author rlapin
 */
public interface OkCancelDialog extends Dialog {
    void setDialogListener(DialogListener listener);
    void onOK();

    void onCancel();
}
