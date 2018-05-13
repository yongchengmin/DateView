<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String errorKey =(String)(request.getAttribute( "errorKey")==null?"":request.getAttribute( "errorKey"));
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
        flex:1 1 0;/*元素弹性*/
    }
    .item img{  
	    width: auto;  
	    height: auto;  
	    max-width: 100%;  
	    max-height: 100%;     
	}
	
 table {
 	/*设置相邻单元格的边框间的距离*/
 	border-spacing: 0;
 	/*表格设置合并边框模型*/
 	border-collapse: collapse;
 	text-align: center;
 }
 /*关键设置 tbody出现滚动条*/
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
 /*关键设置：滚动条默认宽度是16px 将thead的宽度减16px*/
 table thead {
 	width: calc( 100% - 1em)
 }
 table thead th {
 	background: #ddd;
 }
</style> 
  </head>
  <body>
  	<div class="top">
		<form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
			<textarea rows="3" cols="20" class="boxes" readonly="readonly"></textarea>
		    &nbsp;&nbsp;
			<input type="file" name="left_top_demo">
		    <input type="submit" value="确认上传">
	    </form>
	</div>
	
	<div class="left">
		<table width="100%">
			<thead>
				<tr>
					<th>代 码 列 表</th>
				</tr>
			</thead>    
	        <tbody>
				<tr><td>代码01</td></tr>
				<tr><td>代码02</td></tr>
				<tr><td>代码03</td></tr>
				<tr><td>代码04</td></tr>
				<tr><td>代码05</td></tr>
				<tr><td>代码06</td></tr>
				<tr><td>代码07</td></tr>
				<tr><td>代码08</td></tr>
				<tr><td>代码09</td></tr>
				<tr><td>代码10</td></tr>
				<tr><td>代码11</td></tr>
				<tr><td>代码12</td></tr>
				<tr><td>代码13</td></tr>
				<tr><td>代码14</td></tr>
				<tr><td>代码15</td></tr>
				<tr><td>代码16</td></tr>
				<tr><td>代码17</td></tr>
				<tr><td>代码18</td></tr>
				<tr><td>代码19</td></tr>
				<tr><td>代码20</td></tr>
				<tr><td>代码21</td></tr>
				<tr><td>代码22</td></tr>
				<tr><td>代码23</td></tr>
				<tr><td>代码24</td></tr>
				<tr><td>代码25</td></tr>
				<tr><td>代码26</td></tr>
				<tr><td>代码27</td></tr>
				<tr><td>代码28</td></tr>
				<tr><td>代码29</td></tr>
				<tr><td>代码30</td></tr>
			</tbody>
		  </table>
	</div>
	
	<div class="right">
		<div class="item">
        	<img src="http://localhost:8081/dateView/chartJson?parameter=left_top_demo">
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