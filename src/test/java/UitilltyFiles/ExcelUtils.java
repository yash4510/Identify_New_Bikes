package UitilltyFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	static FileOutputStream fo;
	static FileInputStream fi;
	static XSSFWorkbook wb;
	static XSSFSheet sh;
	static XSSFRow row;
	static XSSFCell cell;
	static String fileEmail = System.getProperty("user.dir")+"//IOfile//emails.xlsx";
	
	
	public static int getRowCount(String xlfile,String xlsheet) throws IOException 
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		sh=wb.getSheet(xlsheet);
		int rowcount=sh.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;		
	}
	
	public static String getCellData(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		sh=wb.getSheet(xlsheet);
		row=sh.getRow(rownum);
		cell=row.getCell(colnum);
	
		String data;
		try 
		{		
			DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell);
            return data;
		}
		catch (Exception e) 
		{
			data="";
		}
		
		wb.close();
		fi.close();
		return data;
	}
	
	
	public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
	{
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(xlsheet);
	    // Check if the row exists, and create one if it doesn't
	    row = sh.getRow(rownum);
	    if (row == null) {
	        row = sh.createRow(rownum);
	    }
	    // Create a cell in the row and set its value
	    cell = row.createCell(colnum);
	    cell.setCellValue(data);
	    // Write the changes back to the file
	    FileOutputStream fo = new FileOutputStream(xlfile);
	    wb.write(fo);
	    // Close all the resources to prevent memory leaks
	    wb.close();
	    fo.close();
	    fi.close();			
	}
	
	
	public static void createExcelSheet() throws IOException {
		
		FileOutputStream fo = new FileOutputStream(System.getProperty("user.dir")+"//IOfile//IOfiles.xlsx");
		
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet emailSheet = wb.createSheet("email");
		XSSFSheet bikeListSheet = wb.createSheet("bikes");
		XSSFSheet car = wb.createSheet("cars");
				
		XSSFRow rowE1 = emailSheet.createRow(0);
		
		XSSFCell emailCell = rowE1.createCell(0);
		XSSFCell expectectCell = rowE1.createCell(1);
		XSSFCell actualCell = rowE1.createCell(2);
		XSSFCell resultCell = rowE1.createCell(3);
		
		XSSFRow rowB1 = bikeListSheet.createRow(0);
		XSSFRow rowC1 = car.createRow(0);
		
		XSSFCell bikeName = rowB1.createCell(0);
		XSSFCell bikePrice = rowB1.createCell(1);
		
		Font font = wb.createFont();//creating
		font.setBold(true);//setting font as Bold
		CellStyle style = wb.createCellStyle();//creating cellStyle
		style.setFont(font);//Applying cell style as Bold
		
		bikeName.setCellValue("Bike Name");
		bikePrice.setCellValue("Bike Price");
		
		XSSFCell carName = rowC1.createCell(0);
		carName.setCellValue("Popular Cars");
		
		emailCell.setCellValue("EMAIL");
		expectectCell.setCellValue("EXPECTED");
		actualCell.setCellValue("ACTUAL");
		resultCell.setCellValue("RESULT");
		
		
		emailCell.setCellStyle(style);
		expectectCell.setCellStyle(style);
		actualCell.setCellStyle(style);
		resultCell.setCellStyle(style);
		bikeName.setCellStyle(style);
		bikePrice.setCellStyle(style);
		carName.setCellStyle(style);
		
		for(int i=1;i<=getRowCount(fileEmail, "sheet1");i++) {
			
			if(i>8) {
				emailSheet.createRow(i).createCell(1).setCellValue("Enter a valid email or phone number");
			}else {
				emailSheet.createRow(i).createCell(1).setCellValue("Couldn’t find your Google Account");
			}
		}
		
		
		
		wb.write(fo);
		wb.close();
		fo.close();	
		
	}
}
