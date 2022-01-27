package com.company;

import com.company.GUI.MainMenuGUI;

public class Main {

    public Main() {
        InitialiseGui();
    }

    public static void main(String[] args) {
        new Main();
    }

    void InitialiseGui() {
        MainMenuGUI mainMenuGUI = new MainMenuGUI();
        mainMenuGUI.DisplayGUI();
    }
}
