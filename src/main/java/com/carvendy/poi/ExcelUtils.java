package com.carvendy.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;


public class ExcelUtils {
	public static void main(String[] args) throws IOException {
		String path="d:/Book1.xls";
		String[] sourceArr = read(path);
		
		int positionStart=0;
		int positionEnd=0;

		for(int i=0;i<sourceArr.length;i++){
			String str = sourceArr[i];
			if(str.indexOf("：")!=-1){
				positionStart=i;//起始，坐标
			}else{
				continue;
			}
			
			for(int j=positionStart+1;j<sourceArr.length-1;j++){
				//System.out.println(j);
				String str2 = sourceArr[j];
				if(str2!=null&&str2.indexOf("：")!=-1){
					positionEnd=j-1>0?j-1:0;//结束坐标
					
					if(sourceArr[positionEnd].indexOf("：")!=-1){
						break;
					}
					
					change(sourceArr, positionStart, positionEnd);
					break;
				}
			}
			
		}
		
		change(sourceArr, positionEnd+1, sourceArr.length-1);
		for (String string : sourceArr) {
			System.out.println(string);
		}
		
		//写入
		//write(path);
	}
	
	public static void write(String path) {
		OutputStream out=null;

		try {
			out=new FileOutputStream(path);
			Workbook work=new HSSFWorkbook();
			
			HSSFSheet sheet = null;//work.SheetAt(0);
			System.out.println(sheet);
			int rowNum=6;
			
			for(int i=0;i<rowNum;i++){
				HSSFRow row = sheet.getRow(i);
				HSSFCell cell = row.createCell(1);
				//System.out.println(cell.getStringCellValue());
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					out=null;
				}
			}
		}
	}


	public static String[] read(String path){
		return read(path , 0 );
	}
	
	public static String[] read(String path , int sheetAt ){
		//读取写入
			InputStream in=null;
			POIFSFileSystem fs =null;
			List<String> sourceList=new ArrayList<String>();
			
			try {
				in=new FileInputStream(path);
				
				fs = new POIFSFileSystem(in);
				HSSFWorkbook work=new HSSFWorkbook(fs);
				
				work.getNumberOfSheets();
				HSSFSheet sheet = work.getSheetAt(sheetAt);
				int rowNum = 2000;
				for(int i= 1;i <rowNum;i++){
					HSSFRow row = sheet.getRow(i);
					if(row == null){
						continue;
					}
					HSSFCell cell = row.getCell(0);
					if(null == cell){
						continue;
					}
					String str=cell.getStringCellValue();
					//System.out.println(str);
					if("".equals(str)){
						break;
					}
					sourceList.add(str);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(in!=null){
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}finally{
						in=null;
					}
				}
		}
		
		final int size =sourceList.size();
		String[] arr = (String[])sourceList.toArray(new String[size]);
		return arr;
	}

	
	public static void change(String[] arr,int start,int end){
		String temp = arr[start];
		for(int i=start;i<end;i++){
			arr[i]=arr[i+1];
		}
		arr[end]=temp;
	}
}
