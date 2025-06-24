import cli.TaskApp;
import picocli.CommandLine;

public class Main {
    public static void main(String... args) {
        new CommandLine(new TaskApp()).execute(args);
    }
}