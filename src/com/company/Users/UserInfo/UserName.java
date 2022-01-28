package com.company.Users.UserInfo;

public class UserName {
    private String _firstName;
    private String _lastName;

    public UserName(String firstName, String lastName) {
        _firstName = firstName;
        _lastName = lastName;
    }

    public String get_firstName() {
        return _firstName;
    }

    public String get_lastName() {
        return _lastName;
    }
}
