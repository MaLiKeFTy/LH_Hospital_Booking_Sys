package com.lh_medical.gui.base;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class GuiBase implements ActionListener {

    public static JFrame mainFrame;

    protected JFrame getMainFrame() {

        if (mainFrame != null) {
            mainFrame.repaint();
            mainFrame.setVisible(true);
            return mainFrame;
        }

        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("LH_Medical_App");
        mainFrame.pack();
        mainFrame.setSize(800, 720);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

        return mainFrame;
    }

    public abstract void displayGUI();


}

