package cli.commands;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "update", description = "Modify an existing task", mixinStandardHelpOptions = true)
public class UpdateCommand implements Runnable {

    @Parameters(index = "0", description = "The ID of the task to update")
    int id;

    @Parameters(index = "1", description = "New title for the task")
    String title;

    @Option(names = {"-d", "--description"}, description = "New description for the task")
    String description;

    @Override
    public void run() {
    }
}