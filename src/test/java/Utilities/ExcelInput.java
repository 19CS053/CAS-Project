package Utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelInput {
	
	//This method for reading excel file 
	public static String[] getExcelData() throws IOException
	{
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\TestData\\TimesheetExcelInput.xlsx");
		
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		
		XSSFSheet sheet=workbook.getSheetAt(0);
		
		XSSFRow row=sheet.getRow(0);
		
		XSSFCell cell;
		
		String[] inputs=new String[7];
		for(int i=0;i<7;i++)
		{
			cell=row.getCell(i);
			inputs[i]=cell.toString();
		}
		return inputs;
	}

}
