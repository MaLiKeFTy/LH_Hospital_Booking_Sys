package com.company.GUI.Base;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class GuiBase implements ActionListener {

    static JFrame _mainFrame;

    protected JFrame GetMainFrame() {

        if (_mainFrame != null) {
            _mainFrame.repaint();
            _mainFrame.setVisible(true);
            return _mainFrame;
        }

        _mainFrame = new JFrame();
        _mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _mainFrame.setTitle("LH_Medical_App");
        _mainFrame.pack();
        _mainFrame.setSize(800, 720);
        _mainFrame.setLocationRelativeTo(null);
        _mainFrame.setLayout(null);
        _mainFrame.setResizable(false);
        _mainFrame.setVisible(true);

        return _mainFrame;
    }

    public abstract void DisplayGUI();


}

