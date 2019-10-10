package seedu.address.model.util;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ExcelReader {
    public Workbook workbook;

    public ExcelReader(String filePath) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(new File(filePath));
        this.workbook = workbook;
    }

    public String translate() throws IOException {
        StringBuilder result = new StringBuilder();
        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        for (Row row: sheet) {
            for(Cell cell: row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                result.append(cellValue);
                result.append(" ");
            }
            result.append("\n");

        }
        workbook.close();
        return result.toString();
    }
}
