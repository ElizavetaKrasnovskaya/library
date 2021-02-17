package com.epam.library.pool;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum ConnectionPool {

    INSTANCE;

    private BlockingQueue<ProxyConnection> pool;

    private ApplicationProperties applicationProperties = ApplicationProperties.getApplicationProperties();
    private final int DEFAULT_POOL_SIZE = 32;
    ConnectionPool(){
        pool = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        fillConnectionPool();
    }

    private void fillConnectionPool() {
        for(int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            Connection connection = InitializerConnectionPool.createConnection();
            pool.offer(new ProxyConnection(connection));
        }
    }

    public ProxyConnection getConnection(){
        ProxyConnection connection = null;
        try {
            connection = pool.take();
        } catch (InterruptedException e) {
            // TODO: logger
        }
        return connection;
    }
    public void releaseConnection(ProxyConnection connection){
        pool.offer(connection);
    }
    public void destroyPool(){
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++){
            try {
                pool.take().closeConnection();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        deregisterDrivers();
    }
    private void deregisterDrivers(){
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }
}
