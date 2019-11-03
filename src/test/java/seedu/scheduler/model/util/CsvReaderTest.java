package seedu.scheduler.model.util;

import org.junit.jupiter.api.Test;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvReaderTest {
    private static String sampleCorrectHeader = "11/10/2019,Department A - Person A,Department B - Person B,"
            + "Department C - Person C,Department D - Person D,Department E - Person E,Department F - Person F";
    private static String[] alphabets = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private static String rowOfIntervieweeData = "John Doe,john@u.nus.edu,john@hotmail.com,99999999,NUS,1,publicity,"
            + "\"09/10/2019 18:30-19:00, 10/10/2019 19:00-19:30, 11/10/2019 20:00-20:30\"";
    private static String interviewerFileName_success = "src/test/data/ImportsTest/InterviewerTestData.csv";
    private static String intervieweeFileName_success = "src/test/data/ImportsTest/IntervieweeTestData.csv";

    // ===================================== Interviewers ==============================================
    @Test
    public void getInterviewersFromHeader_success() {
        ArrayList<Interviewer> interviewersFromCorrectSample =
                CsvReader.getInterviewersFromHeader(sampleCorrectHeader.split(","));
        ArrayList<Interviewer> expectedInterviewers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Name interviewerName = new Name("Person " + alphabets[i]);
            Department interviewerDepartment = new Department("Department " + alphabets[i]);
            Interviewer.InterviewerBuilder interviewerBuilder =
                    new Interviewer.InterviewerBuilder(interviewerName,
                            DefaultValues.DEFAULT_PHONE, DefaultValues.DEFAULT_TAGS);
            interviewerBuilder.department(interviewerDepartment);
            interviewerBuilder.availabilities(new ArrayList<>());
            expectedInterviewers.add(interviewerBuilder.build());
        }
        assertEquals(expectedInterviewers, interviewersFromCorrectSample);
    }

    @Test
    public void readInterviewers_success() throws IOException {
        CsvReader reader = new CsvReader(interviewerFileName_success);
        ArrayList<Interviewer> testOutput = reader.readInterviewers();

        ArrayList<Interviewer> expectedInterviewers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Name interviewerName = new Name("Person " + alphabets[i]);
            Department interviewerDepartment = new Department("Department " + alphabets[i]);
            Interviewer.InterviewerBuilder interviewerBuilder =
                    new Interviewer.InterviewerBuilder(interviewerName,
                            DefaultValues.DEFAULT_PHONE, DefaultValues.DEFAULT_TAGS);
            interviewerBuilder.department(interviewerDepartment);
            interviewerBuilder.availabilities(new ArrayList<>());
            expectedInterviewers.add(interviewerBuilder.build());
        }
        assertEquals(expectedInterviewers, testOutput);
    }

    // ===================================== Interviewees ==============================================

    @Test
    public void readInterviewee_success() throws IOException {
        CsvReader reader = new CsvReader(intervieweeFileName_success);
        ArrayList<Interviewee> testOutput = reader.readInterviewees();
        ArrayList<Interviewee> expectedInterviewees = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Name name = new Name("John " + alphabets[i]);
            Emails emails = new Emails(new HashMap<>());
            emails.addNusEmail(new Email("john" + alphabets[i] + "@u.nus.edu"));
            emails.addPersonalEmail(new Email("john" + alphabets[i] + "@hotmail.com"));
            Phone phone = new Phone("9999999" + i);
            Faculty faculty = new Faculty("NUS");
            Integer yearOfStudy = 1;
            ArrayList<Department> choiceOfDepartments = new ArrayList<>();
            choiceOfDepartments.add(new Department("publicity"));
            List<Slot> availableTimeSlots = new ArrayList<>();
            availableTimeSlots.add(Slot.fromString("09/10/2019 18:30-19:00"));
            availableTimeSlots.add(Slot.fromString("10/10/2019 19:00-19:30"));
            availableTimeSlots.add(Slot.fromString("11/10/2019 20:00-20:30"));
            Interviewee interviewee = new Interviewee.IntervieweeBuilder(name, phone, DefaultValues.DEFAULT_TAGS)
                    .availableTimeslots(availableTimeSlots)
                    .departmentChoices(choiceOfDepartments)
                    .emails(emails)
                    .yearOfStudy(yearOfStudy)
                    .faculty(faculty)
                    .build();
            expectedInterviewees.add(interviewee);
        }
        assertEquals(expectedInterviewees, testOutput);
    }

    @Test
    public void getAllSlots_success() {
        List<Slot> testOutput = CsvReader.getAllSlots(rowOfIntervieweeData.split(","));
        List<Slot> expectedSlots = new ArrayList<>();
        expectedSlots.add(Slot.fromString("09/10/2019 18:30-19:00"));
        expectedSlots.add(Slot.fromString("10/10/2019 19:00-19:30"));
        expectedSlots.add(Slot.fromString("11/10/2019 20:00-20:30"));
        assertEquals(expectedSlots, testOutput);
    }
}
