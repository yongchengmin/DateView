发布前一定要仔细阅读我哦

发布前:
1-将配置文件globalMesg.properties放到部署服务器的指定目录下,windows系统放在C:\Users下
2-将配置文件globalMesg.properties内容修改为系统发布的路径,比如发布到D://tomcat//webapps路径下,则修改与之对应为D://tomcat//webapps//DateView
3-view.html:<img src="http://localhost:8080/...">端口号改为实际发布内容 

开发时:
1-sizetwo路径为  项目所在路径/DateView/WebContent下面,比如D://work//DateView//WebContent
1-sizeone路径为  项目所在路径/DateView/WebContent/css下面,比如D://work//DateView//WebContent//css
2-相应的调整globalMesg.properties内容

其他说明:
1-登陆
---10s刷新一次
http://localhost:8080/DateView