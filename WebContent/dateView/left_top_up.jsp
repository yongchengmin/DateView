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

	.container{
        display: flex;
        flex-flow:column nowrap;
        width: 1024;  
	    height: 720;
		overflow:hidden; margin:3;
		padding:3;
        background:#ccc;

    }
    .item{
        flex:1 1 0;/*元素弹性*/
        background: green;
    }
    .item img{  
	    width: auto;  
	    height: auto;  
	    max-width: 100%;  
	    max-height: 100%;     
	}
	.boxes{font-size:14px; color:#F00;vertical-align:left;}
</style>
  </head>
  <body>
    <form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
    <textarea rows="3" cols="20" class="boxes" readonly="readonly"><%=errorKey%></textarea>
    &nbsp;&nbsp;
	<input type="file" name="left_top_demo">
    <input type="submit" value="确认上传">
    </form>
    
    <div class="container">
        <div class="item">
        	<img src="http://localhost:8081/dateView/chartJson?parameter=left_top_demo">
        </div>
    </div>
  </body>
</html>