@rem java native method compile
@SET JAVA_HOME=E:\software\Java\jdk1.8.0_40
@rem %JAVA_HOME%\bin\javah.exe -d ..\src\main\cpp -verbose -classpath ..\target\classes king.flow.control.driver.ICCardConductor
@rem %JAVA_HOME%\bin\javah.exe -d ..\src\main\cpp -verbose -classpath ..\target\classes king.flow.control.driver.KeyBoardDriver
@rem %JAVA_HOME%\bin\javah.exe -d ..\src\main\cpp -verbose -classpath ..\target\classes king.flow.control.driver.MagnetCardConductor
%JAVA_HOME%\bin\javah.exe -d ..\src\main\cpp -verbose -classpath ..\target\classes king.flow.control.driver.TwoInOneCardConductor
@rem %JAVA_HOME%\bin\javah.exe -d ..\src\main\cpp -verbose -classpath ..\target\classes king.flow.control.driver.PrinterConductor