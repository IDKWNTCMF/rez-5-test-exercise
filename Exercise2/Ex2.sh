#!/bin/bash

if [ -n "$1" ]
then
javap -c -p -v $1 > tmp.txt
kotlinc Main.kt -include-runtime -d main.jar
java -jar main.jar
else
echo "No parameters found. "
fi
