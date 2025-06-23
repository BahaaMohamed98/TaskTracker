import database.DBInit;
import database.connection.ConnectionProvider;
import database.connection.HikariConnectionProvider;
import database.dao.TaskDAO;
import database.mapper.TaskMapper;
import database.model.Task;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConnectionProvider connectionProvider = new HikariConnectionProvider();
        DBInit dbInit = new DBInit(connectionProvider);

        try {
            dbInit.init();
            System.out.println("db initialized successfully!");
        } catch (SQLException e) {
            System.err.println("Failed to initialize db: " + e.getMessage());
            return;
        }

        TaskDAO taskDAO = new TaskDAO(connectionProvider, new TaskMapper());

        taskDAO.deleteAll();
        Task learnJava = taskDAO.save(new Task("Learn Java", "Java is cool"));
        Task code = taskDAO.save(new Task("Code stuff"));
        taskDAO.save(new Task("Shave hair"));

        taskDAO.delete(learnJava);

        code.setDescription("code a project");
        code.setDone(true);
        taskDAO.update(code);

        List<Task> tasks = taskDAO.findAll();
        tasks.forEach(System.out::println);
    }
}