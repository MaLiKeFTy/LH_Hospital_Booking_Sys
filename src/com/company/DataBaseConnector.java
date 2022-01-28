package com.company;


import com.company.Users.Base.User;

import java.sql.*;
import java.util.concurrent.ThreadLocalRandom;

public class DataBaseConnector {

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    public DataBaseConnector() {
        ConnectToDataBase();
    }

    void ConnectToDataBase() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost: 3306/lh_medical_company", "root", "Malikefty1997??");

            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void AddUserToTable(User userToAdd) throws SQLException {

        int userID = ThreadLocalRandom.current().nextInt(10, 9999 + 1);
        String firstName = userToAdd.get_name().get_firstName();
        String lastName = userToAdd.get_name().get_lastName();
        String email = userToAdd.get_emailAddress();
        String age = String.valueOf(userToAdd.get_age());
        String password = userToAdd.get_password();
        String gender = userToAdd.get_gender().toString();
        String userType = userToAdd.getClass().getSimpleName();

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

        connection.close();
    }


    public String LoginMessage(String email, String password) throws SQLException {

        String loginMessage = "Wrong email or password";

        resultSet = statement.executeQuery("select * from users");

        while (resultSet.next()) {

            String emailDB = resultSet.getString("email");
            String passwordDB = resultSet.getString("password");

            if(emailDB.equals(email) && passwordDB.equals(password))
                loginMessage = "Login successful";
        }

        return  loginMessage;
    }


}
