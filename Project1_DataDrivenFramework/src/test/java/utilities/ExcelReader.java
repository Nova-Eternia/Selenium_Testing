package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelReader {

    private final String path;
    private XSSFWorkbook workbook;

    public ExcelReader(String path) {
        this.path = path;
        try (FileInputStream fis = new FileInputStream(path)) {
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load Excel file: " + path, e);
        }
    }

    // =========================
    // Sheet & Row Utilities
    // =========================

    public int getRowCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return 0;
        return sheet.getPhysicalNumberOfRows();
    }

    public int getColumnCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return 0;
        Row row = sheet.getRow(0);
        if (row == null) return 0;
        return row.getLastCellNum();
    }

    public boolean isSheetExist(String sheetName) {
        return workbook.getSheet(sheetName) != null;
    }

    // =========================
    // Read Cell Data
    // =========================

    public String getCellData(String sheetName, String colName, int rowNum) {

        if (rowNum <= 0) return "";

        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return "";

        Row headerRow = sheet.getRow(0);
        if (headerRow == null) return "";

        int colIndex = -1;
        for (Cell cell : headerRow) {
            if (cell.getStringCellValue().trim().equalsIgnoreCase(colName.trim())) {
                colIndex = cell.getColumnIndex();
                break;
            }
        }

        if (colIndex == -1) return "";

        return getCellData(sheetName, colIndex, rowNum);
    }

    public String getCellData(String sheetName, int colNum, int rowNum) {

        if (rowNum <= 0) return "";

        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return "";

        Row row = sheet.getRow(rowNum - 1);
        if (row == null) return "";

        Cell cell = row.getCell(colNum);
        if (cell == null) return "";

        return formatCellValue(cell);
    }

    private String formatCellValue(Cell cell) {

        CellType type = cell.getCellType();

        if (type == CellType.FORMULA) {
            type = cell.getCachedFormulaResultType();
        }

        switch (type) {

            case STRING:
                return cell.getStringCellValue();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue()
                               .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                }
                return String.valueOf(cell.getNumericCellValue());

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case BLANK:
                return "";

            default:
                return "";
        }
    }

    // =========================
    // Write Cell Data
    // =========================

    public boolean setCellData(String sheetName, String colName, int rowNum, String data) {

        if (rowNum <= 0) return false;

        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return false;

        Row headerRow = sheet.getRow(0);
        if (headerRow == null) headerRow = sheet.createRow(0);

        int colIndex = -1;
        for (Cell cell : headerRow) {
            if (cell.getStringCellValue().equalsIgnoreCase(colName)) {
                colIndex = cell.getColumnIndex();
                break;
            }
        }

        if (colIndex == -1) {
            colIndex = headerRow.getLastCellNum();
            if (colIndex == -1) colIndex = 0;
            Cell cell = headerRow.createCell(colIndex);
            cell.setCellValue(colName);
        }

        Row row = sheet.getRow(rowNum - 1);
        if (row == null) row = sheet.createRow(rowNum - 1);

        Cell cell = row.getCell(colIndex);
        if (cell == null) cell = row.createCell(colIndex);

        cell.setCellValue(data);

        return save();
    }

    // =========================
    // Sheet Operations
    // =========================

    public boolean addSheet(String sheetName) {
        if (isSheetExist(sheetName)) return false;
        workbook.createSheet(sheetName);
        return save();
    }

    public boolean removeSheet(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1) return false;
        workbook.removeSheetAt(index);
        return save();
    }

    // =========================
    // Save & Close
    // =========================

    private boolean save() {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            workbook.write(fos);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void close() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
