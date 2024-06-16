# **FinalProjectAutomationTest**

## Overview

This project aims to automate the testing process for https://hrm.anhtester.com/ using automated tests. It covers
various functionalities including managing clients, projects, tasks, updating project status, attaching files, and
generating logs, reports, video records, and screenshots for failed cases using Allure or Extent frameworks.
Additionally, it supports parallel execution for efficient testing and utilizes configuration parameters from properties
files and test data from Excel files.

## Features

### Manage Clients

* Add, edit, and delete clients.

### Authentication Testing

* Test login functionality for newly created client accounts.
    * Check invalid username.
    * Check invalid password.
    * Check successful login.

### Manage Projects

* Add, edit, and delete projects.

#### Edit Projects

* Task
   * Add Task for Projects
   * Delete Task
  

* Status
  * Update status for Projects


* File Attachment
  * Add attachments to projects.

### Logging and Reporting

* Generate logs, reports, video records, and screenshots for failed test cases.
    * Utilizes Allure or Extent frameworks.

### Parallel Execution

* Execute tests in parallel for optimal efficiency.

### Configuration Parameters

* Manage configuration parameters via properties files.

### Test Data

* Utilize test data from Excel files.
    * Data is organized into sheets and files for ease of access.

## Getting Started

1. Clone the repository.
2. Set up dependencies as specified in the documentation.
3. Configure properties files with appropriate parameters.
4. Prepare test data in Excel files.
5. Run tests using your preferred testing framework.

## Dependencies

* Selenium Java (org.seleniumhq.selenium:selenium-java:4.21.0)
* TestNG (org.testng:testng:7.4.0)
* Apache POI (org.apache.poi:poi:5.2.5)
* Apache POI OOXML (org.apache.poi:poi-ooxml:5.2.5)
* Commons IO (commons-io:commons-io:2.16.1)
* Log4j Core (org.apache.logging.log4j:log4j-core:2.23.1)
* Log4j API (org.apache.logging.log4j:log4j-api:2.23.1)
* SLF4J API (org.slf4j:slf4j-api:2.0.13)
* SLF4J Simple (org.slf4j:slf4j-simple:2.0.13)
* Monte Screen Recorder (com.github.stephenc.monte:monte-screen-recorder:0.7.7.0)
* Extent Reports (com.aventstack:extentreports:5.1.1)
* Allure TestNG (io.qameta.allure:allure-testng:2.26.0)
* Allure Attachments (io.qameta.allure:allure-attachments:2.26.0)
* AspectJ Weaver (org.aspectj:aspectjweaver:1.9.22)
* Lombok (org.projectlombok:lombok:1.18.32)

# License

* Apache License

# Authors

* Vo Quynh Nhi

