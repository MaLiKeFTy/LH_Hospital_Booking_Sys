package com.company.Users.Base;

import com.company.Users.UserInfo.UserGender;
import com.company.Users.UserInfo.UserName;

public abstract class User {

    UserName _name;
    String _emailAddress;
    String _password;
    int _age;
    UserGender _gender;

    public User(UserName name, String emailAddress, String password, int age, UserGender gender) {
        _name = name;
        _emailAddress = emailAddress;
        _password = password;
        _age = age;
        _gender = gender;
    }

    public UserName get_name() {
        return _name;
    }

    public String get_emailAddress() {
        return _emailAddress;
    }

    public String get_password() {
        return _password;
    }

    public int get_age() {
        return _age;
    }

    public UserGender get_gender() {
        return _gender;
    }
}
