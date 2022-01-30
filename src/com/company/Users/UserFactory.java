package com.company.Users;

import com.company.Users.Base.User;

import java.util.ArrayList;
import java.util.List;

public final class UserFactory {

    public static User GetUser(String profileType) {

        String[] UserNames = UsersNames(Users());

        for (int i = 0; i < UserNames.length; i++) {

            String userName = UserNames[i];

            if (userName.contains(profileType)) {
                return Users()[i];
            }
        }

        return null;
    }

    static User[] Users() {

        User[] users = {
                new Client(),
                new Consultant(),
                new Practitioner(),
                new Surgeon(),
                new SiteManager()
        };

        return users;
    }


    static String[] UsersNames(User[] users) {
        List<String> tempProfileGuiNames = new ArrayList<>();

        for (User user : users) {
            tempProfileGuiNames.add(user.getClass().getSimpleName());
        }


        return tempProfileGuiNames.toArray(new String[0]);
    }

}
