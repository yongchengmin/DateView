<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String errorKey =(String)(request.getAttribute("errorKey")==null?"":request.getAttribute("errorKey"));
String demoKey =(String)(request.getAttribute("demoKey")==null?"default_demo":request.getAttribute("demoKey"));
String imageUrl = "http://localhost:8081/dateView/chartJson?parameter="+demoKey;
%>
<%
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
%> 
<html>
  <head>
    <base href="<%=basePath%>">
    <title>JSON FILE UP</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<style type="text/css">
*{ list-style:none; margin:0; padding:0;}
html{ height:100%; overflow:hidden;}
body{ background:#BBFFEE; height:100%;}
.top,.left,.right,.bottom{ background:#CCFF99; position:absolute;}
.top{ height:70px; left:5px; top:5px; right:5px;}
.left{ width:300px; left:5px; top:85px; bottom:85px;}
.right{ left:320px; right:5px; top:85px; bottom:85px;}
.bottom{height:70px; left:5px; right:5px; bottom:10px;}

.item{
        flex:1 1 0;
    }
    .item img{  
	    width: auto;  
	    height: auto;  
	    max-width: 100%;  
	    max-height: 100%;     
	}
	
 table {
 	border-spacing: 0;
 	border-collapse: collapse;
 	text-align: center;
 }
 table tbody {
 	display: block;
 	height:300px;
 	overflow-y: scroll;
 }
 table thead,
 tbody tr {
 	display: table;
 	width: 100%;
 	table-layout: fixed;
 }
 tbody tr td ul li {
	list-style:none;
	text-align:left;
 }
 table thead {
 	width: calc( 100% - 1em)
 }
 table thead th {
 	background: #ddd;
 }
 #pre{  
	white-space: pre-wrap;       /* css-3 */  
	white-space: -moz-pre-wrap;  /* Mozilla, since 1999 */  
	white-space: -pre-wrap;      /* Opera 4-6 */  
	white-space: -o-pre-wrap;    /* Opera 7 */  
	word-wrap: break-word;       /* Internet Explorer 5.5+ */  
	word-break:break-all;  
	overflow:scroll;
	overflow-x:hidden;
	color: #F00;  
	font-size: 14px;  
	font-family: "微软雅黑"; 
	resize:none;	
  }
</style> 
  </head>
  <body>
  	<div class="top">
		<form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
			<textarea rows="3" cols="20" class="boxes" readonly="readonly" id="pre"><%=errorKey%></textarea>
		    &nbsp;&nbsp;
			<input type="file" name="left_top_demo">
		    <input type="submit" value="确认上传">
	    </form>
	</div>
	
	<div class="left">
		<table width="100%">
			<thead>
				<tr>
					<th>模 板 列 表</th>
				</tr>
			</thead>    
	        <tbody>
			<tr><td><ul><li>
				<a href="books/left_top_01.json" target="_blank">left_top_模板1 / </a>
				<a href="books/left_top_01.png" target="_blank">预览</a>
			</li></ul></td></tr>
			<tr><td><ul><li>
				<a href="books/left_top_02.json" target="_blank">left_top_模板2 / </a>
				<a href="books/left_top_02.png" target="_blank">预览</a>
			</li></ul></td></tr>
			<tr><td><ul><li>
				<a href="books/left_top_03.json" target="_blank">left_top_模板3 / </a>
				<a href="books/left_top_03.png" target="_blank">预览</a>
			</li></ul></td></tr>
			<tr><td><ul><li>
				<a href="books/left_bottom.json" target="_blank">left_bottom_模板 / </a>
				<a href="books/left_bottom.jpeg" target="_blank">预览</a>
			</li></ul></td></tr>
			<tr><td><ul><li>
				<a href="books/right_top.json" target="_blank">right_top_模板 / </a>
				<a href="books/right_top.jpeg" target="_blank">预览</a>
			</li></ul></td></tr>
			<tr><td><ul><li>
				<a href="books/right_bottom.json" target="_blank">right_bottom_模板 / </a>
				<a href="books/right_bottom.jpeg" target="_blank">预览</a>
			</li></ul></td></tr>
		</tbody>
		  </table>
	</div>
	
	<div class="right">
		<div class="item">
        	<img src=<%=imageUrl%>>
        </div>
	</div>
	
	<div class="bottom">
		<table width="100%">
			<tr>
			<td style="background:#ddd;width:25%;">
				<div style="height:100px;">
					<ul></ul>
				</div>
			</td>
			<td style="background:#ccc;width:25%;">
				<div style="height:100px;">
					<ul></ul>
				</div>
			</td>
			<td style="background:#ddd;width:25%;">
				<div style="height:100px;">
					<ul></ul>
				</div>
			</td>
			<td>
				<div style="background:#ccc;height:100px;">
					<ul></ul>
				</div>
			</td>
		   </tr>
		  </table>
	</div>
  </body>
</html>