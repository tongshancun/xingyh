<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
  String importMsg="";
  if(request.getSession().getAttribute("msg")!=null){
     importMsg=request.getSession().getAttribute("msg").toString();
  }
  request.getSession().setAttribute("msg", "");
 %>
  <head>
    <meta charset="utf-8">
    <title>上传excel文件</title>
  </head>
  <body>
    <form action="uploadExcel.action" method="post" enctype="multipart/form-data" onsubmit="return check();">
     <div style="margin: 30x;"><input id="excel_file" type="file" name="filename" accept=".xls,.xlsx" onchange="analysis(this)"/>
     <input id="excel_button" type="submit" value="上传excel"/></div>
     <div id="show">${showcontent }</div>
  </body>
  <script type="text/javascript">

     function check(){
        var excel_file = $("#excel_file").val();
        if(excel_file == "" || excel_file.length == 0){
           alert("请选择文件路径!");
           return false;
        }else{
           return true;
        }
     }
     
  </script>
  </html>  