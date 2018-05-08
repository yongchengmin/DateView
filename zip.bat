cd D:\jac_gitee_v002\DateView\WebContent\dateView\WEB-INF\classes
start "jarCopy" "cmd /k "java -cp .;%MAVEN_REPO%\dom4j\jars\dom4j-1.6.1.jar;%MAVEN_REPO%\itms\jars\ycUtils-1.0.jar;%MAVEN_REPO%\commons-lang\jars\commons-lang-2.6.jar;%MAVEN_REPO%\ant\jars\ant-1.6.2.jar  CopyJarToLib
if ERRORLEVEL 1 goto error
if ERRORLEVEL 0 goto exit
:error
pause
:exit