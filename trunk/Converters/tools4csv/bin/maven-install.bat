
echo off
rem set JAVA_HOME=%JAVA_6_HOME%
rem set Path=%Path%;path/to/maven/bin

echo - installing - ../tools4csv-xbeans-1.0.jar
call mvn install:install-file -Dfile=..\tools4csv-xbeans-1.0.jar -DgroupId=net.sf.tools4csv -DartifactId=tools4csv-xbeans -Dversion=1.0 -Dpackaging=jar -Dsources=..\src\tools4csv-xbeans-1.0-sources.jar

echo - installing - ../tools4csv-config-1.0.jar
call mvn install:install-file -Dfile=..\tools4csv-config-1.0.jar -DgroupId=net.sf.tools4csv -DartifactId=tools4csv-config -Dversion=1.0 -Dpackaging=jar -Dsources=..\src\tools4csv-config-1.0-sources.jar

echo - installing - ../tools4csv-core-1.0.jar
call mvn install:install-file -Dfile=..\tools4csv-core-1.0.jar -DgroupId=net.sf.tools4csv -DartifactId=tools4csv-core -Dversion=1.0 -Dpackaging=jar -Dsources=..\src\tools4csv-core-1.0-sources.jar

