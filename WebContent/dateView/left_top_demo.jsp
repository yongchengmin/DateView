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
<title>江汽物流动态数据监控系统-DEMO</title>

<style type="text/css">
html,body{height:100%; width:100%; overflow:hidden; margin:0;
padding:0;}
.container{
        display: flex;
        flex-flow:column nowrap;
		width:100%; height:100%; overflow:hidden; margin:0;
		padding:0;
        background:#ccc;

    }
    .parent{
        display: flex;
         flex-flow:row nowrap;
        flex:1 1 0;/*元素弹性*/
        text-align: center;
    }
    .container .parent:first-child{margin-bottom: 10px;}
    .item{
        flex:1 1 0;/*元素弹性*/
        background: green;
    }
    .item img{width:100%;height:100%;}
    .parent .item:first-child{margin-right: 10px;}

</style>

</head>

<body>
 <div class="container" >
    <div class="parent">
        <div class="item">
        	<img src="http://localhost:8081/dateView/chartJson?parameter=left_top_demo">
        </div>
        <div class="item"></div>
    </div>
</div>
</body>
</html>