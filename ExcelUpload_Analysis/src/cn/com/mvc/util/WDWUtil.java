package cn.com.mvc.util;

public class WDWUtil {

    // @�������Ƿ���2003��excel������true��2003 
    public static boolean isExcel2003(String filePath)  {  
         return filePath.matches("^.+\\.(?i)(xls)$");  
     }  
   
    //@�������Ƿ���2007��excel������true��2007 
    public static boolean isExcel2007(String filePath)  {  
         return filePath.matches("^.+\\.(?i)(xlsx)$");  
     }  
 
}
