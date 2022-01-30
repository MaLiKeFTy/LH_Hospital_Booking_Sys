package com.company.Users.Base;

import com.company.Users.UserInfo.UserGender;
import com.company.Users.UserInfo.UserName;

import java.util.concurrent.ThreadLocalRandom;

public abstract class User {


    int _id;
    UserName _name;
    String _emailAddress;
    String _password;
    int _age;
    UserGender _gender;
    String _userType;

    public User(UserName name, String emailAddress, String password, int age, UserGender gender) {

        _id = ThreadLocalRandom.current().nextInt(10, 9999 + 1);
        _name = name;
        _emailAddress = emailAddress;
        _password = password;
        _age = age;
        _gender = gender;
        _userType = this.getClass().getSimpleName();
    }

    public User(int id,UserName name, String emailAddress, String password, int age, UserGender gender, String usertype) {
        _id = id;
        _name = name;
        _emailAddress = emailAddress;
        _password = password;
        _age = age;
        _gender = gender;
        _userType = usertype;
    }

    public User() {
    }

    public int get_id() { return _id; }

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

    public String get_userType() { return _userType; }


    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_name(UserName _name) {
        this._name = _name;
    }

    public void set_emailAddress(String _emailAddress) {
        this._emailAddress = _emailAddress;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public void set_age(int _age) {
        this._age = _age;
    }

    public void set_gender(UserGender _gender) {
        this._gender = _gender;
    }

    public void set_userType(String _userType) {
        this._userType = _userType;
    }




}
