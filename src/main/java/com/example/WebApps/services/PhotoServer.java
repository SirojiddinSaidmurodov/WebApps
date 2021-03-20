package com.example.WebApps.services;

import com.example.WebApps.domain.Photo;
import com.example.WebApps.domain.User;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class PhotoServer {
    private Connection connection;

    public PhotoServer() {
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

    public ArrayList<Photo> getPhotosList(User user) {
        long userId = user.getId();
        ArrayList<Photo> photos = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, name from photos where user_id=?");
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                photos.add(new Photo(
                        resultSet.getLong("id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return photos;
    }

    public InputStream getPhoto(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT photo FROM photos where id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBinaryStream("photo");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void upload(User user, String fileName, InputStream inputStream) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO photos(user_id, name, photo) value (?,?,?)");
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, fileName);
            preparedStatement.setBinaryStream(3, inputStream);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void delete(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM photos where id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
