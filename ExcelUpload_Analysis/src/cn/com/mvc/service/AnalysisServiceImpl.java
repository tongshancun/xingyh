package cn.com.mvc.service;

import java.util.ArrayList;
import cn.com.mvc.util.ReadExcel;

public class AnalysisServiceImpl implements AnalysisService{
	public String getExcelContent(String filename,String filePath){
		String showContent = "";
		//创建读取Excel工具类，读取excel第一个sheet页的前两行内容
		ReadExcel re = new ReadExcel();
		showContent = re.getExcelInfo(filename, filePath);
		return showContent;
	}

}
