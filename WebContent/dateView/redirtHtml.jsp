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
<title><%=new Date()%></title>
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/default.css">
<!-- <link rel="stylesheet" type="text/css" href="css/styles.css"> -->
</head>
<script type=”text/javascript“ src=”css/styles.css?+Math.random()“></script> 
<body>
<div class="blocks">
<input id="left-top" type="checkbox" name="left-top"/>
<label for="left-top" class="trigger left top"></label>
<div class="block left top">
<h1>Left top block heading</h1>
<div class="content">
<h2>Left top block inner text heading</h2>
<div class="htmleaf-links">
<a class="htmleaf-icon icon-htmleaf-home-outline" href="http://220.178.49.203:8978/jac_parts_wms/mainFrame.html" title="XG_WMS" target="_blank"><span>XG_WMS</span></a>
<a class="htmleaf-icon icon-htmleaf-arrow-forward-outline" href="http://220.178.49.203:8978/jac_parts_wms/mainFrame.html" title="NEXT..." target="_blank"><span> NEXT...</span></a>
</div>
<p><img src="01.png" width="700" height="300"></p> 
</div>
</div>
<input id="right-top" type="checkbox" name="right-top"/>
<label for="right-top" class="trigger right top"></label>
<div class="block right top">
<h1>Right top block heading</h1>
<div class="content">
<h2>Right top block inner text heading</h2>
<p><img src="02.png" width="700" height="300"></p>
<p></p>
</div>
</div>
<input id="left-bot" type="checkbox" name="left-bot"/>
<label for="left-bot" class="trigger left bot"></label>
<div class="block left bot">
<h1>Left bottom block heading</h1>
<div class="content">
<h2>Left bottom inner text heading</h2>
<p><img src="03.png" width="700" height="300"></p>
<p></p>
</div>
</div>
<input id="right-bot" type="checkbox" name="right-bot"/>
<label for="right-bot" class="trigger right bot"></label>
<div class="block right bot">
<h1>Right bottom block heading</h1>
<div class="content">
<h2>Right bottom inner text heading</h2>
<p><img src="04.png" width="700" height="300"></p>
<p></p>
</div>
</div>
</div>
</body>
<center> 当前时间是： <%=new Date()%> </center> 
<script language="JavaScript"> 
function myrefresh() 
{ 
window.location.reload(); 
} 
setTimeout('myrefresh()',10000); //指定10秒刷新一次 
</script>
</html>