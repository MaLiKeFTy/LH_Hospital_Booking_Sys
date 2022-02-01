package com.lh_medical.gui.authentication_gui;

import com.lh_medical.db.DataBaseManager;
import com.lh_medical.gui.MainMenuGUI;
import com.lh_medical.gui.alerts.AlertMessage;
import com.lh_medical.gui.base.GuiBase;
import com.lh_medical.users.Client;
import com.lh_medical.users.Practitioner;
import com.lh_medical.users.base.User;
import com.lh_medical.users.user_info.UserGender;
import com.lh_medical.users.user_info.UserName;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RegisterGUI extends GuiBase {

    JPanel menuTitlePanel = new JPanel();
    JLabel menuTitleLabel = new JLabel("LH Medical Menu: Register");


    JPanel authenticationPanel = new JPanel();

    JLabel firstNameLabel = new JLabel("First Name");
    JTextField firstNameTextField = new JTextField();

    JLabel lastNameLabel = new JLabel("Last Name");
    JTextField lastNameTextField = new JTextField();

    JLabel emailLabel = new JLabel("Email");
    JTextField emailTextField = new JTextField();

    JLabel ageLabel = new JLabel("Age");
    JComboBox ageComboBox = new JComboBox(ageRange());

    JLabel genderLabel = new JLabel("Gender");
    JComboBox genderComboBox = new JComboBox(genders());

    JLabel passwordLabel = new JLabel("Password");
    JPasswordField passwordTextField = new JPasswordField();

    JLabel repeatPasswordLabel = new JLabel("Repeat Password");
    JPasswordField repeatPasswordTextField = new JPasswordField();


    JLabel userTypeLabel = new JLabel("Account Type");
    JComboBox userTypeBox = new JComboBox(accountTypes());

    JPanel menuButtonsPanel = new JPanel();
    JButton logInButton = new JButton("Register");
    JButton goBackButton = new JButton("Go Back");


    @Override
    public void displayGUI() {

        menuTitlePanel.setBounds(100, 20, 600, 150);
        menuTitlePanel.setBackground(Color.BLUE);
        menuTitlePanel.setLayout(new GridBagLayout());

        menuTitleLabel.setForeground(Color.white);
        menuTitleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        menuTitlePanel.add(menuTitleLabel);


        JScrollPane authenticationScrollPane = new JScrollPane(authenticationPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        authenticationPanel.setLayout(new GridLayout(9, 2, 20, 20));
        authenticationPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        authenticationPanel.setBounds(100, 170, 600, 400);
        authenticationPanel.setBackground(Color.BLUE);


        firstNameLabel.setForeground(Color.WHITE);
        authenticationPanel.add(firstNameLabel);

        firstNameTextField.setBounds(100, 20, 165, 25);
        authenticationPanel.add(firstNameTextField);


        lastNameLabel.setForeground(Color.WHITE);
        authenticationPanel.add(lastNameLabel);

        lastNameTextField.setBounds(100, 20, 165, 25);
        authenticationPanel.add(lastNameTextField);


        emailLabel.setForeground(Color.WHITE);
        authenticationPanel.add(emailLabel);

        emailTextField.setBounds(100, 20, 165, 25);
        authenticationPanel.add(emailTextField);


        ageLabel.setForeground(Color.WHITE);
        authenticationPanel.add(ageLabel);

        ageComboBox.setBounds(100, 20, 165, 25);
        authenticationPanel.add(ageComboBox);


        genderLabel.setForeground(Color.WHITE);
        authenticationPanel.add(genderLabel);

        genderComboBox.setBounds(100, 20, 165, 25);
        authenticationPanel.add(genderComboBox);

        passwordLabel.setForeground(Color.WHITE);
        authenticationPanel.add(passwordLabel);

        passwordTextField.setBounds(100, 50, 165, 25);
        authenticationPanel.add(passwordTextField);


        repeatPasswordLabel.setForeground(Color.WHITE);
        authenticationPanel.add(repeatPasswordLabel);

        repeatPasswordTextField.setBounds(100, 50, 165, 25);
        authenticationPanel.add(repeatPasswordTextField);


        userTypeLabel.setForeground(Color.WHITE);
        authenticationPanel.add(userTypeLabel);

        userTypeBox.setBounds(100, 20, 165, 25);
        authenticationPanel.add(userTypeBox);


        menuButtonsPanel.setBounds(100, 565, 600, 80);
        menuButtonsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        menuButtonsPanel.setLayout(new GridLayout(1, 0, 20, 20));
        menuButtonsPanel.setBackground(Color.BLUE);

        logInButton.addActionListener(this);
        logInButton.setBackground(Color.WHITE);
        logInButton.setForeground(Color.BLUE);
        menuButtonsPanel.add(logInButton);

        goBackButton.addActionListener(this);
        goBackButton.setBackground(Color.WHITE);
        goBackButton.setForeground(Color.BLUE);
        menuButtonsPanel.add(goBackButton);

        getMainFrame().add(menuTitlePanel);
        getMainFrame().add(menuButtonsPanel);
        getMainFrame().add(authenticationPanel);
        getMainFrame().add(authenticationScrollPane);
        getMainFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == goBackButton) {
            getMainFrame().getContentPane().removeAll();
            MainMenuGUI mainMenuGUI = new MainMenuGUI();
            mainMenuGUI.displayGUI();
        } else {

            AlertMessage alertMessage = new AlertMessage();
            if (!detailsAreFilled()) {
                alertMessage.displayAlert(new String[]{"ok"}, "Please fill all details before registering.");
                return;
            }

            if (!samePassword()) {
                alertMessage.displayAlert(new String[]{"ok"}, "Password and repeat password and not matching, please try again.");
                return;
            }


            UserName userName = new UserName(firstNameTextField.getText().toLowerCase(Locale.ROOT), lastNameTextField.getText().toLowerCase(Locale.ROOT));
            String userEmail = emailTextField.getText().toLowerCase(Locale.ROOT);
            String userPassword = passwordTextField.getText();
            String userAgeText = ageComboBox.getSelectedItem().toString();
            int userAge = Integer.parseInt(userAgeText);
            UserGender userGender = UserGender.values()[genderComboBox.getSelectedIndex()];

            User newUser = null;
            switch (userTypeBox.getSelectedIndex()) {
                case 0:
                    newUser = new Client(userName,
                            userEmail,
                            userPassword,
                            userAge,
                            userGender);
                    break;
                case 1:
                    newUser = new Practitioner(userName,
                            userEmail,
                            userPassword,
                            userAge,
                            userGender);
                    break;
                default:
                    break;
            }

            DataBaseManager dataBaseManager = new DataBaseManager();

            try {
                dataBaseManager.addUserToTable(newUser);


                int alertChoice = alertMessage.displayAlert(new String[]{"Log In", "Close"}, "You have successfully registered.");

                if (alertChoice == 0) {
                    getMainFrame().getContentPane().removeAll();
                    LoginGUI loginGUI = new LoginGUI();
                    loginGUI.displayGUI();
                }


            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    }


    Boolean detailsAreFilled() {

        String[] inputs = {firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), passwordTextField.getText()};

        for (var input : inputs) {
            if (input.equals(""))
                return false;
        }

        return true;
    }

    Boolean samePassword() {
        if (passwordTextField.getText().equals(repeatPasswordTextField.getText())) {
            return true;
        }
        return false;
    }


    String[] ageRange() {
        List<String> tempAgeRange = new ArrayList<>();

        for (int i = 18; i < 200; i++) {
            tempAgeRange.add(String.format("" + i));
        }
        return tempAgeRange.toArray(new String[0]);
    }

    String[] accountTypes() {

        String AccountTypes[] = {"Client", "Practitioner"};
        return AccountTypes;
    }

    String[] genders() {

        String genders[] = {"Male", "Female", "Other"};
        return genders;
    }

}
