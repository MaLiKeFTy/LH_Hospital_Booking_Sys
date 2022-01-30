package com.company.GUI.ProfilesGUI.Base;

import com.company.GUI.Base.GuiBase;
import com.company.GUI.LoginGUI;
import com.company.Users.Base.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class ProfileGUI extends GuiBase {

    User _user;

    JPanel menuTitlePanel = new JPanel();
    JLabel menuTitleLabel = new JLabel();
    JLabel userGreetingLabel = new JLabel();

    JButton logOutButton = new JButton("Log out");

    public User get_user() {
        return _user;
    }

    public void set_user(User _user) {
        this._user = _user;
    }



    @Override
    public void DisplayGUI() {

        menuTitlePanel.setBounds(100, 50, 600, 200);
        menuTitlePanel.setBackground(Color.BLUE);
        menuTitlePanel.setLayout(new GridLayout(3, 1));
        menuTitlePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        menuTitleLabel.setText("LH Medical Menu: " + _user.get_userType());
        menuTitleLabel.setForeground(Color.white);
        menuTitleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        menuTitlePanel.add(menuTitleLabel);

        userGreetingLabel.setText("Welcome back " + get_user().get_name().get_firstName() + "!");
        userGreetingLabel.setForeground(Color.white);
        userGreetingLabel.setFont(new Font("Serif", Font.BOLD, 30));
        menuTitlePanel.add(userGreetingLabel);


        logOutButton.addActionListener(this);
        logOutButton.setFont(new Font("Serif", Font.BOLD, 30));
        logOutButton.setBackground(Color.WHITE);
        logOutButton.setForeground(Color.BLUE);
        menuTitlePanel.add(logOutButton);

        GetMainFrame().add(menuTitlePanel);
        GetMainFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logOutButton){
            GetMainFrame().getContentPane().removeAll();
            LoginGUI loginGUI = new LoginGUI();
            loginGUI.DisplayGUI();
        }
    }
}
