package com.github.bahaa.tasktracker.cli.commands;

import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;

@Command(name = "task-cli", description = "A command-line task management application", version = "1.0.0", mixinStandardHelpOptions = true)
public class TaskCLI implements Runnable {
    @Spec
    CommandSpec spec;

    @Override
    public void run() {
        spec.commandLine().usage(System.out);
    }
}
