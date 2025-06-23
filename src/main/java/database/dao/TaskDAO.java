package database.dao;

import database.connection.ConnectionProvider;
import database.mapper.TaskMapper;
import database.model.Task;
import exception.TaskDAOException;
import exception.TaskNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    private final ConnectionProvider connectionProvider;
    private final TaskMapper mapper;

    public TaskDAO(ConnectionProvider connectionProvider, TaskMapper mapper) {
        this.connectionProvider = connectionProvider;
        this.mapper = mapper;
    }

    public Task save(Task task) {
        try (var connection = connectionProvider.getConnection();
             var ps = connection.prepareStatement("INSERT INTO TASKS (title, description, is_done, created_at) values (?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)
        ) {

            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setBoolean(3, task.isDone());
            ps.setObject(4, task.getCreatedAt());

            if (ps.executeUpdate() == 1) {
                var keys = ps.getGeneratedKeys();

                if (keys.next()) {
                    int id = keys.getInt("id");
                    return task.withId(id);
                }
            }

            return null;
        } catch (SQLException e) {
            throw new TaskDAOException("Failed to save Task", e);
        }
    }

    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();

        try (var connection = connectionProvider.getConnection();
             var stmt = connection.createStatement()
        ) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM TASKS");

            while (resultSet.next()) {
                tasks.add(mapper.from(resultSet));
            }

            return tasks;
        } catch (SQLException e) {
            throw new TaskDAOException("Failed to fetch all tasks", e);
        }
    }

    public void update(Task task) {
        try (var connection = connectionProvider.getConnection();
             var ps = connection.prepareStatement("UPDATE TASKS SET TITLE = ?, DESCRIPTION = ?, IS_DONE = ? WHERE ID = ?")
        ) {
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());
            ps.setBoolean(3, task.isDone());
            ps.setInt(4, task.getId());

            if (ps.executeUpdate() != 1) {
                throw new TaskNotFoundException(task.getId());
            }
        } catch (SQLException e) {
            throw new TaskDAOException("Failed to update task", e);
        }
    }

    public void delete(Task task) {
        try (var connection = connectionProvider.getConnection();
             var ps = connection.prepareStatement("DELETE FROM TASKS WHERE id = ?")
        ) {
            ps.setInt(1, task.getId());

            if (ps.executeUpdate() != 1) {
                throw new TaskNotFoundException(task.getId());
            }
        } catch (SQLException e) {
            throw new TaskDAOException("Failed to delete task" + e);
        }
    }

    public void deleteAll() {
        try (var connection = connectionProvider.getConnection();
             var stmt = connection.createStatement()
        ) {
            //noinspection SqlWithoutWhere
            stmt.executeUpdate("DELETE FROM TASKS");
        } catch (SQLException e) {
            throw new TaskDAOException("Failed to delete all tasks" + e);
        }
    }
}
