echo off
cls
set M2_HOME=C:\TOOLS\maven-3.0.3
set JAVA_HOME=%JAVA_6_HOME%
set Path=%Path%;%M2_HOME%\bin;%JAVA_HOME%
set MAVEN_OPTS=-Xmx1024m -Xms512m

call mvn clean install

pause