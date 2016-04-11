@rem java native method compile
@SET JAVA_HOME=G:\king\Java\jdk1.8.0_20
@rem %JAVA_HOME%\bin\javah.exe -d ..\src\main\cpp -verbose -classpath ..\target\classes king.flow.control.driver.ICCardConductor
@rem %JAVA_HOME%\bin\javah.exe -d ..\src\main\cpp -verbose -classpath ..\target\classes king.flow.control.driver.KeyBoardDriver
@rem %JAVA_HOME%\bin\javah.exe -d ..\src\main\cpp -verbose -classpath ..\target\classes king.flow.control.driver.MagnetCardConductor
@rem %JAVA_HOME%\bin\javah.exe -d ..\src\main\cpp -verbose -classpath ..\target\classes king.flow.control.driver.TwoInOneCardConductor
%JAVA_HOME%\bin\javah.exe -d ..\src\main\cpp -verbose -classpath ..\target\classes king.flow.control.driver.PKG8583
@rem %JAVA_HOME%\bin\javah.exe -d ..\src\main\cpp -verbose -classpath ..\target\classes king.flow.control.driver.PrinterConductor