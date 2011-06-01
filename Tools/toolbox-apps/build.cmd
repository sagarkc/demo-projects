echo off
cls
set M2_HOME=C:\TOOLS\maven-2.2.1
rem JDK 1,5 is minimum requirement for MAVEN
set JAVA_HOME=%JAVA_6_HOME%
set Path=%Path%;%M2_HOME%\bin;%JAVA_HOME%
set MAVEN_OPTS=-Xmx1024m -Xms512m

@if "%1" == "c" goto clean
@if "%1" == "e" goto eclipse
@if "%1" == "C" goto clean
@if "%1" == "E" goto eclipse

@echo Building Application.
@echo.
call mvn install
goto end

:clean
	@echo Cleaning 'Sony Opinio-Support' Application.
	@echo.
	call mvn clean
	goto end

:eclipse
	@echo Running 'Sony Opinio-Support' eclipse:eclipse
	@echo.
	call mvn eclipse:eclipse
	goto end



:end
pause
