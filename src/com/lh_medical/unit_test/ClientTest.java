package com.lh_medical.unit_test;

import com.lh_medical.db.DataBaseManager;
import com.lh_medical.users.Client;
import com.lh_medical.users.Practitioner;
import com.lh_medical.users.user_info.UserGender;
import com.lh_medical.users.user_info.UserName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class ClientTest {

    Client dummyClient;
    Practitioner[] practitioners;
    DataBaseManager dataBaseManager;

    @BeforeEach
    void setUp() {
        dummyClient = new Client(
                new UserName("Malik", "Fitouri"),
                "malik.fitouri@gmail.com",
                "malik1997",
                24,
                UserGender.MALE);

        SetDummyPractitioners();

        dataBaseManager = new DataBaseManager();
    }


    /**
     * test if the practitioners will be added to the client.
     */

    @Test
    void addPractitioner() throws SQLException {
        dataBaseManager.addPractitionerToClient(practitioners[0], dummyClient);
        dataBaseManager.addPractitionerToClient(practitioners[1], dummyClient);

        dummyClient.setPractitioners(dataBaseManager.getClientPractitioners(dummyClient));


        Boolean foundPractitionersInDatabase = true;


        for (Practitioner practitioner : dummyClient.getPractitioners()) {
            if (!containsPractitioner(practitioner)) {
                foundPractitionersInDatabase = false;
            }

        }

        Assertions.assertTrue(foundPractitionersInDatabase);
    }



    void SetDummyPractitioners() {

        Practitioner practitioner1 = new Practitioner(
                new UserName("John", "White"),
                "John.white@gmail.com",
                "john1997",
                26,
                UserGender.MALE);

        Practitioner practitioner2 = new Practitioner(
                new UserName("Jenny", "Site"),
                "Jenny.Site@gmail.com",
                "jenny1997",
                28,
                UserGender.FEMALE);


        practitioners = new Practitioner[]{practitioner1, practitioner2};

    }

    Boolean containsPractitioner(Practitioner practitioner) {

        boolean found = false;

        for (Practitioner dummyPractitioner : practitioners) {
            if (dummyPractitioner.get_id() == practitioner.get_id()) {
                found = true;
            }
        }

        return found;
    }

}