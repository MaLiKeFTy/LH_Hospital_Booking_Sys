package com.company.GUI.ProfilesGUI.Factory;

import com.company.GUI.ProfilesGUI.AdminProfiles.SiteManagerProfileGUI;
import com.company.GUI.ProfilesGUI.Base.ProfileGUI;
import com.company.GUI.ProfilesGUI.UserProfiles.ClientProfileGUI;
import com.company.GUI.ProfilesGUI.UserProfiles.ConsultantProfileGui;
import com.company.GUI.ProfilesGUI.UserProfiles.PractitionerProfileGUI;
import com.company.GUI.ProfilesGUI.UserProfiles.SurgeonProfileGUI;

import java.util.ArrayList;
import java.util.List;

public final class ProfileGuiFactory {

    public static ProfileGUI GetProfileGUI(String profileType) {

        String[] profilesGuiNames = ProfilesGuiNames(ProfilesGUI());

        for (int i = 0; i < profilesGuiNames.length; i++) {

            String profilesGuiName = profilesGuiNames[i];

            if (profilesGuiName.contains(profileType)) {
                return ProfilesGUI()[i];
            }
        }

        return null;
    }

    static ProfileGUI[] ProfilesGUI() {

        ProfileGUI[] profileGUIS = {
                new ClientProfileGUI(),
                new ConsultantProfileGui(),
                new PractitionerProfileGUI(),
                new SurgeonProfileGUI(),
                new SiteManagerProfileGUI()
        };

        return profileGUIS;
    }


    static String[] ProfilesGuiNames(ProfileGUI[] profileGUIS) {
        List<String> tempProfileGuiNames = new ArrayList<>();

        for (ProfileGUI profileGUI : profileGUIS) {
            tempProfileGuiNames.add(profileGUI.getClass().getSimpleName());
        }


        return tempProfileGuiNames.toArray(new String[0]);
    }

}
