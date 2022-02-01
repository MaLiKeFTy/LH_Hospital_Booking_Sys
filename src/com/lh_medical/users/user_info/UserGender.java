package com.lh_medical.users.user_info;

public enum UserGender {
    MALE (0), FEMALE(1), OTHER(2);

    final int genderValue;

    UserGender(int genderValue) {
        this.genderValue = genderValue;
    }
}
