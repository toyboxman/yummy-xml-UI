@rem xml schema binding compile
@REM SET JAVA_HOME=C:\liujin\Java\jdk1.7
@SET JAVA_HOME=G:\king\Java\jdk1.8.0_20
@rem  %JAVA_HOME%\bin\xjc.exe -p king.flow.view -d ..\src\main\java -verbose .\WindowXmlSchema.xsd
@rem %JAVA_HOME%\bin\xjc.exe -p king.flow.net -d ..\src\main\java -verbose .\TransportationSchema.xsd
%JAVA_HOME%\bin\xjc.exe -p king.flow.data -d ..\src\main\java -verbose .\CommandSchema.xsd