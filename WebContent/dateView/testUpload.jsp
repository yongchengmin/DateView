<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
  </head>
  <body>
    <form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
	<input type="file" name="myfile">
    <input type="text" name="mytext" value="123456"/>
    <input type="text" name="mytext" value="123456"/>
    <input type="submit" value="确认上传">
    </form>
  </body>
</html>