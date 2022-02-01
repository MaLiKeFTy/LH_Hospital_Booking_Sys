package com.lh_medical.unit_test;

import com.lh_medical.authentication.Register;
import com.lh_medical.db.DataBaseManager;
import com.lh_medical.users.Client;
import com.lh_medical.users.user_info.UserGender;
import com.lh_medical.users.user_info.UserName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;

class RegisterTest {

    DataBaseManager dataBaseManager;
    UserName clientName;
    Client clientToRegister;
    Register register;

    @BeforeEach
    void setUp() {
        register = new Register();

        dataBaseManager = new DataBaseManager();

        clientName = new UserName("Paul", "Walker");

        clientToRegister = new Client(
                clientName,
                "paul.walker@gmail.com",
                "paul1197",
                25,
                UserGender.MALE);
    }

    /**
     * test if the user will be registered by checking them in the database.
     */

    @org.junit.jupiter.api.Test
    void registerUser() throws SQLException {
        register.registerUser(clientToRegister);

        Assertions.assertTrue(dataBaseManager.checkUser(clientToRegister));
    }

    /**
     * test if the user have the same email address as the other users.
    */

    @org.junit.jupiter.api.Test
    void checkDuplicatedEmail() throws SQLException {
        register.registerUser(clientToRegister);

        Client clientWithSameEmail = new Client(
                new UserName("malik", "fitouri"),
                "paul.walker@gmail.com",
                "malik1997",
                24,
                UserGender.MALE);

        //test if database check for duplicated account.
        Assertions.assertTrue(dataBaseManager.duplicatedEmail(clientWithSameEmail));

    }

}