package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/*
 * _____________________________________________________________________________
 * |  • This class provides methods to read data from an Excel file. 
 * |    It uses the Apache POI library to handle the Excel file format.
 * |  • The class supports reading data from both XLS and XLSX file formats.
 * |  • To use this class, create an instance of the ExcelReader class and 
 * |    call the readExcelFile() method, passing in the path to the Excel 
 * |    file as a parameter.
 * |  • The method returns a List of List of String objects, representing the 
 * |    rows and columns of the Excel sheet.
 * |  • The class also provides a few utility methods to help with parsing cell 
 * |    values and formatting dates.
 * |____________________________________________________________________________
 * |  • Code Complexity: O(n^2)
 * |____________________________________________________________________________
 */

@RestController
public class FileReader {
    @PostMapping("/upload")
    public String readExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("Starting to read file...");
        // Read the Excel file using Apache POI
        Workbook workbook = WorkbookFactory.create(file.getInputStream());

        // Get the first sheet in the workbook
        Sheet sheet = workbook.getSheetAt(0);

        StringBuilder sb = new StringBuilder();
        // Loop through the rows in the sheet
        for (Row row : sheet) {
            // Loop through the cells in the row
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        sb.append(cell.getStringCellValue()).append("\t");
                        break;
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            sb.append(cell.getDateCellValue()).append("\t");
                        } else {
                            sb.append(cell.getNumericCellValue()).append("\t");
                        }
                        break;
                    case BOOLEAN:
                        sb.append(cell.getBooleanCellValue()).append("\t");
                        break;
                    case FORMULA:
                        sb.append(cell.getCellFormula()).append("\t");
                        break;
                    default:
                        sb.append("\t");
                }
            }
            sb.append("\n");
        }
        // Close the workbook using try-with-resources
        try (workbook) {}

        // Display response
        System.out.println(sb.toString());

        return "";
    }

}
