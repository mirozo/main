package seedu.address.model.util;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

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
    public String translate() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        StringBuilder result = new StringBuilder();
        // Getting the Sheet at index zero
        XSSFSheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                //Check the cell type and format accordingly
                result.append(cell.getStringCellValue());
            }
            System.out.println("");
        }
        fileInputStream.close();
        workbook.close();
        return result.toString();
    }
}
