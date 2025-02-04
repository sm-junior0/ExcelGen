import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.ResultSet;

public class ExcelExporter {

    // Write employee data to an Excel file
    public static void exportToExcel(ResultSet resultSet, String fileName) throws Exception {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Employees");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("First Name");
            headerRow.createCell(2).setCellValue("Last Name");
            headerRow.createCell(3).setCellValue("role Id");

            // Fill rows with data
            int rowIndex = 1;
            while (resultSet.next()) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(resultSet.getInt("id"));
                row.createCell(1).setCellValue(resultSet.getString("first_name"));
                row.createCell(2).setCellValue(resultSet.getString("last_name"));
                row.createCell(3).setCellValue(resultSet.getInt("role_id"));
            }

            // Write to file
            try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
                workbook.write(outputStream);
            }
        }
    }
}