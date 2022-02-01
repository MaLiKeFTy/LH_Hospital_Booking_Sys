package com.lh_medical.authentication;

import com.lh_medical.db.DataBaseManager;
import com.lh_medical.users.base.User;

import java.sql.SQLException;

public class Register {

    public void registerUser(User newUser) throws SQLException {
        DataBaseManager dataBaseManager = new DataBaseManager();
        dataBaseManager.addUserToTable(newUser);
    }

}
