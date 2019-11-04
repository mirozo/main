package seedu.scheduler.model;

import java.io.File;

import static java.util.Objects.requireNonNull;
import static seedu.scheduler.commons.util.AppUtil.checkArgument;

public class FilePath {
    public static final String MESSAGE_CONSTRAINTS =
            "File path is invalid. It must lead to a valid file with .csv extension.";
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
        String[] parts = filePath.split("\\.");
        try {
            return parts[parts.length - 1].equals(FILE_EXTENSION) && !parts[parts.length-2].equals("");
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    /**
     * Checks if file exists.
     * @return boolean true if file exists, false otherwise
     */
    public boolean exists() {
        File file = new File(this.getValue());
        return file.exists();
    }

    @Override
    public String toString() {
        return value;
    }
}
