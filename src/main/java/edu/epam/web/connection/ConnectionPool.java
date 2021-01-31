package edu.epam.web.connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum ConnectionPool {
    INSTANCE;

    public static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static final String DATABASE_PROPERTIES = "data_base";
    private static final String DATABASE_URL = "url";
    private static final String DATABASE_DRIVER = "driver";
    private static final int DEFAULT_POOL_SIZE = 32;

    private BlockingQueue<Connection> freeConnections;
    private Queue<Connection> givenAwayConnections;

    ConnectionPool() {
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        givenAwayConnections = new ArrayDeque<>();
        ClassLoader classLoader = this.getClass().getClassLoader();
        Properties properties = new Properties();

        try {
            properties.load(classLoader.getResourceAsStream(DATABASE_PROPERTIES));
            String sqlUrl = properties.getProperty(DATABASE_URL);
            String sqlDriver = properties.getProperty(DATABASE_DRIVER);
            Class.forName(sqlDriver);
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                freeConnections.add(new ProxyConnection(DriverManager.getConnection(sqlUrl, properties)));
            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = freeConnections.take();
            givenAwayConnections.add(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        givenAwayConnections.remove(connection);
        freeConnections.offer(connection);
    }

    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                freeConnections.take().close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, e.getMessage());
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, e.getMessage());
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, e.getMessage());
            }
        });
    }
}
