package cn.com.mvc.service;

import java.util.ArrayList;
import cn.com.mvc.util.ReadExcel;

public class AnalysisServiceImpl implements AnalysisService{
	public String getExcelContent(String filename,String filePath){
		String showContent = "";
		//������ȡExcel�����࣬��ȡexcel��һ��sheetҳ��ǰ��������
		ReadExcel re = new ReadExcel();
		showContent = re.getExcelInfo(filename, filePath);
		return showContent;
	}

}
