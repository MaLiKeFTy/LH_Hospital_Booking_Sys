package com.lh_medical.users.factory;

import com.lh_medical.users.Client;
import com.lh_medical.users.Practitioner;
import com.lh_medical.users.base.User;

import java.util.ArrayList;
import java.util.List;

public final class UserFactory {

    public static User getUser(String profileType) {

        String[] UserNames = usersNames(users());

        for (int i = 0; i < UserNames.length; i++) {

            String userName = UserNames[i];

            if (userName.contains(profileType)) {
                return users()[i];
            }
        }

        return null;
    }

    static User[] users() {

        User[] users = {
                new Client(),
                new Practitioner(),
        };

        return users;
    }


    static String[] usersNames(User[] users) {
        List<String> tempProfileGuiNames = new ArrayList<>();

        for (User user : users) {
            tempProfileGuiNames.add(user.getClass().getSimpleName());
        }

        return tempProfileGuiNames.toArray(new String[0]);
    }

}
