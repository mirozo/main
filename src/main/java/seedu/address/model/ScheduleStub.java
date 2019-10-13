package seedu.address.model;

import java.util.ArrayList;

public class ScheduleStub {
    private ArrayList<ArrayList<String>> table;

    public ScheduleStub(ArrayList<ArrayList<String>> table) {
        this.table = table;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.table.size(); i++) {
            for (int j = 0; j < this.table.get(0).size(); j++) {
                sb.append(table.get(i).get(j));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
