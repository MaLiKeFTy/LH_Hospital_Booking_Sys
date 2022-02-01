package com.lh_medical.gui.profiles_gui.base;

import com.lh_medical.gui.base.GuiBase;
import com.lh_medical.gui.authentication_gui.LoginGUI;
import com.lh_medical.users.base.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class ProfileGUI extends GuiBase {

    User user;

    JPanel menuTitlePanel = new JPanel();
    JLabel menuTitleLabel = new JLabel();
    JLabel userGreetingLabel = new JLabel();

    JButton logOutButton = new JButton("Log out");

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    @Override
    public void displayGUI() {

        menuTitlePanel.setBounds(100, 50, 600, 200);
        menuTitlePanel.setBackground(Color.BLUE);
        menuTitlePanel.setLayout(new GridLayout(3, 1));
        menuTitlePanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        menuTitleLabel.setText("LH Medical Menu: " + user.get_userType());
        menuTitleLabel.setForeground(Color.white);
        menuTitleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        menuTitlePanel.add(menuTitleLabel);

        userGreetingLabel.setText("Welcome back " + getUser().get_name().getFirstName() + "!");
        userGreetingLabel.setForeground(Color.white);
        userGreetingLabel.setFont(new Font("Serif", Font.BOLD, 30));
        menuTitlePanel.add(userGreetingLabel);


        logOutButton.addActionListener(this);
        logOutButton.setFont(new Font("Serif", Font.BOLD, 30));
        logOutButton.setBackground(Color.WHITE);
        logOutButton.setForeground(Color.BLUE);
        menuTitlePanel.add(logOutButton);

        getMainFrame().add(menuTitlePanel);
        getMainFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logOutButton){
            getMainFrame().getContentPane().removeAll();
            LoginGUI loginGUI = new LoginGUI();
            loginGUI.displayGUI();
        }
    }
}
