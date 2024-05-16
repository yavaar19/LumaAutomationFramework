package DataReader;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class DataReader {

    XSSFWorkbook workbook;

    public DataReader(String filePath) throws IOException, InvalidFormatException {

        File src = new File(filePath);
        workbook = new XSSFWorkbook(src);

    }

    public String[][] getAllData(String sheetName) {

        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        String[][] data = new String[rowCount - 1][colCount];

        for (int i = 1; i < rowCount; i++) {

            XSSFRow row = sheet.getRow(i);

            Iterator<Cell> cellIterator = row.cellIterator();

            for (int j = 0; j < colCount; j++) {

                data[i - 1][j] = cellIterator.next().getStringCellValue();

            }

        }

        return data;

    }

}
