package com.github.bahaa.tasktracker.cli.commands;

import com.github.bahaa.tasktracker.controller.TaskController;
import com.github.bahaa.tasktracker.database.model.Task;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.Objects;

@Command(name = "add", description = "Create a new task", mixinStandardHelpOptions = true)
public class Add implements Runnable {
    private final TaskController controller;

    @Parameters(index = "0", description = "The title of the task")
    String title;

    @Option(names = {"-d", "--description"}, description = "Optional description for the task")
    String description;

    public Add(TaskController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        Task task = controller.addTask(title, Objects.requireNonNullElse(description, ""));
        System.out.println("Task added successfully (ID: " + task.getId() + ")");
    }
}