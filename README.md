# To-do List web application using Hibernate and Tomcat

## Overview
This exercise uses a GUI (Graphical User Interface) that allows a user to create to-do items that are stored to and retrieved from a MySQL database.

## What does this program do?
The program has two GUIs. The first uses JavaFX and allows the user to create (add), view, and delete to-do items. The second is a web application using Tomcat, which allows the user to view, add, edit, and delete to-do items. The to-do items are saved to a MySQL database, which is queried every time the application runs.

## System requirements
The JavaFX application uses version 8 of Amazon's distribution of the Open Java Development Kit (OpenJDK) [Corretto 8](https://aws.amazon.com/corretto/), which includes JavaFX 8. The web application relies on Tomcat for deployment. Both use Hibernate and persistence to perform JDBC operations on a MySQL database.

## Database connection defaults
The program assumes that a database named `module_07_db` exists in the local MySQL database and a user with all database privileges exists with username/password: `module07user`/`module07pass`. It also assumes that it will connect to localhost using default port 3306. 

The user may change these initial configuration options by editing lines 16, 20, and 24 in the hibernate configuration file: 

```
/src/model/hibernate.cfg.xml

16    jdbc:mysql://localhost:3306/module_05_db?serverTimezone=America/New_York
...
20    module05user
...
24    module05pass
```

## How to use this program.
### JavaFX application
The GUI consists of a single stage (window) and a single scene (window content). The user can create to-do items by specifying a description of the to-do item, additional details of the item, and a due date. New items can be added by selecting the "Add new item" menu option in the Edit menu or by pressing `CTRL-N`.

The user may also delete a selected item by selecting the "Delete selected item" menu option in the Edit menu or by pressing the `Delete` key.

Newly added to-do items will be saved to the MySQL database.

The user may exit the application by closing the application window, by selecting the "Exit" menu opion in the File menu, or by pressing `ALT-Q`.

### Web application
The web application opens to a list of all to-do items in the database. The user may add, edit, or delete new items. The web application will run until the user stops the Tomcat server within the IDE. Closing the browser window will not stop the application.

## Installation.
Clone the repo and import it into your favorite Java IDE. Make sure that:
 1. The project SDK is Java 8 with project language level 8, and
 2. JavaFX 8, Hibernate, and the Oracle [JDBC Driver](https://dev.mysql.com/downloads/connector/j/) for MySQL are installed in your system.
 3. The latest version of Tomcat is installed in your system.

## Known Issues
This application was developed using the IntelliJ IDEA Ultimate IDE. When generating the WAR exploded artifact for deployment, not all dependencies were included by the IDE. There are two ways to solve this issue. The first requires copying the required dependencies to the Tomcat/lib folder. The second relies on adding the required dependencies to the WEB-INF/lib folder. I prefer the second option, as the dependencies will stay with the project as opposed to having them in folders outside the project structure, limiting its portability. Files located in lib folders are not typically included in version control systems. This project will follow that guideline, but note that the following files are required for the application to run with Tomcat:

```
antlr-2.7.7.jar
byte-buddy-1.10.7.jar
classmate-1.5.1.jar
dom4j-2.1.1.jar
FastInfoset-1.2.15.jar
hibernate-commons-annotations-5.1.0.Final.jar
hibernate-core-5.4.11.Final.jar
istack-commons-runtime-3.0.7.jar
jandex-2.1.1.Final.jar
javassist-3.24.0-GA.jar
javax.activation-api-1.2.0.jar
javax.annotation.jar
javax.ejb.jar
javax.jms.jar
javax.persistence-api-2.2.jar
javax.resource.jar
javax.servlet.jar
javax.servlet.jsp.jar
javax.servlet.jsp.jstl.jar
jaxb-api-2.3.1.jar
jaxb-runtime-2.3.1.jar
jboss-logging-3.3.2.Final.jar
jboss-transaction-api_1.2_spec-1.1.1.Final.jar
stax-ex-1.8.jar
taglibs-standard-impl-1.2.5.jar
txw2-2.3.1.jar
```
