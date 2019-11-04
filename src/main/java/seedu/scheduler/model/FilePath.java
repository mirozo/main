package seedu.scheduler.model;

import java.io.File;

import static java.util.Objects.requireNonNull;
import static seedu.scheduler.commons.util.AppUtil.checkArgument;

public class FilePath {
    public static final String MESSAGE_CONSTRAINTS =
            "File paths must be a valid file with .csv extension";
    public static final String FILE_EXTENSION = "csv";
    public final String value;

    public FilePath(String filePath) {
        requireNonNull(filePath);
        checkArgument(isValidFilePath(filePath), MESSAGE_CONSTRAINTS);
        this.value = filePath;
    }

    public String getValue() {
        return value;
    }

    public static boolean isValidFilePath(String filePath) {
        String[] parts = filePath.split(".");
        if (exists(filePath)) {
            return parts[1].equals(FILE_EXTENSION);
        } else {
            return false;
        }
    }

    /**
     * Checks if file exists.
     * @return boolean true if file exists, false otherwise
     */
    private static boolean exists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

    @Override
    public String toString() {
        return value;
    }
}
