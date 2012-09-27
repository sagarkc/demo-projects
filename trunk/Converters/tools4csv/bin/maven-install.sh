
echo off

echo - installing - ../tools4csv-xbeans-1.0.jar
mvn install:install-file -Dfile=../tools4csv-xbeans-1.0.jar -DgroupId=net.sf.tools4csv -DartifactId=tools4csv-xbeans -Dversion=1.0 -Dpackaging=jar

echo - installing - ../tools4csv-config-1.0.jar
mvn install:install-file -Dfile=../tools4csv-config-1.0.jar -DgroupId=net.sf.tools4csv -DartifactId=tools4csv-config -Dversion=1.0 -Dpackaging=jar

echo - installing - ../tools4csv-core-1.0.jar
mvn install:install-file -Dfile=../tools4csv-core-1.0.jar -DgroupId=net.sf.tools4csv -DartifactId=tools4csv-core -Dversion=1.0 -Dpackaging=jar

