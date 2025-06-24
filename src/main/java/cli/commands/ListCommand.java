package cli.commands;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "list", description = "Display tasks filtered by status", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable {

    enum ListType {all, done, todo}

    @Parameters(index = "0", defaultValue = "all", description = "Filter tasks by status: ${COMPLETION-CANDIDATES} (default: all)")
    ListType listType = ListType.all;

    @Override
    public void run() {
    }
}