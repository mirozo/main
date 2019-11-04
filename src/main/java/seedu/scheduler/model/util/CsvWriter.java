package seedu.scheduler.model.util;

import javafx.collections.ObservableList;
import seedu.scheduler.model.Model;
import seedu.scheduler.model.Schedule;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {
    private String destinationFile;
    private Model model;

    public CsvWriter(String destinationFile, Model model) {
        this.destinationFile = destinationFile;
        this.model = model;
    }

    public void writeSchedulesToFile() throws IOException {
        FileWriter csvWriter = new FileWriter(destinationFile);
        List<Schedule> schedules = model.getSchedulesList();
        for (Schedule schedule: schedules) {
            ObservableList<ObservableList<String>> rows = schedule.getObservableList();
            for (ObservableList<String> rowData: rows) {
                csvWriter.append(String.join(",", rowData));
                csvWriter.append("\n")
            }
            csvWriter.append("\n").append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }
}
