@rem xml schema binding compile
@REM SET JAVA_HOME=C:\liujin\Java\jdk1.7
@SET JAVA_HOME=E:\software\Java\jdk1.8.0_20
%JAVA_HOME%\bin\xjc.exe -p king.flow.test.devops.define -d ..\src\main\java -verbose .\TestDefine.xsd