package com.github.bahaa.tasktracker.cli.commands;

import com.github.bahaa.tasktracker.controller.TaskController;
import com.github.bahaa.tasktracker.database.model.Task;
import picocli.CommandLine.*;
import picocli.CommandLine.Model.CommandSpec;

@Command(name = "update", description = "Modify an existing task", mixinStandardHelpOptions = true)
public class Update implements Runnable {
    private final TaskController controller;

    @Spec
    CommandSpec spec;

    @Parameters(index = "0", description = "The ID of the task to update")
    int id;

    @Option(names = {"-t", "--title"}, description = "New title for the task")
    String title;

    @Option(names = {"-d", "--description"}, description = "New description for the task")
    String description;

    public Update(TaskController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        if (title == null && description == null) {
            throw new ParameterException(spec.commandLine(), "At least one of --title or --description must be specified");
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