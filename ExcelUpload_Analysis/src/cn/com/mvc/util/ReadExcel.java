package cn.com.mvc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ReadExcel {
    //总行数
    private int totalRows = 0;  
    //总条数
    private int totalCells = 0; 
    //错误信息接收器
    private String errorMsg;
    //构造方法
    public ReadExcel(){}
    //获取总行数
    public int getTotalRows()  { return totalRows;} 
    //获取总列数
    public int getTotalCells() {  return totalCells;} 
    //获取错误信息
    public String getErrorInfo() { return errorMsg; }  
    
  /**
   * 验证EXCEL文件
   * @param filePath
   * @return
   */
  public boolean validateExcel(String filePath){
        if (filePath == null || !(WDWUtil.isExcel2003(filePath) || WDWUtil.isExcel2007(filePath))){  
            errorMsg = "文件名不是excel格式";  
            return false;  
        }  
        return true;
  }
  /**
   * 读取excel文件，获取客户信息集合
   * @param filePath
   * @return showContent
   */
  public String getExcelInfo(String filename,String filePath){
	  String showContent = "";
	  //初始化输入流
	  InputStream is = null;
	  try{
		  //验证文件名是否合格
		  if(!validateExcel(filename)){
			  return null;
		  }
		  //根据文件名判断文件是2003版本还是2007版本
		  boolean isExcel2003 = true;
		  if(WDWUtil.isExcel2007(filename)){
              isExcel2003 = false;  
          }
		  //实例化输入流
		  is = new FileInputStream(filePath);
		  //读Excel文件，获取开始sheet的前两行数据
		  showContent = getExcelInfo(is, isExcel2003);
		  is.close();
	  }catch(Exception e){
		  e.printStackTrace();
      } finally{
          if(is !=null)
          {
              try{
                  is.close();
              }catch(IOException e){
                  is = null;    
                  e.printStackTrace();  
              }
          }
      }
	  return showContent;
  }
  
  /**
   * 读Excel文件，获取开始sheet的前两行数据
   * @param is 输入流
   * @param isExcel2003 excel是2003还是2007版本
   * @return showContent
   */
  public String getExcelInfo(InputStream is,boolean isExcel2003){
	  String showContent = "";
	  try{
		  //根据版本选择创建Workbook的方式
		  Workbook wb = null;
		  //当excel是2003时
		  if(isExcel2003){
			  wb = new HSSFWorkbook(is);
		  }else{//当excel是2007时
			  wb = new XSSFWorkbook(is);
		  }
		  showContent = readExcelValue(wb);
	  }catch(IOException e){
		  e.printStackTrace();
	  }
	  return showContent;
  }
  
  /**
   * 读Excel文件，获取开始sheet的前两行数据
   * @param Workbook wb
   * @return showContent
   */
  private String readExcelValue(Workbook wb){
	  //得到第一个sheet
	  Sheet sheet = wb.getSheetAt(0);
	  //得到Excel的行数
	  this.totalRows=sheet.getPhysicalNumberOfRows();
	  
	  //得到Excel的列数(前提是有列数)
	  if(totalRows>=1 && sheet.getRow(0) != null){
		  this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
	  }
	  //前两行字符定义
	  String showContent = "";
	  //读取Excel的前两行
	  for(int r=0; r<2; r++){
		  Row row = sheet.getRow(r);
		  if(row == null) continue;
		  
		  //循环Excel的列
		  for(int c=0; c<this.totalCells; c++){
			  Cell cell = row.getCell(c);
			  if(null != cell){
				  showContent += cell.getStringCellValue() + " ";
			  }
		  }
		  showContent += "<br>";
		  
	  }
	  return showContent;
	  
  }
  
	public static void main(String[] args) throws Throwable {  
		ReadExcel re = new ReadExcel();
		String showContent = re.getExcelInfo("item.xls", "C:\\upload\\item.xls");
		System.out.println(showContent);
	} 
}