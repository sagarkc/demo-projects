echo off
cls
set M2_HOME=C:\TOOLS\maven-2.2.1
set JAVA_HOME=%JAVA_6_HOME%
set Path=%Path%;%M2_HOME%\bin;%JAVA_HOME%
set MAVEN_OPTS=-Xmx1024m -Xms512m

@echo Building LightReader Application.
@echo.
call mvn clean install
goto end


:end
pause