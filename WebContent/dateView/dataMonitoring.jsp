<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String leftTopUrl = basePath+"chartJson?parameter=left_top";
String rightTopUrl = basePath+"chartJson?parameter=right_top";
String leftBottomUrl = basePath+"chartJson?parameter=left_bottom";
String rightBottomUrl = basePath+"chartJson?parameter=right_bottom";
%>
<%
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);
%>
<html>

<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">

<meta http-equiv="refresh" content="20">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<META HTTP-EQUIV="pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<META HTTP-EQUIV="expires" CONTENT="0">

<title>新港数据监控系统C-4</title>
    <style type="text/css">
        *{
            margin: 1px auto;
            padding: 0;
        }
        .main{
            width: 100%;
            height: 100%;
            position: absolute;
        }
        .quarter-div{
            width: 50%;
            height: 50%;
            float: left;
        }
        .blue{
            background-color: #5BC0DE;
        }
        .green{
            background-color: #5CB85C;
        }
        .orange{
            background-color: #F0AD4E;
        }
        .yellow{
            background-color: #FFC706;
        }
		.main img{width:100%;height:100%;}
		.divname{
		    color: #0066CC;text-align:center;
		}
    </style>
</head>
<body>
    <div class="main">
        <div class="quarter-div blue">
			<img src=<%=leftTopUrl%>>
		</div>
        <div class="quarter-div green divname">
        	<img src=<%=rightTopUrl%>>
        </div>
        <div class="quarter-div orange divname">
        	<img src=<%=leftBottomUrl%>>
        </div>
        <div class="quarter-div yellow divname">
        	<img src=<%=rightBottomUrl%>>
        </div>
    </div>
</body>
</html>