package cli.commands;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "delete", description = "Remove a task by ID", mixinStandardHelpOptions = true)
public class DeleteCommand implements Runnable {

    @Parameters(index = "0", description = "The ID of the task to delete")
    int id;

    @Override
    public void run() {
    }
}