<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>验证码</title>
<script type="text/javascript">
function getVrifyCode(){

//employeeId 作为js的参数传递进来

window.location.href = 'verify';

} 
</script>
</head>
<body >
 

<input type="button"  onclick="getVrifyCode();" value="验证码" style="width: 20;height: 20"/>
</body>
</html>