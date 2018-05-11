<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
%> 
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="0">
<title>动态数据监控系统</title>
<style type="text/css">
*{margin:0; padding:0; font-size:14px;}
a{color:#333;text-decoration:none}
.nav{list-style:none; height:30px; border-bottom:10px solid #F60; margin-top:20px; padding-left:50px;}
.nav li{float:left}
.nav li a{display:block; height:30px;text-align:center; line-height:30px; width:80px; background:#efefef; margin-left:1px;}
.nav li a.on, .nav li a:hover{background:#F60;color:#fff; }
</style>
</head>
<body>
<ul class="nav">
    <li><a href="#">首　　页</a></li>
    <li><a href="#">关于我们</a></li>
    <li><a href="#">产品展示</a></li>
    <li><a href="#">售后服务</a></li>
    <li><a href="#">联系我们</a></li>
  </ul>
</body>
</html>