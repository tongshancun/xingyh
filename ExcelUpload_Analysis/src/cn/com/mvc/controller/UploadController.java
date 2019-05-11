package cn.com.mvc.controller;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import cn.com.mvc.service.*;

@Controller
public class UploadController {
   @RequestMapping("/toAddExcelPage")
   public String toAddExcelPage()throws Exception{
	   return "/addExcel";
   }
   
   @RequestMapping("/uploadExcel")
   public ModelAndView uploadExcel(@RequestParam(value="filename")MultipartFile file,
		   HttpServletRequest request, HttpServletResponse response)throws Exception{	   
	   ModelAndView mv = new ModelAndView();  
	   String showContent = "";
	   try{
	   //�ж��ļ��Ƿ�Ϊ��
	   if(file==null) return null;

	   //�ϴ���excelԭʼ����
	   String filename=file.getOriginalFilename();

	   //�ϴ�excel
	   if(file!=null&&filename!=null&&filename.length()>0){
		  //�洢excel����·��
		   String excel_path="C:\\upload\\";
		   String filePath = excel_path+filename;
		   File newFile=new File(filePath);
		   //���ڴ��е�����д�����
		   file.transferTo(newFile);
		   
		   //��ȡexcel�е�һ��sheetҳǰ����
		   AnalysisService util = new AnalysisServiceImpl();	   
		   showContent = util.getExcelContent(filename,filePath);
	   }
	   }catch(Exception e){
		   e.printStackTrace();
	   }

	   mv.addObject("showcontent", showContent);
	   mv.setViewName("/addExcel");
	   return mv;
   }
}