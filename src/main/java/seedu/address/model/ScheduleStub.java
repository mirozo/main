package seedu.address.model;

import java.util.LinkedList;

public class ScheduleStub {
    private LinkedList<LinkedList<String>> table;

    public ScheduleStub(LinkedList<LinkedList<String>> table) {
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
