package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{


        public static void main(String[] args) {
        String filePath = "src/main/resources/testdata/Beymen.xlsx";
        String sheetName = "Sheet1"; // Replace with the actual sheet name

            try (FileInputStream fileInputStream = new FileInputStream(filePath);
                 Workbook workbook = new XSSFWorkbook(fileInputStream)) {

                Sheet sheet = workbook.getSheet(sheetName);

                if (sheet != null) {
                    Row row = sheet.getRow(0);

                    if (row != null) {
                        Cell cell = row.getCell(1);

                        if (cell != null) {
                            String cellValue = getCellValueAsString(cell);
                            System.out.println("Cell Value: " + cellValue);
                        } else {
                            System.out.println("First Cell is null");
                        }
                    } else {
                        System.out.println("First Row is null");
                    }
                } else {
                    System.out.println("Sheet not found: " + sheetName);
                    System.out.println("Available sheets:");

                    for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                        System.out.println(workbook.getSheetName(i));
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return ""; // Return an empty string if the cell is null
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
