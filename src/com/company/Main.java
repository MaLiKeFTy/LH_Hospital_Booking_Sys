package com.company;

import com.company.GUI.MainMenuGUI;
import com.company.GUI.ProfilesGUI.Base.ProfileGUI;
import com.company.GUI.ProfilesGUI.Factory.ProfileGuiFactory;
import com.company.GUI.ProfilesGUI.UserProfiles.ClientProfileGUI;
import com.company.Users.Client;
import com.company.Users.UserInfo.UserGender;
import com.company.Users.UserInfo.UserName;

public class Main {

    public Main() {
        InitialiseGui();
    }

    public static void main(String[] args) {
        new Main();
    }

    void InitialiseGui() {
        /*ClientProfileGUI profileGUI = new ClientProfileGUI();

        Client client = new Client(new UserName("sample","name"),"sample","sample", 20, UserGender.MALE);

        profileGUI.set_user(client);
        profileGUI.DisplayGUI();*/

        MainMenuGUI mainMenuGUI = new MainMenuGUI();
        mainMenuGUI.DisplayGUI();


       // DataBaseConnector dataBaseConnector = new DataBaseConnector();

    }
}
