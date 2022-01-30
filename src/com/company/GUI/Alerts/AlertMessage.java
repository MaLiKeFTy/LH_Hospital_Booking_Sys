package com.company.GUI.Alerts;

import javax.swing.*;

public class AlertMessage {

    public int DisplayAlert(String[] options,String message) {

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
