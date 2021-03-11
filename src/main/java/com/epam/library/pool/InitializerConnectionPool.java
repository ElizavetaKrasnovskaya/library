package com.epam.library.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InitializerConnectionPool {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitializerConnectionPool.class);

    private static ApplicationProperties applicationProperties = ApplicationProperties.getApplicationProperties();
    static {
        try {
            Class.forName(applicationProperties.getDriver());
        } catch (ClassNotFoundException exception) {
            LOGGER.info("ClassNotFoundException: {}", exception.getMessage());
        }
    }

    public static Connection createConnection(){
        try {
            Connection connection = DriverManager.getConnection(
                    applicationProperties.getUrl(),
                    applicationProperties.getUsername(),
                    applicationProperties.getPassword()
            );
            return connection;
        } catch (SQLException exception) {
            LOGGER.info("SQLException: {}", exception.getMessage());
        }
        return null;
    }
}
