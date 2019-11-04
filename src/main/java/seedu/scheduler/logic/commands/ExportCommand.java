package seedu.scheduler.logic.commands;

import seedu.scheduler.model.Model;
import seedu.scheduler.model.util.CsvWriter;

public class ExportCommand extends Command {
    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": export schedules to specified .csv file"
            + "Example: " + COMMAND_WORD + "<FILE_NAME.csv>";
    public static final String SUCCESS_MESSAGE = "Data exported successfully.";

    private String destinationFile;

    public ExportCommand(String args) {
        this.destinationFile = args;
    }

    public CommandResult execute(Model model) {
        CsvWriter writer = new CsvWriter(destinationFile, model);

    }
}
