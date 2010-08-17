@echo off
cls
set M2=D:\TOOLS\maven-2.0.8
set JAVA_HOME=%JAVA_5_HOME%
set FLEX_HOME=D:\TOOLS\flex_builder\sdks\3.1.0
set Path=%JAVA_HOME%\bin;%M2%\bin;%Path%

@if "%1" == "c" goto clean
@if "%1" == "i" goto install
@if "%1" == "ci" goto ci
@if "%1" == "ic" goto ci

:clean
@echo Cleaning Application.
@echo.
call mvn clean
goto end

:install
@echo Installing Application.
@echo.
call mvn install %2
goto end

:ci
@echo Cleaning and Installing Application.
@echo.
call mvn clean install %2
goto end

:end
pause