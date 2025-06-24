package com.github.bahaa.tasktracker.database.connection;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariConnectionProvider implements ConnectionProvider {
    private final static String URL = "jdbc:h2:./database;AUTO_SERVER=TRUE";
    private final static HikariDataSource dataSource = createDataSource();

    private static HikariDataSource createDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(URL);
        return ds;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
