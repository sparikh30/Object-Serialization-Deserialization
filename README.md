# CS542: Assignment 5
## Name: Samarth Parikh

-----------------------------------------------------------------------
-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.

Assuming you are on the in the directory where /src is

#### Note: build.xml is present in genericCheckpointing/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile src/build.xml run -Darg0={modeType} -Darg1={N} -Darg2=checkpoint.txt

-----------------------------------------------------------------------
## Description:

The program will take three arguments : 

1. Mode : Mode can be deser or serdeser.
In deser Mode, program will read the file checkpoint.txt and store it into the object. Console will display the output.
In serdeser Mode, program will generate the random for each of the data member and serialize and deserialize it.

2. N. which is number of objects to be deserialize in deser mode and number of unique objects in mode serdeser.

3. checkpoint.txt file path.

Design Patterns used:

Dynamic Proxy pattern : Driver code will create the proxy and invoke the method on that proxy.

Strategy Pattern : Strategy to serialize and deserialize the XML file.

Visitor Pattern : Visitor for finding prime integers and palindrome strings.

Use Of Reflections:

calling getters and setters using reflections.
-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 12/08/18
