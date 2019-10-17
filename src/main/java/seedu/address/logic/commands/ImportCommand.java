package seedu.address.logic.commands;

import java.io.IOException;
import java.util.ArrayList;

import seedu.address.model.Model;
import seedu.address.model.Schedule;
import seedu.address.model.util.ExcelReader;


/**
 * Import excel file containing interviewee's information.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Import excel file containing "
            + "interviewer or interviewee's information.\n"
            + "Example: " + COMMAND_WORD + "interviewer/interviewee" + "<csvFilePath>";

    public static final String SHOWING_MESSAGE = "Data imported successfully.";

    private String filePath;
    private String type;

    public ImportCommand(String args) {
        String[] strings = args.split(" ");
        this.type = strings[0];
        this.filePath = strings[1];
    }

    @Override
    public CommandResult execute(Model model) {
        ArrayList<Schedule> result;
        try {
            if (this.type.equals("interviewer")) {
                ExcelReader excelReader = new ExcelReader(filePath);
                result = excelReader.read();
                return new CommandResult(result.get(0).toString(), false, false);
            } else if (this.type.equals("interviewee")) {
                return new CommandResult("Not implemented yet", false, false);
            } else {
                return new CommandResult(MESSAGE_USAGE, false, false);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new CommandResult("Failed", false, false);
        }



    }
}
