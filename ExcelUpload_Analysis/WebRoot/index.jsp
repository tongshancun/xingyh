<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>  
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>首页</title>
  </head>
  
  <body>
    <form action="<%=path%>/toAddExcelPage" method="post">
     <div style="margin: 30x;">
     <!-- <a href＝"#" onclick="skip()"> 上传excel显示前两行</a> -->
     <input id="excel_button" type="submit" value="上传excel显示前两行"/></div>
     </div>
    </form>
    
  </body>
</html>
