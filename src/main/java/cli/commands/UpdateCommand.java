package cli.commands;

import controller.TaskController;
import database.model.Task;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name = "update", description = "Modify an existing task", mixinStandardHelpOptions = true)
public class UpdateCommand implements Runnable {
    private final TaskController controller;

    @Spec
    CommandSpec spec;

    @Parameters(index = "0", description = "The ID of tfe task to update")
    int id;

    @Option(names = {"-t", "--title"}, description = "New title for the task")
    String title;

    @Option(names = {"-d", "--description"}, description = "New description for the task")
    String description;

    public UpdateCommand(TaskController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        if (title == null && description == null) {
            throw new CommandLine.ParameterException(spec.commandLine(), "Error: At least one of --title or --description must be specified");
        }

        Task task = controller.getTask(id);

        if (title != null) {
            task.setTitle(title);
        }

        if (description != null) {
            task.setDescription(description);
        }

        controller.updateTask(task);
    }
}