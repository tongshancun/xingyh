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
    //������
    private int totalRows = 0;  
    //������
    private int totalCells = 0; 
    //������Ϣ������
    private String errorMsg;
    //���췽��
    public ReadExcel(){}
    //��ȡ������
    public int getTotalRows()  { return totalRows;} 
    //��ȡ������
    public int getTotalCells() {  return totalCells;} 
    //��ȡ������Ϣ
    public String getErrorInfo() { return errorMsg; }  
    
  /**
   * ��֤EXCEL�ļ�
   * @param filePath
   * @return
   */
  public boolean validateExcel(String filePath){
        if (filePath == null || !(WDWUtil.isExcel2003(filePath) || WDWUtil.isExcel2007(filePath))){  
            errorMsg = "�ļ�������excel��ʽ";  
            return false;  
        }  
        return true;
  }
  /**
   * ��ȡexcel�ļ�����ȡ�ͻ���Ϣ����
   * @param filePath
   * @return showContent
   */
  public String getExcelInfo(String filename,String filePath){
	  String showContent = "";
	  //��ʼ��������
	  InputStream is = null;
	  try{
		  //��֤�ļ����Ƿ�ϸ�
		  if(!validateExcel(filename)){
			  return null;
		  }
		  //�����ļ����ж��ļ���2003�汾����2007�汾
		  boolean isExcel2003 = true;
		  if(WDWUtil.isExcel2007(filename)){
              isExcel2003 = false;  
          }
		  //ʵ����������
		  is = new FileInputStream(filePath);
		  //��Excel�ļ�����ȡ��ʼsheet��ǰ��������
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
   * ��Excel�ļ�����ȡ��ʼsheet��ǰ��������
   * @param is ������
   * @param isExcel2003 excel��2003����2007�汾
   * @return showContent
   */
  public String getExcelInfo(InputStream is,boolean isExcel2003){
	  String showContent = "";
	  try{
		  //���ݰ汾ѡ�񴴽�Workbook�ķ�ʽ
		  Workbook wb = null;
		  //��excel��2003ʱ
		  if(isExcel2003){
			  wb = new HSSFWorkbook(is);
		  }else{//��excel��2007ʱ
			  wb = new XSSFWorkbook(is);
		  }
		  showContent = readExcelValue(wb);
	  }catch(IOException e){
		  e.printStackTrace();
	  }
	  return showContent;
  }
  
  /**
   * ��Excel�ļ�����ȡ��ʼsheet��ǰ��������
   * @param Workbook wb
   * @return showContent
   */
  private String readExcelValue(Workbook wb){
	  //�õ���һ��sheet
	  Sheet sheet = wb.getSheetAt(0);
	  //�õ�Excel������
	  this.totalRows=sheet.getPhysicalNumberOfRows();
	  
	  //�õ�Excel������(ǰ����������)
	  if(totalRows>=1 && sheet.getRow(0) != null){
		  this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
	  }
	  //ǰ�����ַ�����
	  String showContent = "";
	  //��ȡExcel��ǰ����
	  for(int r=0; r<2; r++){
		  Row row = sheet.getRow(r);
		  if(row == null) continue;
		  
		  //ѭ��Excel����
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