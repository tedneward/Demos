@ECHO OFF
ECHO Setting JS environment....

if "%1"=="rhino" goto :rhinoSetup
if "%1"=="es4" goto :esSetup
if "%1"=="sm" goto :smSetup
if "%1"=="java" goto :javaSetup
if "%1"=="v8" goto :v8Setup

@ECHO Must choose one of rhino, es4, java, sm, v8...
goto :exit

:rhinoSetup
SET JAVA_HOME=C:\Prg\jdk1.6.0
SET RHINO_HOME=C:\Prg\rhino1_6R7
PATH=%JAVA_HOME%\bin;%PATH%
@ECHO Use java -jar %RHINO_HOME%\js.jar file.js to execute
goto :exit

:esSetup
@ECHO Not implemented yet
goto :exit

:smSetup
SET SM_HOME=C:\Prg\SpiderMonkey\mozilla\js
PATH=%SM_HOME%\src\WINNT5.1_DBG.OBJ;%PATH%
@ECHO Use js file.js to execute
goto :exit

:javaSetup
SET JAVA_HOME=C:\Prg\jdk1.6.0
PATH=%JAVA_HOME%\bin;%PATH%
@ECHO Use jrunscript file.js to execute
goto :exit

:v8Setup
PATH=C:\Prg\v8-svn;%PATH%
@ECHO Use shell file.js to execute
goto :exit

:exit