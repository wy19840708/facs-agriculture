#!/usr/bin/env bash
java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:../logs/gcfile.txt -classpath ".:../lib/*:../conf/*" Application 2>&1 &