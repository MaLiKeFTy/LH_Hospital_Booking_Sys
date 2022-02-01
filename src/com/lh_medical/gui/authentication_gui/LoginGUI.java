package com.lh_medical.gui.authentication_gui;

import com.lh_medical.authentication.Login;
import com.lh_medical.db.DataBaseManager;
import com.lh_medical.gui.MainMenuGUI;
import com.lh_medical.gui.alerts.AlertMessage;
import com.lh_medical.gui.base.GuiBase;
import com.lh_medical.gui.profiles_gui.base.ProfileGUI;
import com.lh_medical.gui.profiles_gui.factory.ProfileGuiFactory;
import com.lh_medical.users.base.User;

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
    public void displayGUI() {

        menuTitlePanel.setBounds(100, 150, 600, 150);
        menuTitlePanel.setBackground(Color.BLUE);
        menuTitlePanel.setLayout(new GridBagLayout());

        menuTitleLabel.setForeground(Color.white);
        menuTitleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        menuTitlePanel.add(menuTitleLabel);

        authenticationPanel.setLayout(new GridLayout(2, 2, 20, 20));
        authenticationPanel.setBorder(BorderFactory.createEmptyBorder(60, 20, 60, 20));
        authenticationPanel.setBounds(100, 300, 600, 200);
        authenticationPanel.setBackground(Color.BLUE);

        emailLabel.setForeground(Color.WHITE);
        authenticationPanel.add(emailLabel);

        emailTextField.setBounds(100, 20, 165, 25);
        authenticationPanel.add(emailTextField);

        passwordLabel.setForeground(Color.WHITE);
        authenticationPanel.add(passwordLabel);

        passwordTextField.setBounds(100, 50, 165, 25);
        authenticationPanel.add(passwordTextField);

        menuButtonsPanel.setBounds(100, 500, 600, 100);
        menuButtonsPanel.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        menuButtonsPanel.setLayout(new GridLayout(1, 0, 20, 20));
        menuButtonsPanel.setBackground(Color.BLUE);

        logInButton.addActionListener(this);
        logInButton.setFont(new Font("Serif", Font.BOLD, 30));
        logInButton.setBackground(Color.WHITE);
        logInButton.setForeground(Color.BLUE);
        menuButtonsPanel.add(logInButton);

        goBackButton.addActionListener(this);
        goBackButton.setFont(new Font("Serif", Font.BOLD, 30));
        goBackButton.setBackground(Color.WHITE);
        goBackButton.setForeground(Color.BLUE);
        menuButtonsPanel.add(goBackButton);

        getMainFrame().add(menuTitlePanel);
        getMainFrame().add(menuButtonsPanel);
        getMainFrame().add(authenticationPanel);
        getMainFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goBackButton) {
            getMainFrame().getContentPane().removeAll();

            MainMenuGUI mainMenuGUI = new MainMenuGUI();
            mainMenuGUI.displayGUI();
        } else {
            DataBaseManager dataBaseManager = new DataBaseManager();

            User user = null;

            try {

                user = dataBaseManager.loginResult(new Login(emailTextField.getText(), passwordTextField.getText()));

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            AlertMessage alertMessage = new AlertMessage();
            if (user == null) {
                alertMessage.displayAlert(new String[]{"ok"}, "Wrong email or password, please try again.");
            } else {
                getMainFrame().getContentPane().removeAll();
                ProfileGUI profileGUI = ProfileGuiFactory.getProfileGUI(user.get_userType());

                assert profileGUI != null;
                profileGUI.setUser(user);
                profileGUI.displayGUI();
            }

        }

    }
}
