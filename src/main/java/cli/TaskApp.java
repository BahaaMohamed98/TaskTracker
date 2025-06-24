package cli;

import cli.commands.*;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;

@Command(name = "task-cli", description = "A command-line task management application",
        version = "1.0.0", mixinStandardHelpOptions = true,
        subcommands = {
                AddCommand.class,
                DeleteCommand.class,
                ListCommand.class,
                MarkCommand.class,
                UpdateCommand.class,
        }
)
public class TaskApp implements Runnable {
    @Spec
    CommandSpec spec;

    @Override
    public void run() {
        spec.commandLine().usage(System.out);
    }
}