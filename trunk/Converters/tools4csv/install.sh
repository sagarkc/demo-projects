#!/bin/bash

export java_home=/opt/tools/java/jdk1.6.0_35

export PATH=$PATH:$java_home:/opt/tools/maven/maven-3.0.4/bin

mvn clean install

