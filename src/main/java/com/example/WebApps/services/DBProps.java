package com.example.WebApps.services;

import java.io.IOException;
import java.util.Properties;

public class DBProps {
    private final Properties properties;

    public DBProps() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.properties = properties;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
