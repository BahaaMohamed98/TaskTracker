package com.github.bahaa.tasktracker.database;

import com.github.bahaa.tasktracker.database.connection.ConnectionProvider;

import java.sql.SQLException;
import java.sql.Statement;

public class DBInit {
    private final ConnectionProvider connectionProvider;

    public DBInit(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    private void createTables() throws SQLException {
        try (var connection = connectionProvider.getConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.execute("""
                        CREATE TABLE IF NOT EXISTS TASKS(
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            title VARCHAR(255) NOT NULL,
                            description VARCHAR(1000) NOT NULL,
                            is_done BOOLEAN NOT NULL,
                            created_at TIMESTAMP NOT NULL
                        )
                    """);
        }
    }

    public void init() throws SQLException {
        createTables();
    }
}
