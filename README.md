SampleProject
=============

Sample Java project

This first project contains an Eclipse project setup demonstrating
some basic Java constructs: including Class, Interface, Object, and
JUnit test cases.

The directory structure is as follows.

$ tree .
.
|-- ext
|   |-- junit-4.11.jar
|   `-- junit-4.11-src.jar
|-- README.md
|-- src
|   `-- sample
|       `-- project
|           |-- Main.java
|           `-- TextProviderInterface.java
`-- test
    `-- sample
        `-- project
            `-- MainTest.java

10 directories, 12 files

Note:
Eclipse will place the Java class files into a `bin` directory. This
directory should not never be checked into source control.

The `ext` directory contains external dependency, junit 4.11 and its 
Java source archive.

Note: 
This is _not_ good form, such dependencies should not be in our public
repository. In the future, we will modify the project to use Maven to
resolve the external dependencies and remove these jar files.

The `src` directory contains a simple example of a command-line Java
application that consumes an Interface providing a String.

The `test` directory contains JUnit test cases providing that the Main.java
implementation meets our expectations.

With this project, you can set Breakpoints in the source code, run the
Test cases in the Debugger, and walk through the code execution of these
simple objects and tests.
