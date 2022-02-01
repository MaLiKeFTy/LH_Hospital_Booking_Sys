package com.lh_medical.unit_test;

import com.lh_medical.authentication.Login;
import com.lh_medical.db.DataBaseManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;


class LoginTest {

    DataBaseManager dataBaseManager;
    Login login;


    @BeforeEach
    void setUp() {
        dataBaseManager = new DataBaseManager();
        login = new Login("paul.walker@gmail.com", "paul1197");
    }

    /**
     * test if the user will login successfully.
     */

    @org.junit.jupiter.api.Test
    void loginUser() throws SQLException {

        boolean successfulLogin = dataBaseManager.loginResult(login) != null;
        Assertions.assertTrue(successfulLogin);
    }

}