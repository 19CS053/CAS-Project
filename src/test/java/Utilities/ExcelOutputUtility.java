package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelOutputUtility {
	
	//This method for Writing into Excel
	public static void setExcelWrite(String sheetName,int rowValue,int cellValue,String result) throws IOException, InvalidFormatException
	{
		String filepath = System.getProperty("user.dir")+"/Output/Excel/ExcelOutputFile.xlsx";
		FileInputStream fis=new FileInputStream(filepath);
		
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		
		//Creating sheet if doesn't exist
		if(workbook.getSheetIndex(sheetName)==-1) {
			workbook.createSheet(sheetName);
		}
		
		//Creating row
		XSSFSheet sheet = workbook.getSheet(sheetName);
		if(sheet.getRow(rowValue)==null) {
			sheet.createRow(rowValue);
		}
		
		//Creating cell
		XSSFRow row = sheet.getRow(rowValue);
		XSSFCell cell = row.createCell(cellValue);
		
		//Setting the data to the cell
		cell.setCellValue(result);
		
		
		//Writing the data through FileOutputStream
		FileOutputStream fos = new FileOutputStream(filepath);
		workbook.write(fos);
		workbook.close();
	}

}
