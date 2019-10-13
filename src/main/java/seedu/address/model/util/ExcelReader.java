package seedu.address.model.util;

import seedu.address.model.ScheduleStub;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Helper class to read .csv files (Comma separated values).
 */
public class ExcelReader {
    private String filePath;

    /**
     * Constructor for ExcelReader object to read from excel.
     * @param filePath
     */
    public ExcelReader(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads from excel and returns the corresponding string.
     * @return String
     * @throws IOException if input file is not of .xlsx extension
     */
    public ArrayList<ScheduleStub> read() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(filePath));
        String firstLine = csvReader.readLine();
        int numberOfDays = getValue(firstLine.split(",")[0]);
        int numberOfColumns = getValue(firstLine.split(",")[1]);
        ArrayList<ScheduleStub> schedules = new ArrayList<>();
        for (int i = 0; i < numberOfDays; i++) {
            ArrayList<ArrayList<String>> schedule = new ArrayList<>();
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",", -1);
                if (data[0].equals("")) {
                    
                } else if (numberOfColumns != 0) {
                    ArrayList<String> dataRow = new ArrayList<>();
                    for (int j = 0; j < numberOfColumns; j++) {
                        String element = data[j];
                        dataRow.add(element);
                    }
                    schedule.add(dataRow);
                }
            }
            schedules.add(new ScheduleStub(schedule));
        }
        csvReader.close();
        return schedules;
    }

    private static int getValue(String element) {
        String[] strings = element.split("= ");
        return Integer.parseInt(strings[1]);
    }
}
