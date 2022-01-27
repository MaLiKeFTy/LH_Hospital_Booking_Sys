package com.company.GUI;

import com.company.GUI.Base.GuiBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenuGUI extends GuiBase {

    JPanel menuTitlePanel = new JPanel();
    JLabel menuTitleLabel = new JLabel("LH Medical Menu");
    JPanel menuButtonsPanel = new JPanel();
    JButton logInButton = new JButton("Log In");
    JButton registerButton = new JButton("Register");

    @Override
    public void DisplayGUI() {

        menuTitlePanel.setBounds(100, 200, 600, 150);
        menuTitlePanel.setBackground(Color.BLUE);
        menuTitlePanel.setLayout(new GridBagLayout());

        menuTitleLabel.setForeground(Color.white);
        menuTitleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        menuTitlePanel.add(menuTitleLabel);

        menuButtonsPanel.setBounds(100, 350, 600, 150);
        menuButtonsPanel.setBorder(BorderFactory.createEmptyBorder(50, 30, 50, 30));
        menuButtonsPanel.setLayout(new GridLayout(1, 0, 20, 20));
        menuButtonsPanel.setBackground(Color.BLUE);


        logInButton.addActionListener(this);
        logInButton.setFont(new Font("Serif", Font.BOLD, 30));
        logInButton.setBackground(Color.WHITE);
        logInButton.setForeground(Color.BLUE);
        menuButtonsPanel.add(logInButton);

        registerButton.addActionListener(this);
        registerButton.setFont(new Font("Serif", Font.BOLD, 30));
        registerButton.setBackground(Color.WHITE);
        registerButton.setForeground(Color.BLUE);
        menuButtonsPanel.add(registerButton);

        GetMainFrame().add(menuButtonsPanel);
        GetMainFrame().add(menuTitlePanel);

        GetMainFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        GetMainFrame().getContentPane().removeAll();

        if (e.getSource() == logInButton) {
            LoginGUI loginGUI = new LoginGUI();
            loginGUI.DisplayGUI();
        }
        else {
            RegisterGUI registerGUI = new RegisterGUI();
            registerGUI.DisplayGUI();
        }
    }
}
