package com.github.bahaa.tasktracker.database.mapper;

import com.github.bahaa.tasktracker.database.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TaskMapper {
    public Task from(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");
        boolean done = resultSet.getBoolean("is_done");
        LocalDateTime createdAt = ((Timestamp) resultSet.getObject("created_at")).toLocalDateTime();

        return new Task(id, title, description, done, createdAt);
    }
}
