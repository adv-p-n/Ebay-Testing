package com.ebay.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

    public static Object[][] readExcelData(String filePath, String sheetName) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheet(sheetName);
        List<Object[]> dataList = new ArrayList<>();

        boolean isFirstRow = true; // Flag to skip header

        for (Row row : sheet) {
            if (isFirstRow) { 
                isFirstRow = false; // Skip the first row
                continue;
            }

            if (row.getCell(0) == null || row.getCell(1) == null) continue; // Skip empty rows

            String productName = row.getCell(0).getStringCellValue();
            String expectedPrice = row.getCell(1).toString(); // Handles both string & numeric cells

            if (!productName.trim().isEmpty() && !expectedPrice.trim().isEmpty()) {
                dataList.add(new Object[]{productName, expectedPrice});
            }
        }

        file.close();
        workbook.close();

        return dataList.toArray(new Object[0][0]);
    }
}

