package com.company.Users.Base;

import com.company.Users.UserInfo.UserAddress;
import com.company.Users.UserInfo.UserGender;
import com.company.Users.UserInfo.UserName;

public abstract class User {

    UserName _name;
    String _emailAddress;
    String _password;
    int _age;
    UserAddress _address;
    UserGender _gender;

    public User(UserName name, String emailAddress, String password, int age, UserAddress address, UserGender gender) {
        _name = name;
        _emailAddress = emailAddress;
        _password = password;
        _age = age;
        _address = address;
        _gender = gender;
    }
}
