package seedu.scheduler.logic.commands;
import static seedu.scheduler.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.scheduler.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.scheduler.commons.core.Messages;
import seedu.scheduler.model.FilePath;
import seedu.scheduler.model.Model;
import seedu.scheduler.model.ModelManager;
import seedu.scheduler.model.person.DefaultValues;
import seedu.scheduler.model.person.Department;
import seedu.scheduler.model.person.Email;
import seedu.scheduler.model.person.Emails;
import seedu.scheduler.model.person.Faculty;
import seedu.scheduler.model.person.Interviewee;
import seedu.scheduler.model.person.Interviewer;
import seedu.scheduler.model.person.Name;
import seedu.scheduler.model.person.Phone;
import seedu.scheduler.model.person.Role;
import seedu.scheduler.model.person.RoleType;
import seedu.scheduler.model.person.Slot;
import seedu.scheduler.testutil.SampleInterviewee;
import seedu.scheduler.testutil.SampleInterviewer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ImportCommandTest {
    private static final String INTERVIEWER_FILE_PATH = "src/test/data/ImportsTest/InterviewerTestData.csv";
    private static final String INTERVIEWEE_FILE_PATH = "src/test/data/ImportsTest/IntervieweeTestData.csv";
    private static final String INTERVIEWER = "interviewer";
    private static final String INTERVIEWEE = "interviewee";

    private Model model = new ModelManager();

    // ===================================== Interviewers ==============================================

    @Test
    public void interviewerImportCommand_success() {
        ImportCommand importCommand = new ImportCommand(new Role(INTERVIEWER),
                new FilePath(INTERVIEWER_FILE_PATH));
        CommandResult expectedCommandResult = new CommandResult(ImportCommand.SUCCESS_MESSAGE, false, false);
        Model expectedModel = new ModelManager();
        for (Interviewer interviewer: SampleInterviewer.getSampleListOfInterviewers()) {
            expectedModel.addInterviewer(interviewer);
        }
        assertCommandSuccess(importCommand, model, expectedCommandResult, expectedModel);
    }


    // ===================================== Interviewees ==============================================

    @Test
    public void intervieweeImportCommand_success() {
        ImportCommand importCommand = new ImportCommand(new Role(INTERVIEWEE),
                new FilePath(INTERVIEWEE_FILE_PATH));
        CommandResult expectedCommandResult = new CommandResult(ImportCommand.SUCCESS_MESSAGE, false, false);
        Model expectedModel = new ModelManager();
        for (Interviewee interviewee: SampleInterviewee.getSampleIntervieweeList()) {
            expectedModel.addInterviewee(interviewee);
        }
        assertCommandSuccess(importCommand, model, expectedCommandResult, expectedModel);
    }

}
