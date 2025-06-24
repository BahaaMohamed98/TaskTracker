package cli.commands;

import controller.TaskController;
import database.model.Task;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "add", description = "Create a new task", mixinStandardHelpOptions = true)
public class AddCommand implements Runnable {
    private final TaskController controller;

    @Parameters(index = "0", description = "The title of the task")
    String title;

    @Option(names = {"-d", "--description"}, description = "Optional description for the task")
    String description;

    public AddCommand(TaskController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        Task task = controller.addTask(title, description == null ? "" : description);
        System.out.println("Task added successfully (ID: " + task.getId() + ")");
    }
}