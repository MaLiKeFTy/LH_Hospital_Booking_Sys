package com.company.GUI;

import com.company.GUI.Base.GuiBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

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
    JComboBox ageComboBox= new JComboBox(AgeRange());

    JLabel genderLabel = new JLabel("Gender");
    JComboBox genderComboBox= new JComboBox(Genders());

    JLabel usernameLabel = new JLabel("User Name");
    JTextField usernameTextField = new JTextField();

    JLabel passwordLabel = new JLabel("Password");
    JPasswordField passwordTextField = new JPasswordField();

    JLabel repeatPasswordLabel = new JLabel("Repeat Password");
    JPasswordField repeatPasswordTextField = new JPasswordField();



    JLabel userTypeLabel = new JLabel("Account Type");
    JComboBox userTypeBox= new JComboBox(AccountTypes());

    JPanel menuButtonsPanel = new JPanel();
    JButton logInButton = new JButton("Register");
    JButton goBackButton = new JButton("Go Back");


    @Override
    public void DisplayGUI() {

        menuTitlePanel.setBounds(100, 20, 600, 150);
        menuTitlePanel.setBackground(Color.BLUE);
        menuTitlePanel.setLayout(new GridBagLayout());

        menuTitleLabel.setForeground(Color.white);
        menuTitleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        menuTitlePanel.add(menuTitleLabel);


        JScrollPane authenticationScrollPane = new JScrollPane(authenticationPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        authenticationPanel.setLayout(new GridLayout(9,2,20,20));
        authenticationPanel.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
        authenticationPanel.setBounds(100, 170, 600, 400);
        authenticationPanel.setBackground(Color.BLUE);


        firstNameLabel.setForeground(Color.WHITE);
        authenticationPanel.add(firstNameLabel);

        firstNameTextField.setBounds(100,20,165,25);
        authenticationPanel.add(firstNameTextField);


        lastNameLabel.setForeground(Color.WHITE);
        authenticationPanel.add(lastNameLabel);

        lastNameTextField.setBounds(100,20,165,25);
        authenticationPanel.add(lastNameTextField);


        emailLabel.setForeground(Color.WHITE);
        authenticationPanel.add(emailLabel);

        emailTextField.setBounds(100,20,165,25);
        authenticationPanel.add(emailTextField);


        ageLabel.setForeground(Color.WHITE);
        authenticationPanel.add(ageLabel);

        ageComboBox.setBounds(100,20,165,25);
        authenticationPanel.add(ageComboBox);


        genderLabel.setForeground(Color.WHITE);
        authenticationPanel.add(genderLabel);

        genderComboBox.setBounds(100,20,165,25);
        authenticationPanel.add(genderComboBox);


        usernameLabel.setForeground(Color.WHITE);
        authenticationPanel.add(usernameLabel);

        usernameTextField.setBounds(100,20,165,25);
        authenticationPanel.add(usernameTextField);


        passwordLabel.setForeground(Color.WHITE);
        authenticationPanel.add(passwordLabel);

        passwordTextField.setBounds(100,50,165,25);
        authenticationPanel.add(passwordTextField);


        repeatPasswordLabel.setForeground(Color.WHITE);
        authenticationPanel.add(repeatPasswordLabel);

        repeatPasswordTextField.setBounds(100,50,165,25);
        authenticationPanel.add(repeatPasswordTextField);


        userTypeLabel.setForeground(Color.WHITE);
        authenticationPanel.add(userTypeLabel);

        userTypeBox.setBounds(100,20,165,25);
        authenticationPanel.add(userTypeBox);


        menuButtonsPanel.setBounds(100, 565, 600, 80);
        menuButtonsPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        menuButtonsPanel.setLayout(new GridLayout(1,0,20,20));
        menuButtonsPanel.setBackground(Color.BLUE);

        logInButton.addActionListener(this);
       // logInButton.setFont(new Font("Serif", Font.BOLD,30));
        logInButton.setBackground(Color.WHITE);
        logInButton.setForeground(Color.BLUE);
        menuButtonsPanel.add(logInButton);

        goBackButton.addActionListener(this);
        //goBackButton.setFont(new Font("Serif", Font.BOLD,30));
        goBackButton.setBackground(Color.WHITE);
        goBackButton.setForeground(Color.BLUE);
        menuButtonsPanel.add(goBackButton);

        GetMainFrame().add(menuTitlePanel);
        GetMainFrame().add(menuButtonsPanel);
        GetMainFrame().add(authenticationPanel);
        GetMainFrame().add(authenticationScrollPane);
        GetMainFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        GetMainFrame().getContentPane().removeAll();

        MainMenuGUI mainMenuGUI = new MainMenuGUI();
        mainMenuGUI.DisplayGUI();
    }


    String[] AgeRange() {
        List<String> tempAgeRange = new ArrayList<>();

        for (int i = 18; i < 200; i++) {
            tempAgeRange.add(String.format("" + i));
        }
        return tempAgeRange.toArray(new String[0]);
    }

    String[] AccountTypes() {

        String AccountTypes[] = {"Client", "Consultant", "Practitioner", "SiteManager", "Surgeon"};
        return  AccountTypes;
    }

    String[] Genders() {

        String genders[] = {"Male", "Female", "Other"};
        return  genders;
    }

}
