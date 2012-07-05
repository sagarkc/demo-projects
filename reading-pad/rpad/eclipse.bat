echo off

set M2_HOME=C:\TOOLS\maven-3.0.3
set JAVA_HOME=C:\TOOLS\Java\jdk1.6.0_22
set Path=%Path%;%M2_HOME%\bin;%JAVA_HOME%

call mvn eclipse:eclipse

pause