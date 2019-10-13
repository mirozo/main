package seedu.address.logic.commands;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import seedu.address.model.Model;
import seedu.address.model.ScheduleStub;
import seedu.address.model.util.ExcelReader;


/**
 * Import excel file containing interviewee's information.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Import excel file containing "
            + "interviewee's information.\n"
            + "Example: " + COMMAND_WORD + "<excelFilePath>";

    public static final String SHOWING_MESSAGE = "Data imported successfully.";

    private String filePath;

    public ImportCommand(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) {
        ArrayList<ScheduleStub> result;
        try {
            ExcelReader excelReader = new ExcelReader(filePath);
            result = excelReader.read();
            return new CommandResult(result.get(0).toString(), false, false);
        } catch (IOException e) {
            e.printStackTrace();
            return new CommandResult("Failed", false, false);
        }



    }
}
