dataMonitoring.html--地址和端口替换为实际发布服务器
更新频频默认20s

先登录以下地址验证 IP和端口以实际发布为准
http://192.168.10.92:8089/dateView/chartJson?parameter=left_top

left_top_up.jsp--地址和端口替换为实际发布服务器
http://192.168.10.92:8089/dateView/chartJson?parameter=left_top_demo

注意事项:修改下面文件之前,建议先启动一次,让项目先加载sql的路径
防止job提示找不到文件
WEB-INF\web.xml 定时任务注释

