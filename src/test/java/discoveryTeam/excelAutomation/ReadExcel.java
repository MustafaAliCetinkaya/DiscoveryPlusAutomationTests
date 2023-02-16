package discoveryTeam.excelAutomation;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcel {

    @Test
    public void readExcelFiles() throws IOException {
        String path="/Users/mustafacetinkaya/IdeaProjects/BasicNavigationTests/src/test/resources/EU10-Group-7_Tracking Sheet_SAMPLE.xlsx"; //Java can open the Excel files but can not read. File do not has to be in the resource folder, it can read from directly computer as well.

        FileInputStream file=new FileInputStream(path);

        //Read the Excel file
        Workbook excel= WorkbookFactory.create(file);

        //Select sheet that will be read
        Sheet sheet= excel.getSheetAt(0);   //First sheet's index is 0.

        //Select cell (column) and row that will be read
        Row row= sheet.getRow(16);   //First row's index is 0.
        Cell points= row.getCell(4);   //First cell's index is 0.
        Cell name= row.getCell(1);   //First cell's index is 0.

        //Read the intersection of the cell and row
        System.out.println(points.toString());
        System.out.println(name.toString()+"\n*************************");

        for (int i = 3; i <21 ; i++) {
            Row nameRow= sheet.getRow(i);
            Cell allNames= nameRow.getCell(1);
            System.out.println(allNames.toString());
        }

        for (int i = 2; i <23 ; i++) {
            Row myName= sheet.getRow(16);
            Cell myPoints= myName.getCell(i);
            System.out.print(myPoints.toString()+" / ");
        }

        System.out.println("***********************");
        //Last row index
        int lastRow=sheet.getLastRowNum();
        System.out.println(lastRow);

        //Row number which has data
        int rowNumber=sheet.getPhysicalNumberOfRows();
        System.out.println(rowNumber);

        for (int i = 0; i <lastRow-1 ; i++) {
            Cell allListedNames=sheet.getRow(i).getCell(1);
            System.out.println(allListedNames);
        }

        //ForEach loop and all data in the same column
        for (Cell data : sheet.getRow(3)){
            System.out.print(data+" / ");
        }

        System.out.println();

        //ForEach loop and all data in the same column
        for (Cell data : sheet.getRow(4)){
            System.out.print(data+" / ");
        }

        file.close();
        excel.close();
    }
}
