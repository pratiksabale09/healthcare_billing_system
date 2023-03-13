## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies
- `SQL`: the folder to maintain SQL queries
- `bills`: the folder to maintain all generated bills

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Git commands

create branch : git branch branchname
switch branch : git checkout branchname
push changes : 1. git add .
                2. git commit -m "changes message"
                3. git push origin branchname
                4. git pull
                5. git merge
                6. git branch
                7. git checkout

## Project Details

The final bill consists of following charges:
1. Equipment Charges
2. Room Charges
3. Treatment Charges
4. Medical Test Charges
5. Medicines Charges

These amounts are generated from respective usage tables, where each of these amounts are mapped with patient ID's.

## Application flow

1. App.java
2. AccountSectionFunctionality -> Depending on user selection, respective .java file will run.
   This folder consists mainly IO related operations, where each function internally calls seperate functions from individual providers listed under SQLprovider directory.
3. SQLprovider -> This folder consists of all the methods that makes database calls, and sends results 
    taking into respective data structure to AccountSectionFunctionality for IO.
4. Models: Data Model Classes that are used in the project.
5. Common: This directory consists of common classes like database connection that is shared within the project.
6. db.properties: properties file to store database details.

## External JAR's Used

1. ojdbc.jar --for oracle database management
2. itextPdf.jar --for text file to pdf file conversion



                
