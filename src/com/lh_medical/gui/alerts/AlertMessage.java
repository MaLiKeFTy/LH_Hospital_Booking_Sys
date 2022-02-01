package com.lh_medical.gui.alerts;

import javax.swing.*;

public class AlertMessage {

    public int displayAlert(String[] options, String message) {

        int response = JOptionPane.showOptionDialog(
                null,
                message,
                "Alert",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                0);

        return response;
    }
}
