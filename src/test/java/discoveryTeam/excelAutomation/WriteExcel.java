package discoveryTeam.excelAutomation;

import discoveryTeam.utilities.ReusableMethods;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteExcel {
/*Sheet == Page
* Row == horizontal line
* Cell == */
    @Test
    public void writeExcelFiles() throws IOException {
        String path = "/Users/mustafacetinkaya/IdeaProjects/BasicNavigationTests/src/test/resources/EU10-Group-7_Tracking Sheet_SAMPLE.xlsx"; //Java can open the Excel files but can not read. File do not has to be in the resource folder, it can read from directly computer as well.

        FileInputStream file = new FileInputStream(path);//For opening

        //Read the Excel file
        Workbook excel = WorkbookFactory.create(file);

        FileOutputStream fileOutputStream = new FileOutputStream(path);//For reading after saving. Order is important. Firstly open, then read, lastly show result


        //Select sheet that will be written
        Sheet sheet = excel.getSheetAt(0);   //First sheet's index is 0.

        //Select cell (column) and row that will be written
        sheet.getRow(0).getCell(1).setCellValue("CYDEOTEAM");   //First row's index is 0.
        excel.write(fileOutputStream);

        //Last row index
        int lastRow=sheet.getLastRowNum();

        for (int i = 0; i < 21; i++) {
            sheet.getRow(lastRow-5).getCell(i).setCellValue(i+"");   //First row's index is 0.
            excel.write(fileOutputStream);
        }

        //Creating a new cell and writing data in it.
        sheet.getRow(1).createCell(23).setCellValue("New Assignment");

        file.close();
        excel.close();
    }
}
