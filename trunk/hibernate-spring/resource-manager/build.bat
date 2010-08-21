echo off

set M2_HOME=%MAVEN_HOME%
rem set JAVA_HOME=%JAVA_6_HOME%
set FLEX_HOME=D:\TOOLS\flex_builder\sdks\3.1.0
set Path=%Path%;%M2_HOME%\bin;%JAVA_HOME%

call mvn clean install

pause