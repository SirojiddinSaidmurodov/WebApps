package com.example.WebApps.services;

import com.example.WebApps.domain.User;

import java.sql.*;

public class UserServer {
    Connection connection;

    public UserServer() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBProps props = new DBProps();
        try {
            connection = DriverManager.getConnection(
                    props.getProperty("url"),
                    props.getProperty("user"),
                    props.getProperty("password"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setSession(String sessionID, String login) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update user SET session=? where login=?");
            preparedStatement.setString(1, sessionID);
            preparedStatement.setString(2, login);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public User getUser(String login) {
        User result = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, login, session from user where login=?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("session"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
}
