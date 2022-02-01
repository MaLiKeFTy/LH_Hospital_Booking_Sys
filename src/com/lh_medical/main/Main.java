package com.lh_medical.main;

import com.lh_medical.gui.MainMenuGUI;

public class Main {

    public Main() {
        InitialiseGui();
    }

    public static void main(String[] args) {
        new Main();
    }

    void InitialiseGui() {
        MainMenuGUI mainMenuGUI = new MainMenuGUI();
        mainMenuGUI.displayGUI();
    }
}
