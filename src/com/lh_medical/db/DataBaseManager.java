package com.lh_medical.db;


import com.lh_medical.authentication.Login;
import com.lh_medical.treatment_course.TreatmentCourse;
import com.lh_medical.users.Client;
import com.lh_medical.users.Practitioner;
import com.lh_medical.users.factory.UserFactory;
import com.lh_medical.users.base.User;
import com.lh_medical.users.user_info.UserGender;
import com.lh_medical.users.user_info.UserName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class DataBaseManager {

    static Connection connection;
    Statement statement;
    ResultSet resultSet;

    public DataBaseManager() {
        connectToDataBase();
    }

    void connectToDataBase() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost: 3306/lh_medical_company", "root", "Malikefty1997??");

            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void addUserToTable(User userToAdd) throws SQLException {

        int userID = userToAdd.get_id();
        String firstName = userToAdd.get_name().getFirstName();
        String lastName = userToAdd.get_name().getLastName();
        String email = userToAdd.get_emailAddress();
        String age = String.valueOf(userToAdd.get_age());
        String password = userToAdd.get_password();
        String gender = userToAdd.get_gender().toString();
        String userType = userToAdd.get_userType();

        String sql = "INSERT INTO users(id,firstname,lastname,email,age,gender,password,usertype) Values (?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, userID);
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, lastName);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, age);
        preparedStatement.setString(6, gender);
        preparedStatement.setString(7, password);
        preparedStatement.setString(8, userType);

        preparedStatement.executeUpdate();

        //connection.close();
    }


    public User loginResult(Login login) throws SQLException {

        resultSet = statement.executeQuery("select * from users");

        while (resultSet.next()) {

            User user = UserFactory.getUser(resultSet.getString("usertype"));

            int userIdDB = resultSet.getInt("id");
            String firstNameDB = resultSet.getString("firstname");
            String lastNameDB = resultSet.getString("lastname");
            String emailDB = resultSet.getString("email");
            String ageDB = resultSet.getString("age");
            String genderDB = resultSet.getString("gender");
            String passwordDB = resultSet.getString("password");
            String usertypeDB = resultSet.getString("usertype");


            user.set_id(userIdDB);
            user.set_name(new UserName(firstNameDB, lastNameDB));
            user.set_emailAddress(emailDB);
            user.set_age(Integer.parseInt(ageDB));
            user.set_gender(UserGender.valueOf(genderDB));
            user.set_password(passwordDB);
            user.set_userType(usertypeDB);


            if (emailDB.equals(login.getEmail().toLowerCase(Locale.ROOT)) && passwordDB.equals(login.getPassword()))
                return user;
        }

        // connection.close();
        return null;
    }

    public User[] getAllUsers(String userType) throws SQLException {


        List<User> tempUsers = new ArrayList<>();

        resultSet = statement.executeQuery("select * from users");

        while (resultSet.next()) {

            if (!resultSet.getString("usertype").equals(userType))
                continue;

            User user = UserFactory.getUser(resultSet.getString("usertype"));

            int userIdDB = resultSet.getInt("id");
            String firstNameDB = resultSet.getString("firstname");
            String lastNameDB = resultSet.getString("lastname");
            String emailDB = resultSet.getString("email");
            String ageDB = resultSet.getString("age");
            String genderDB = resultSet.getString("gender");
            String passwordDB = resultSet.getString("password");
            String usertypeDB = resultSet.getString("usertype");


            user.set_id(userIdDB);
            user.set_name(new UserName(firstNameDB, lastNameDB));
            user.set_emailAddress(emailDB);
            user.set_age(Integer.parseInt(ageDB));
            user.set_gender(UserGender.valueOf(genderDB));
            user.set_password(passwordDB);
            user.set_userType(usertypeDB);


            tempUsers.add(user);
        }

        return tempUsers.toArray(new User[0]);

    }

    public void addPractitionerToClient(Practitioner practitioner, Client client) throws SQLException {

        int clientId = client.get_id();
        String clientFirstName = client.get_name().getFirstName();
        String clientLastName = client.get_name().getLastName();

        int practitionerId = practitioner.get_id();
        String practitionerFirstName = practitioner.get_name().getFirstName();
        String practitionerLastName = practitioner.get_name().getLastName();

        String sql = "INSERT INTO clients_practitioners(client_id,client_firstname,client_lastname,practitioner_id,practitioner_firstname,practitioner_lastname) Values (?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, clientId);
        preparedStatement.setString(2, clientFirstName);
        preparedStatement.setString(3, clientLastName);
        preparedStatement.setInt(4, practitionerId);
        preparedStatement.setString(5, practitionerFirstName);
        preparedStatement.setString(6, practitionerLastName);

        preparedStatement.executeUpdate();
    }


    public void assignClientToTreatment(TreatmentCourse treatmentCourse, Client client, Practitioner practitioner) throws SQLException {

        int treatmentCourseId = treatmentCourse.get_id();
        String treatmentCourseName = treatmentCourse.get_name();
        String treatmentCourseStatus = treatmentCourse.get_status().toString();

        int clientId = client.get_id();
        String clientFirstName = client.get_name().getFirstName();
        String clientLastName = client.get_name().getLastName();

        int practitionerId = practitioner.get_id();
        String practitionerFirstName = practitioner.get_name().getFirstName();
        String practitionerLastName = practitioner.get_name().getLastName();

        String sql = "INSERT INTO treatment_course(" +
                "treatment_id," +
                "treatment_name," +
                "treatment_status," +
                "practitioner_id," +
                "practitioner_firstname," +
                "practitioner_lastname," +
                "client_id," +
                "client_firstname," +
                "client_lastname) " +
                "Values (?,?,?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, treatmentCourseId);
        preparedStatement.setString(2, treatmentCourseName);
        preparedStatement.setString(3, treatmentCourseStatus);


        preparedStatement.setInt(4, practitionerId);
        preparedStatement.setString(5, practitionerFirstName);
        preparedStatement.setString(6, practitionerLastName);

        preparedStatement.setInt(7, clientId);
        preparedStatement.setString(8, clientFirstName);
        preparedStatement.setString(9, clientLastName);

        preparedStatement.executeUpdate();
    }


    public Practitioner[] getClientPractitioners(Client client) throws SQLException {


        List<Practitioner> tempUsers = new ArrayList<>();

        resultSet = statement.executeQuery("select * from clients_practitioners");

        while (resultSet.next()) {

            if (resultSet.getInt("client_id") != client.get_id())
                continue;

            Practitioner user = new Practitioner();

            int userIdDB = resultSet.getInt("practitioner_id");
            String firstNameDB = resultSet.getString("practitioner_firstname");
            String lastNameDB = resultSet.getString("practitioner_lastname");


            user.set_id(userIdDB);
            user.set_name(new UserName(firstNameDB, lastNameDB));


            tempUsers.add(user);
        }

        return tempUsers.toArray(new Practitioner[0]);


    }


    public String[] practitionerTreatmentNames(Practitioner practitioner) throws SQLException {

        List<String> tempTreatmentNames = new ArrayList<>();

        resultSet = statement.executeQuery("select * from treatment_course");

        while (resultSet.next()) {

            if (resultSet.getInt("practitioner_id") == practitioner.get_id()) {
                tempTreatmentNames.add(resultSet.getString("treatment_name"));
            }
        }

        return tempTreatmentNames.toArray(new String[0]);
    }

    public String[] clientTreatmentNames(Client client) throws SQLException {

        List<String> tempTreatmentNames = new ArrayList<>();

        resultSet = statement.executeQuery("select * from treatment_course");

        while (resultSet.next()) {

            if (resultSet.getInt("client_id") == client.get_id()) {
                tempTreatmentNames.add(resultSet.getString("treatment_name"));
            }
        }

        return tempTreatmentNames.toArray(new String[0]);
    }


    public Client[] getPractitionerClients(Practitioner practitioner) throws SQLException {


        List<Client> tempClients = new ArrayList<>();

        resultSet = statement.executeQuery("select * from clients_practitioners");

        while (resultSet.next()) {

            if (resultSet.getInt("practitioner_id") != practitioner.get_id())
                continue;

            Client client = new Client();

            int userIdDB = resultSet.getInt("client_id");
            String firstNameDB = resultSet.getString("client_firstname");
            String lastNameDB = resultSet.getString("client_lastname");


            client.set_id(userIdDB);
            client.set_name(new UserName(firstNameDB, lastNameDB));


            tempClients.add(client);
        }

        return tempClients.toArray(new Client[0]);
    }

    public Boolean checkUser(User userToFind) throws SQLException {

        Boolean foundUser = false;

        resultSet = statement.executeQuery("select * from users");

        while (resultSet.next()) {
            if (resultSet.getInt("id") == userToFind.get_id()) {
                foundUser = true;
                break;
            }
        }

        return foundUser;
    }

    public Boolean duplicatedEmail(User user) throws SQLException {

        Boolean duplicated = false;

        resultSet = statement.executeQuery("select * from users");

        while (resultSet.next()) {
            if (Objects.equals(resultSet.getString("email"), user.get_emailAddress())) {
                duplicated = true;
                break;
            }
        }

        return duplicated;
    }

}
