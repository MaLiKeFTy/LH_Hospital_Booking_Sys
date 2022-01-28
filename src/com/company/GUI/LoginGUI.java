package com.company.GUI;

import com.company.DataBaseConnector;
import com.company.GUI.Base.GuiBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class LoginGUI extends GuiBase {


    JPanel menuTitlePanel = new JPanel();
    JLabel menuTitleLabel = new JLabel("LH Medical Menu: Login");


    JPanel authenticationPanel = new JPanel();

    JLabel emailLabel = new JLabel("Email");
    JTextField emailTextField = new JTextField();
    JLabel passwordLabel = new JLabel("Password");
    JPasswordField passwordTextField = new JPasswordField();


    JPanel menuButtonsPanel = new JPanel();
    JButton logInButton = new JButton("Log In");
    JButton goBackButton = new JButton("Go Back");


    @Override
    public void DisplayGUI() {

        menuTitlePanel.setBounds(100, 150, 600, 150);
        menuTitlePanel.setBackground(Color.BLUE);
        menuTitlePanel.setLayout(new GridBagLayout());

        menuTitleLabel.setForeground(Color.white);
        menuTitleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        menuTitlePanel.add(menuTitleLabel);

        authenticationPanel.setLayout(new GridLayout(2,2,20,20));
        authenticationPanel.setBorder(BorderFactory.createEmptyBorder(60,20,60,20));
        authenticationPanel.setBounds(100, 300, 600, 200);
        authenticationPanel.setBackground(Color.BLUE);

        emailLabel.setForeground(Color.WHITE);
        authenticationPanel.add(emailLabel);

        emailTextField.setBounds(100,20,165,25);
        authenticationPanel.add(emailTextField);

        passwordLabel.setForeground(Color.WHITE);
        authenticationPanel.add(passwordLabel);

        passwordTextField.setBounds(100,50,165,25);
        authenticationPanel.add(passwordTextField);

        menuButtonsPanel.setBounds(100, 500, 600, 100);
        menuButtonsPanel.setBorder(BorderFactory.createEmptyBorder(30,20,30,20));
        menuButtonsPanel.setLayout(new GridLayout(1,0,20,20));
        menuButtonsPanel.setBackground(Color.BLUE);

        logInButton.addActionListener(this);
        logInButton.setFont(new Font("Serif", Font.BOLD,30));
        logInButton.setBackground(Color.WHITE);
        logInButton.setForeground(Color.BLUE);
        menuButtonsPanel.add(logInButton);

        goBackButton.addActionListener(this);
        goBackButton.setFont(new Font("Serif", Font.BOLD,30));
        goBackButton.setBackground(Color.WHITE);
        goBackButton.setForeground(Color.BLUE);
        menuButtonsPanel.add(goBackButton);

        GetMainFrame().add(menuTitlePanel);
        GetMainFrame().add(menuButtonsPanel);
        GetMainFrame().add(authenticationPanel);
        GetMainFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == goBackButton){
            GetMainFrame().getContentPane().removeAll();

            MainMenuGUI mainMenuGUI = new MainMenuGUI();
            mainMenuGUI.DisplayGUI();
        }else{
            DataBaseConnector dataBaseConnector = new DataBaseConnector();
            try {
                String loginResult = dataBaseConnector.LoginMessage(emailTextField.getText(),passwordTextField.getText());
                System.out.println(loginResult);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

    }
}
