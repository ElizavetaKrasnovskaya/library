package com.epam.library.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ApplicationProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationProperties.class);

    private static ApplicationProperties instance;

    private static ResourceBundle databaseProperties;

    private static final String DATABASE_PROPERTIES_PATH = "database";
    private static final String DATABASE_URL = "db.url";
    private static final String DATABASE_USERNAME = "db.username";
    private static final String DATABASE_PASSWORD = "db.password";
    private static final String DATABASE_DRIVER = "db.driver";

    private ApplicationProperties() {}

    private void loadProperties(){
        databaseProperties = ResourceBundle.getBundle(DATABASE_PROPERTIES_PATH);
    }
    public String getUrl(){
        try {
            String url = databaseProperties.getString(DATABASE_URL);
            return url;
        } catch (MissingResourceException exception){
            LOGGER.info("MissingResourceException: {}.", exception.getMessage());
        }
        return null;
    }
    public String getUsername(){
        try {
            String username = databaseProperties.getString(DATABASE_USERNAME);
            return username;
        } catch (MissingResourceException exception){
            LOGGER.info("MissingResourceException: {}", exception.getMessage());
        }
        return null;
    }
    public String getPassword(){
        try {
            String pass = databaseProperties.getString(DATABASE_PASSWORD);
            return pass;
        } catch (MissingResourceException exception){
            LOGGER.info("MissingResourceException: {}", exception.getMessage());
        }
        return null;
    }
    public String getDriver(){
        try {
            String driver = databaseProperties.getString(DATABASE_DRIVER);
            return driver;
        } catch (MissingResourceException exception){
            LOGGER.info("MissingResourceException: {}", exception.getMessage());
        }
        return null;
    }


    public static ApplicationProperties getApplicationProperties(){
        if (instance == null){
            instance = new ApplicationProperties();
            instance.loadProperties();
        }
        return instance;
    }

}
