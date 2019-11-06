package seedu.scheduler.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.scheduler.model.FilePath;
import seedu.scheduler.model.Model;
import seedu.scheduler.model.ModelManager;

import static seedu.scheduler.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.scheduler.logic.commands.CommandTestUtil.assertCommandSuccess;


public class ExportCommandTest {
    public static final String DESTINATION_FILE = "src/test/data/ImportsTest/storage.csv";
    public static Model model = new ModelManager();

    @Test
    public void execute_ExportCommand_success() {
        ExportCommand exportCommand = new ExportCommand(new FilePath(DESTINATION_FILE));
        CommandResult expectedCommandResult = new CommandResult(ExportCommand.SUCCESS_MESSAGE, false, false);
        model.setScheduled(true);
        assertCommandSuccess(exportCommand, model, expectedCommandResult, model);
    }

    @Test
    public void execute_ExportCommand_throwsCommandException() {
        ExportCommand exportCommand = new ExportCommand(new FilePath(DESTINATION_FILE));
        model.setScheduled(false);
        assertCommandFailure(exportCommand, model, ExportCommand.NOT_SCHEDULED_ERROR);
    }
}
