#!/bin/bash



# Compile Java code

javac project1.java



# Check if compilation was successful

if [ $? -eq 0 ]; then

    # Run Java program with input file provided as argument

    java project1 "$1"

else

    echo "Compilation failed. Please fix the errors before running the program."

fi


