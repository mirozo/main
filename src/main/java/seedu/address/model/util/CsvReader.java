package seedu.address.model.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import seedu.address.model.Model;
import seedu.address.model.person.DefaultValues;
import seedu.address.model.person.Department;
import seedu.address.model.person.Interviewee;
import seedu.address.model.person.Interviewer;
import seedu.address.model.person.Name;
import seedu.address.model.person.Slot;

/**
 * Helper class to read .csv files (Comma separated values).
 */
public class CsvReader {
    private String filePath;
    private Model model;

    /**
     * Constructor for CsvReader object to read from excel.
<<<<<<< HEAD
     * @param filePath Path of csv file
=======
     * @param filePath
>>>>>>> 8d5e5072afe29f45a0772570f143a75cefd6b715
     */
    public CsvReader(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads from excel and returns the corresponding string.
     * @return String
     * @throws IOException if input file is not found.
     */
    public ArrayList<Interviewer> readInterviewers() throws IOException {

        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        String firstLine = csvReader.readLine();
        int numberOfDays = getValue(firstLine.split(",")[0]);
        int numberOfColumns = getValue(firstLine.split(",")[1]) + 1;
        if (numberOfColumns < 1) {
            ArrayList<Interviewer> emptyList = new ArrayList<>();
            return emptyList;
        }
        String date = null;
        ArrayList<Interviewer> interviewers = new ArrayList<>();
        csvReader.readLine(); //removes next line

        for (int i = 0; i < numberOfDays; i++) {
            String row;
            boolean firstEncounter = true;
            boolean firstRow = true;
            while ((row = csvReader.readLine()) != null) {
                String[] rowData = row.split(",", -1);
                if (rowData[0].equals("")) {
                    if (firstEncounter) {
                        firstEncounter = false;
                    } else {
                        break;
                    }
                } else if (firstRow) {
                    if (i == 0) { //if this is the first table(day) being read
                        date = rowData[0];
                        interviewers = getInterviewersFromHeader(rowData);
                    }
                    firstRow = false;
                } else {
                    if (date == null) {
                        throw new FileFormatException();
                    }
                    updateInterviewersSlotsFromData(interviewers, rowData, date);
                }
            }
        }
        csvReader.close();
        return interviewers;
    }

    private static int getValue(String element) {
        String[] strings = element.split("=");
        String trimmedString = strings[1].trim();
        return Integer.parseInt(trimmedString);
    }

    private boolean fileExists() {
        File file = new File(this.filePath);
        return file.exists();
    }

    private static Name getInterviewerName(String cell) {
        String[] strings = cell.split("-");
        return new Name(strings[1].trim());
    }

    private static Department getInterviewerDepartment(String cell) {
        String[] strings = cell.split("-");
        return new Department(strings[0].trim());
    }

    private static String getStartTime(String timeRange) {
        String[] strings = timeRange.split("-");
        return strings[0].trim();
    }

    private static String getEndTime(String timeRange) {
        String[] strings = timeRange.split("-");
        return strings[1].trim();
    }

    private static ArrayList<Interviewer> getInterviewersFromHeader(String[] rowData) {
        ArrayList<Interviewer> interviewers = new ArrayList<>();
        for (int j = 1; j < rowData.length; j++) {
            Name interviewerName = getInterviewerName(rowData[j]);
            Department interviewerDepartment = getInterviewerDepartment(rowData[j]);
            Interviewer.InterviewerBuilder interviewerBuilder =
                    new Interviewer.InterviewerBuilder(interviewerName,
                            DefaultValues.DEFAULT_PHONE, DefaultValues.DEFAULT_TAGS);
            interviewerBuilder.department(interviewerDepartment);
            interviewerBuilder.availabilities(new ArrayList<>());
            interviewers.add(interviewerBuilder.build());
        }
        return interviewers;
    }

    private static void updateInterviewersSlotsFromData(ArrayList<Interviewer> interviewers,
                                                                String[] rowData, String date) {
        String timing = rowData[0];
        String startTime = getStartTime(timing);
        String endTime = getEndTime(timing);
        Slot slot = new Slot(date, startTime, endTime);
        for (int j = 1; j < rowData.length; j++) {
            if (rowData[j].trim().equals("1")) {
                Interviewer currentInterviewer = interviewers.get(j - 1);
                List<Slot> currentSlots = currentInterviewer.getAvailabilities();
                currentSlots.add(slot);
                List<Slot> updatedSlots = cloneSlots(currentSlots);
                currentInterviewer.setAvailabilities(updatedSlots);
            }
        }
    }

   private static List<Slot> cloneSlots(List<Slot> list) {
        List<Slot> listClone = new ArrayList<>();
        for (Slot slot : list) {
            listClone.add(slot);
        }
        return listClone;
    }
}
