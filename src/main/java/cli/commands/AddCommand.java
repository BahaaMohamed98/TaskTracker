package cli.commands;

import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name = "add", description = "Create a new task", mixinStandardHelpOptions = true)
public class AddCommand implements Runnable {
    @Spec
    CommandSpec spec;

    @Parameters(index = "0", description = "The title of the task")
    String title;

    @Option(names = {"-d", "--description"}, description = "Optional description for the task")
    String description;

    @Override
    public void run() {
    }
}