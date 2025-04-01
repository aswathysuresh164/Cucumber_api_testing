package com.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
//This class is responsible for calling the actual API.
public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config/config.properties");
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file!", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}

