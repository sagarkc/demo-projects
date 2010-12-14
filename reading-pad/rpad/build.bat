echo off
cls
set M2_HOME=D:\TOOLS\maven-2.2.1
set JAVA_HOME=D:\TOOLS\Java\jdk1.6.0_21
set Path=%Path%;%M2_HOME%\bin;%JAVA_HOME%
set MAVEN_OPTS=-Xmx1024m -Xms512m

@if "%1" == "c" goto clean
@if "%1" == "e" goto eclipse
@if "%1" == "d" goto desktop
@if "%1" == "m" goto mobility
@if "%1" == "w" goto web
@if "%1" == "f" goto flex
@if "%1" == "fb" goto findbugs
@if "%1" == "s" goto site

@if "%1" == "C" goto clean
@if "%1" == "E" goto eclipse
@if "%1" == "D" goto desktop
@if "%1" == "M" goto mobility
@if "%1" == "W" goto web
@if "%1" == "F" goto flex
@if "%1" == "FB" goto findbugs
@if "%1" == "S" goto site

@echo Building ReadingPad Application.
@echo.
call mvn install
goto end

:desktop
@echo Building Desktop Application.
@echo.
call mvn install -P desktop-app
goto end

:mobility
@echo Building Mobile Application.
@echo.
call mvn install -P mobile-app
goto end

:web
@echo Building Web Application.
@echo.
call mvn install -P web-app
goto end

:flex
@echo Building Flex Application.
@echo.
call mvn install -P only-flex
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