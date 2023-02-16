package discoveryTeam.excelAutomation;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WritingToExcelAssigment {
    @Test
    public void writingToExcelFile() throws IOException {
        String path = "src/test/resources/Book1.xlsx"; //Java can open the Excel files but can not read. File do not has to be in the resource folder, it can read from directly computer as well.

        FileInputStream fileInputStream = new FileInputStream(path);//For opening

        //Read the Excel file
        Workbook excel = WorkbookFactory.create(fileInputStream);

        FileOutputStream fileOutputStream = new FileOutputStream(path);//For reading after saving. Order is important. Firstly open, then read, lastly show result

        //Select sheet that will be written
        Sheet sheet = excel.getSheetAt(0);   //First sheet's index is 0.

        //Select cell (column) and row that will be written (Headers)
        sheet.getRow(1).getCell(1).setCellValue("Name");
        sheet.getRow(0).getCell(2).setCellValue("Surname");
        sheet.getRow(0).getCell(3).setCellValue("Batch Number");
        sheet.getRow(0).getCell(4).setCellValue("Group");

        //Writing row data
        sheet.getRow(1).getCell(1).setCellValue("Hasan");
        sheet.getRow(2).getCell(1).setCellValue("Can");
        sheet.getRow(3).getCell(1).setCellValue("Tarık");
        sheet.getRow(4).getCell(1).setCellValue("Tan");


        excel.write(fileOutputStream);
        excel.close();
        fileInputStream.close();

    }
}
