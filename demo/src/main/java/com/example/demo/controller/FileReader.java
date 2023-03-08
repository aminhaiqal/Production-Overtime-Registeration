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
        int startRow = 3;
        // Read the Excel file using Apache POI
        Workbook workbook = WorkbookFactory.create(file.getInputStream());

        // Get the first sheet in the workbook
        Sheet sheet = workbook.getSheetAt(0);

        StringBuilder sb = new StringBuilder();
        // Get the starting row
        Row firstRow = sheet.getRow(startRow);
        
        // Loop through the rows in the sheet
        for (Row row = firstRow; row != null;) {
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
            //sb.append("\n");
            String[] result = splitStringBuilder(sb, "\t");
            //assignString(result);

            System.out.println("\n");
            break;
        }
        // Close the workbook using try-with-resources
        try (workbook) {}

        // Display response
        System.out.println(sb.toString());

        return "";
    }

    private String[] splitStringBuilder(StringBuilder sb, String delimiter) {
        String[] result = sb.toString().split(delimiter);
        return result;
    }

    public void assignString (String[] result) {
        int staff_id = Integer.parseInt(result[0]);
        String name = result[1];
        String dept = result[2];
        String section = result[3];
        Boolean MON = Boolean.parseBoolean(result[4]);
        Boolean TUE = Boolean.parseBoolean(result[5]);
        Boolean WED = Boolean.parseBoolean(result[6]);
        Boolean THU = Boolean.parseBoolean(result[7]);
        Boolean FRI = Boolean.parseBoolean(result[8]);
        Boolean SAT = Boolean.parseBoolean(result[9]);
        Boolean SUN = Boolean.parseBoolean(result[10]);

        System.out.println(staff_id);
        System.out.println(name);
        System.out.println(dept);
        System.out.println(section);
        System.out.println(MON);
        System.out.println(TUE);
        System.out.println(WED);
        System.out.println(THU);
        System.out.println(FRI);
        System.out.println(SAT);
        System.out.println(SUN);

    }
}
