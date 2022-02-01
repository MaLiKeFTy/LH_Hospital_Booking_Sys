package com.lh_medical.users.user_info;

public record UserName(String firstName, String lastName) {

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
