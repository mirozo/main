package seedu.scheduler.model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class DateComparator implements Comparator<String> {
    public int compare(String dateStringA, String dateStringB) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dateA = formatter.parse(dateStringA);
            Date dateB = formatter.parse(dateStringB);
            return dateA.compareTo(dateB);
        } catch (ParseException e) {
            return 0;
        }

    }
}
