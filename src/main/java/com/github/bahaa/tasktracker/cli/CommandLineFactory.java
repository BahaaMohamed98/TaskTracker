package com.github.bahaa.tasktracker.cli;

import com.github.bahaa.tasktracker.cli.commands.*;
import com.github.bahaa.tasktracker.controller.TaskController;
import picocli.CommandLine;

public class CommandLineFactory {
    public static CommandLine create(TaskController controller) {
        CommandLine commandLine = new CommandLine(new TaskCLI())
                .addSubcommand(new Add(controller))
                .addSubcommand(new Update(controller))
                .addSubcommand(new Delete(controller))
                .addSubcommand(new Mark(controller))
                .addSubcommand(new List(controller));

        commandLine.setExecutionExceptionHandler((ex, cmdline, parseResult) -> {
            System.err.println("Error: " + ex.getMessage());
            return 1;
        });

        return commandLine;
    }
}
