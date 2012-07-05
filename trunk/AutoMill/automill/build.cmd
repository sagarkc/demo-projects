echo off
cls
set M2_HOME=C:\TOOLS\maven-3.0.3
set JAVA_HOME=%JAVA_6_HOME%
set Path=%Path%;%M2_HOME%\bin;%JAVA_HOME%
set MAVEN_OPTS=-Xmx1024m -Xms512m


@echo Building ::- AutoMill Application.
@echo Argument: %1
call mvn clean install %1
goto end



:end
pause

rem 8496030993