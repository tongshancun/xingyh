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
	   //判断文件是否为空
	   if(file==null) return null;

	   //上传的excel原始名称
	   String filename=file.getOriginalFilename();

	   //上传excel
	   if(file!=null&&filename!=null&&filename.length()>0){
		  //存储excel物理路径
		   String excel_path="C:\\upload\\";
		   String filePath = excel_path+filename;
		   File newFile=new File(filePath);
		   //将内存中的数据写入磁盘
		   file.transferTo(newFile);
		   
		   //获取excel中第一个sheet页前两行
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