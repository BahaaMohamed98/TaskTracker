package cli.commands;

import controller.TaskController;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "delete", description = "Remove a task by ID", mixinStandardHelpOptions = true)
public class DeleteCommand implements Runnable {
    private final TaskController controller;

    @Parameters(index = "0", description = "The ID of the task to delete")
    int id;

    public DeleteCommand(TaskController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        controller.deleteTask(id);
    }
}