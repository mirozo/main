package seedu.scheduler.logic.commands;
import static seedu.scheduler.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.scheduler.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.scheduler.commons.core.Messages;
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
        ImportCommand importCommand = new ImportCommand(INTERVIEWER + " " + INTERVIEWER_FILE_PATH);
        CommandResult expectedCommandResult = new CommandResult(ImportCommand.SUCCESS_MESSAGE, false, false);
        Model expectedModel = new ModelManager();
        for (Interviewer interviewer: SampleInterviewer.getSampleListOfInterviewers()) {
            expectedModel.addInterviewer(interviewer);
        }
        assertCommandSuccess(importCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void interviewerImportCommand_throwsCommandException() {
        ImportCommand importCommandFailure = new ImportCommand(INTERVIEWER + " not a valid file");
        assertCommandFailure(importCommandFailure, model, ImportCommand.FILE_DOES_NOT_EXIST);
    }

    // ===================================== Interviewees ==============================================

    @Test
    public void intervieweeImportCommand_success() {
        ImportCommand importCommand = new ImportCommand(INTERVIEWEE + " " + INTERVIEWEE_FILE_PATH);
        CommandResult expectedCommandResult = new CommandResult(ImportCommand.SUCCESS_MESSAGE, false, false);
        Model expectedModel = new ModelManager();
        for (Interviewee interviewee: SampleInterviewee.getSampleIntervieweeList()) {
            expectedModel.addInterviewee(interviewee);
        }
        assertCommandSuccess(importCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void intervieweeImportCommand_throwsCommandException() {
        ImportCommand importCommandFailure = new ImportCommand(INTERVIEWEE + " not a valid file");
        assertCommandFailure(importCommandFailure, model, ImportCommand.FILE_DOES_NOT_EXIST);
    }

}
