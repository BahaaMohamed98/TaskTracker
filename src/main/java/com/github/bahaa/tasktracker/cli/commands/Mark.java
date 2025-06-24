package com.github.bahaa.tasktracker.cli.commands;

import com.github.bahaa.tasktracker.controller.TaskController;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "mark", description = "Update task status to done or todo", mixinStandardHelpOptions = true)
public class Mark implements Runnable {
    private final TaskController controller;

    enum MarkType {done, todo}

    @Parameters(index = "0", description = "New status for the task: ${COMPLETION-CANDIDATES}")
    MarkType markType;

    @Parameters(index = "1", description = "The ID of the task to update")
    int id;

    public Mark(TaskController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        switch (markType) {
            case todo -> controller.markTaskTodo(id);
            case done -> controller.markTaskDone(id);
        }
    }
}