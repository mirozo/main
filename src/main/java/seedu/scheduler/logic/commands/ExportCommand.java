package seedu.scheduler.logic.commands;

public class ExportCommand extends Command {
    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": export schedules to specified .csv file"
            + "Example: " + COMMAND_WORD + "<FILE_NAME.csv>";
    public static final String SUCCESS_MESSAGE = "Data exported successfully.";

    public CommandResult execute() {
        
    }
}
