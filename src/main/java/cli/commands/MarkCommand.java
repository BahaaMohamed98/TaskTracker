package cli.commands;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "mark", description = "Update task status to done or todo", mixinStandardHelpOptions = true)
public class MarkCommand implements Runnable {

    enum MarkType {done, todo}

    @Parameters(index = "0", description = "New status for the task: ${COMPLETION-CANDIDATES}")
    MarkType markType;

    @Parameters(index = "1", description = "The ID of the task to update")
    int id;

    @Override
    public void run() {
    }
}