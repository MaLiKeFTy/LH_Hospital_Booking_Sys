package com.lh_medical.gui.alerts;

import javax.swing.*;

public class AlertMessage {


    /**
     * Display gui message to the user.
     */

    public int displayAlert(String[] options, String message) {

        return JOptionPane.showOptionDialog(
                null,
                message,
                "Alert",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                0);
    }
}
