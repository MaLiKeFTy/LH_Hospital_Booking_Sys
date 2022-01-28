package com.company.Users.UserInfo;

public enum UserGender {
    MALE (0), FEMALE(1), OTHER(2);

    int _genderValue;

    UserGender(int genderValue) {
        _genderValue = genderValue;
    }
}
