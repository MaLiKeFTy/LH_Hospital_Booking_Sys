package com.lh_medical.gui.profiles_gui.factory;

import com.lh_medical.gui.profiles_gui.base.ProfileGUI;
import com.lh_medical.gui.profiles_gui.user_profiles.ClientProfileGUI;
import com.lh_medical.gui.profiles_gui.user_profiles.PractitionerProfileGUI;

import java.util.ArrayList;
import java.util.List;

public final class ProfileGuiFactory {

    public static ProfileGUI getProfileGUI(String profileType) {

        String[] profilesGuiNames = profilesGuiNames(profilesGUI());

        for (int i = 0; i < profilesGuiNames.length; i++) {

            String profilesGuiName = profilesGuiNames[i];

            if (profilesGuiName.contains(profileType)) {
                return profilesGUI()[i];
            }
        }

        return null;
    }

    static ProfileGUI[] profilesGUI() {

        ProfileGUI[] profileGUIS = {
                new ClientProfileGUI(),
                new PractitionerProfileGUI(),
        };

        return profileGUIS;
    }


    static String[] profilesGuiNames(ProfileGUI[] profileGUIS) {
        List<String> tempProfileGuiNames = new ArrayList<>();

        for (ProfileGUI profileGUI : profileGUIS) {
            tempProfileGuiNames.add(profileGUI.getClass().getSimpleName());
        }


        return tempProfileGuiNames.toArray(new String[0]);
    }

}
