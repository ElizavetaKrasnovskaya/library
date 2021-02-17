package com.epam.library.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InitializerConnectionPool {
    private static ApplicationProperties applicationProperties = ApplicationProperties.getApplicationProperties();
    static {
        try {
            Class.forName(applicationProperties.getDriver());
        } catch (ClassNotFoundException e) {
            // TODO: logger
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
        } catch (SQLException throwables) {
            // TODO: logger
        }
        return null;
    }
}
