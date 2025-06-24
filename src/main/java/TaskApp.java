import cli.commands.*;
import controller.TaskController;
import database.DBInit;
import database.connection.ConnectionProvider;
import database.connection.HikariConnectionProvider;
import database.dao.TaskDAO;
import database.mapper.TaskMapper;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;

import java.sql.SQLException;

@Command(name = "task-cli", description = "A command-line task management application", version = "1.0.0", mixinStandardHelpOptions = true)
public class TaskApp implements Runnable {
    @Spec
    CommandSpec spec;

    @Override
    public void run() {
        spec.commandLine().usage(System.out);
    }

    public static void main(String... args) {
        final ConnectionProvider connectionProvider = new HikariConnectionProvider();
        final TaskDAO dao = new TaskDAO(connectionProvider, new TaskMapper());
        final TaskController taskController = new TaskController(dao);

        try {
            new DBInit(connectionProvider).init();
        } catch (SQLException e) {
            System.err.println("Failed to initialize database:" + e.getMessage());
            return;
        }

        new CommandLine(new TaskApp())
                .addSubcommand(new Add(taskController))
                .addSubcommand(new Update(taskController))
                .addSubcommand(new Delete(taskController))
                .addSubcommand(new Mark(taskController))
                .addSubcommand(new List(taskController))
                .execute(args);
    }
}
