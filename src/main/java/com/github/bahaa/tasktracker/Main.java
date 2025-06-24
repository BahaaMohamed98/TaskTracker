package com.github.bahaa.tasktracker;

import com.github.bahaa.tasktracker.cli.CommandLineFactory;
import com.github.bahaa.tasktracker.controller.TaskController;
import com.github.bahaa.tasktracker.database.DBInit;
import com.github.bahaa.tasktracker.database.connection.ConnectionProvider;
import com.github.bahaa.tasktracker.database.connection.HikariConnectionProvider;
import com.github.bahaa.tasktracker.database.dao.TaskDAO;
import com.github.bahaa.tasktracker.database.mapper.TaskMapper;
import picocli.CommandLine;

import java.sql.SQLException;

public class Main {
    private static boolean initializeDatabase(ConnectionProvider connectionProvider) {
        try {
            new DBInit(connectionProvider).init();
            return true;
        } catch (SQLException e) {
            System.err.println("Failed to initialize database:" + e.getMessage());
            return false;
        }
    }

    public static void main(String... args) {
        final ConnectionProvider connectionProvider = new HikariConnectionProvider();
        final TaskDAO dao = new TaskDAO(connectionProvider, new TaskMapper());
        final TaskController taskController = new TaskController(dao);

        if (!initializeDatabase(connectionProvider)) {
            System.exit(1);
        }

        CommandLine commandLine = CommandLineFactory.create(taskController);
        int exitCode = commandLine.execute(args);

        System.exit(exitCode);
    }
}
