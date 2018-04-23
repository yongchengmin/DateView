<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import= "java.util.Date "%> 
<%
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="0">
<title>新港动态数据监控系统</title>
<!-- <link rel="stylesheet" type="text/css" href="css/styles.css"> -->
</head>
<body>
<div class="blocks">
<div class="block left top">
<div class="content">
<div class="htmleaf-links">
<a class="htmleaf-icon icon-htmleaf-home-outline" href="http://220.178.49.203:8978/jac_parts_wms/mainFrame.html" title="XG_WMS" target="_blank"><span>XG_WMS</span></a>
<a class="htmleaf-icon icon-htmleaf-arrow-forward-outline" href="http://220.178.49.203:8978/jac_parts_wms/mainFrame.html" title="NEXT..." target="_blank"><span> NEXT...</span></a>
</div>
<p><img src="http://localhost:8080/DateView/chartImage" width="1024" height="420"></p> 
</div>
</div>
</div>

</body>
<script language="JavaScript"> 
function myrefresh() 
{ 
window.location.reload(); 
} 
setTimeout('myrefresh()',10000); //指定10秒刷新一次 
</script>
</html>