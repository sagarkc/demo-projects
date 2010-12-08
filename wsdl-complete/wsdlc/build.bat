echo off
cls
set M2_HOME=D:\TOOLS\maven-2.2.1
set JAVA_HOME=D:\TOOLS\Java\jdk1.6.0_22
set Path=%Path%;%M2_HOME%\bin;%JAVA_HOME%
set MAVEN_OPTS=-Xmx1024m -Xms512m

@if "%1" == "clean" goto clean
@if "%1" == "ecl" goto eclipse
@if "%1" == "findbugs" goto findbugs

@echo Building WSDLC Application.
@echo.
call mvn install
goto end

:clean
@echo Cleaning Application.
@echo.
call mvn clean
goto end

:eclipse
@echo Running eclipse:eclipse Application.
@echo.
call mvn eclipse:eclipse
goto end

:findbugs
@echo Running findbugs on java code
@echo.
call mvn compile findbugs:check
goto end

:site
@echo Running sites
@echo.
call mvn site:site
goto end

:end
pause