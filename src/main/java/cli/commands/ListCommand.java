package cli.commands;

import controller.TaskController;
import database.model.Task;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.List;

@Command(name = "list", description = "Display tasks filtered by status", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable {
    private final TaskController controller;

    enum ListType {all, done, todo}

    @Parameters(index = "0", defaultValue = "all", description = "Filter tasks by status: ${COMPLETION-CANDIDATES} (default: all)")
    ListType listType = ListType.all;

    public ListCommand(TaskController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        List<Task> tasks = switch (listType) {
            case all -> controller.getAllTasks();
            case done -> controller.getDoneTasks();
            case todo -> controller.getTodoTasks();
        };

        System.out.println("Tasks:");
        tasks.forEach(System.out::println);
    }
}