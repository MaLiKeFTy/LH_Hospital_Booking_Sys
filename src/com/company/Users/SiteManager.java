package com.company.Users;

import com.company.Users.Base.User;
import com.company.Users.UserInfo.UserGender;
import com.company.Users.UserInfo.UserName;

public class SiteManager extends User {

    public SiteManager(UserName name, String emailAddress, String password, int age, UserGender userGender) {
        super(name, emailAddress, password, age, userGender);
    }

    public SiteManager() {
    }
}
