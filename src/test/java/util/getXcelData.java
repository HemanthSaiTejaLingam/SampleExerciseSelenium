package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class getXcelData {
	public static Object[][] getData(String path,String sheetName){
		File f=new File(path);
		FileInputStream fin;
		Object[][] obj=null;
		try {
			fin = new FileInputStream(f);
			XSSFWorkbook wb=new XSSFWorkbook(fin);
			XSSFSheet s=wb.getSheet(sheetName);
			int rows=s.getPhysicalNumberOfRows();
			int cols=s.getRow(0).getLastCellNum();
			obj=new Object[rows-1][cols];
			for(int i=1;i<rows;i++) {
				XSSFRow row=s.getRow(i);
				for(int j=0;j<cols;j++)
				{
					XSSFCell cell=row.getCell(j);
					//System.out.println(cell.getCellType());
					//System.out.println(j);
					if(j==3) {
						int x=(int) cell.getNumericCellValue();
						obj[i-1][j]=Integer.toString(x);
					}
					else
					obj[i-1][j]=cell.getStringCellValue();
				}
			}
			wb.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return obj;
		
	}
}
